package virtual.software.registration;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static virtual.software.registration.Endpoint.POST_USER;
import static virtual.software.registration.TemporaryData.isSaved;
import static virtual.software.registration.TemporaryData.saveTag;

public class PersonController {

    ModelPerson person;
    Context ctx;

    public PersonController() {
    }

    public PersonController(ModelPerson modelPerson, Context context) {
        person = modelPerson;
        ctx = context;
    }


    public Boolean savePerson() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_USER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("responseFromSavePerson", response.toString());
//                        if (saveTag.equals("save")) {
//                            createUserAccount(response.toString());
//                        }

                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        isSaved = false;
                        Log.e("errorHere", error.networkResponse.statusCode + "");
//                        Snackbar.make(view, "Try Again", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        if (saveTag.equals("save")) {
                            params.put("new", "");
//                            params.put("Username", person.getUsername());
//                            params.put("Password", person.getPassword());
                        } else {
                            params.put("edit", "");
                            params.put("REG_NUMBER", person.getRegId());
                        }
                        params.put("Zone", person.getZone());
                        params.put("Placeofbirth", person.getPlaceofBirth());
                        params.put("Birthdate", person.getDateofBirth());
                        params.put("Gender", person.getGender());
                        params.put("Civilstatus", person.getCivilStatus());
                        params.put("Citizenship", person.getCitizenship());
                        params.put("Occupation", person.getOccupation());
                        params.put("Voter", person.getVoter());
                        params.put("Other", person.getOther());
                        params.put("Province", person.getProvince());
                        params.put("Municipality", person.getMunicipality());
                        params.put("Barangay", person.getBarangay());
                        params.put("HouseHoldNo", person.getHousehold());
                        params.put("Lastname", person.getLastName());
                        params.put("Firstname", person.getFirstName());
                        params.put("Middlename", person.getMiddleName());
                        params.put("Street", person.getStreet());

                        return params;
                    }
                };
                MySingleton.getInstance(ctx).addToRequestQueue(stringRequest);
            }
        };
        new Thread(runnable).start();


        return isSaved;
    }


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


    public boolean isAddressInfoNotEmpty() {
        if (person.getHousehold().equals("")
                || person.getStreet().equals("")
                || person.getZone().equals("")
                || person.getBarangay().equals("")
//                || person.getProvince().equals("")
                || person.getMunicipality().equals("")) {
            return false;
        }
        return true;
    }


}
