package virtual.software.registration;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import static virtual.software.registration.Endpoint.POST_USER;
import static virtual.software.registration.TemporaryData.isSaved;
import static virtual.software.registration.TemporaryData.saveTag;

public class PersonController implements personInterface {

    ModelPerson person;

    public PersonController(ModelPerson modelPerson) {
        person = modelPerson;
    }

    @Override
    public ModelPerson getPerson() {

        return person;
    }

    @Override
    public Boolean savePerson() {
        String url = "";

        if (saveTag.equals("save")) {
            url = POST_USER + "new";
        } else {
            url = POST_USER + "edit";
        }

        AndroidNetworking.post(url)
//                .addBodyParameter(person) // posting java object
                .addBodyParameter("Zone", person.getZone())
                .addBodyParameter("Placeofbirth", person.getPlaceofBirth())
                .addBodyParameter("Birthdate", person.getDateofBirth())
                .addBodyParameter("Gender", person.getGender())
                .addBodyParameter("Civilstatus", person.getCivilStatus())
                .addBodyParameter("Citizenship", person.getCitizenship())
                .addBodyParameter("Occupation", person.getOccupation())
                .addBodyParameter("Voter", person.getVoter())
                .addBodyParameter("Other", person.getOther())
                .addBodyParameter("Province", person.getProvince())
                .addBodyParameter("Municipality", person.getMunicipality())
                .addBodyParameter("Barangay", person.getBarangay())
                .addBodyParameter("HouseHoldNo", person.getHousehold())
                .addBodyParameter("Lastname", person.getLastName())
                .addBodyParameter("Firstname", person.getFirstName())
                .addBodyParameter("Middlename", person.getMiddleName())
                .addBodyParameter("RegId", person.getRegId())
                .addBodyParameter("Street", person.getStreet())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
//                        Log.d("AnyText", response.get(0).toString());
                        isSaved = true;
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.e("eerrSave", error.getErrorDetail());
                        isSaved = false;
                    }
                });
        return isSaved;
    }

    @Override
    public boolean isPersonalInfoNotEmpty() {
        if (person.getLastName().equals("")
                || person.getFirstName().equals("")
                || person.getMiddleName().equals("")
                || person.getCivilStatus().equals("")
                || person.getDateofBirth().equals("")
                || person.getPlaceofBirth().equals("")
                || person.getCitizenship().equals("")
                || person.getOccupation().equals("")
                || person.getVoter().equals("")
                || person.getOther().equals("")
                || person.getGender().equals("")) {
            return false;
        }
        return true;
    }


    @Override
    public boolean isAddressInfoNotEmpty() {
        if (person.getHousehold().equals("")
                || person.getStreet().equals("")
                || person.getZone().equals("")
                || person.getBarangay().equals("")
                || person.getProvince().equals("")
                || person.getMunicipality().equals("")) {
            return false;
        }
        return true;
    }


}
