package virtual.software.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static virtual.software.registration.Endpoint.GET_USER;
import static virtual.software.registration.TemporaryData.modelPerson;

public class LoginActivity extends AppCompatActivity {


    EditText txtUsername, txtPassword;
    ModelPerson _mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        txtUsername.setText("mark");
        txtPassword.setText("aaaaaaa");
    }

    public void loginClicked(View view) {

        view.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
        getUser(txtUsername.getText().toString(), txtPassword.getText().toString());
    }


    private void getUser(String username, String password) {

//        String imei = Utils.getDeviceIMEI(this);
//        Log.d("imei", imei);
        AndroidNetworking.get(GET_USER + "username=" + username + "&password=" + password)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("size", response.length() + "");
                            for (int i = 0; i < response.length(); i++) {


                                JSONObject jsonObj = response.getJSONObject(i);

                                setModelPersonFromJson(jsonObj);


                            }

                        } catch (JSONException e) {
                            Log.e("errorDito", e.getMessage());
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void setModelPersonFromJson(JSONObject jsonObj) throws JSONException {

        String RecId = jsonObj.getString("REG_NUMBER");
        String Lastname = jsonObj.getString("Lastname");
        String Firstname = jsonObj.getString("Firstname");
        String Middlename = jsonObj.getString("Middlename");
        String Age = jsonObj.getString("Age");

        String Placeofbirth = jsonObj.getString("Placeofbirth");
        String Birthdate = jsonObj.getString("Birthdate");
        String Gender = jsonObj.getString("Gender");
        String Civilstatus = jsonObj.getString("Civilstatus");
        String Citizenship = jsonObj.getString("Citizenship");
        String Cccupation = jsonObj.getString("Cccupation");
        String Voter = jsonObj.getString("Voter");
        String Other = jsonObj.getString("Other");

        String HouseHoldNo = jsonObj.getString("HouseHoldNo");
        String Street = jsonObj.getString("Street");
        String Zone = jsonObj.getString("Zone");
        String Barangay = jsonObj.getString("Barangay");
        String Province = jsonObj.getString("Province");
        String Municipality = jsonObj.getString("Municipality");
        String photo = jsonObj.getString("Photo");

        _mPerson = new ModelPerson();
        _mPerson.setRegId(RecId);
        _mPerson.setLastName(Lastname);
        _mPerson.setFirstName(Firstname);
        _mPerson.setMiddleName(Middlename);
        _mPerson.setCivilStatus(Civilstatus);
        _mPerson.setDateofBirth(Birthdate);
        _mPerson.setPlaceofBirth(Placeofbirth);
        _mPerson.setCitizenship(Citizenship);
        _mPerson.setOccupation(Cccupation);
        _mPerson.setGender(Gender);
        _mPerson.setVoter(Voter);
        _mPerson.setHousehold(HouseHoldNo);
        _mPerson.setStreet(Street);
        _mPerson.setZone(Zone);
        _mPerson.setBarangay(Barangay);
        _mPerson.setProvince(Province);
        _mPerson.setMunicipality(Municipality);
        _mPerson.setOther(Other);
        _mPerson.setAge(Age);
        modelPerson = _mPerson;

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        startActivity(new Intent(getApplicationContext(), MainActivity.class));


    }

    public void createAccountClicked(View view) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(new Intent(getApplicationContext(), PersonInfoActivity.class));
        finish();
    }
}
