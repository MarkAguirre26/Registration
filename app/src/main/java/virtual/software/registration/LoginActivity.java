package virtual.software.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static virtual.software.registration.Endpoint.GET_USER;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;

public class LoginActivity extends AppCompatActivity {


    EditText txtUsername, txtPassword;


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
        getUser(view, txtUsername.getText().toString(), txtPassword.getText().toString());
    }


    private void getUser(final View view, final String username, final String password) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_USER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.contains("No Data")) {
                            Log.e("errRe", response);
                            Snackbar.make(view, "Invalid", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        } else {
                            JSONObject jObject = null;
                            try {
                                jObject = new JSONObject(response.replace("[", "").replace("]", ""));

                                setModelPersonFromJson(jObject);


                            } catch (JSONException e) {
                                Log.e("resultSaError", response);
                                e.printStackTrace();

                            }

//                            // userInfo.cn = response;
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(view, "Try Again", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("select", "");
                        params.put("Username", username);
                        params.put("Password", password);
                        return params;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        };
        new Thread(runnable).start();
    }


    private void setModelPersonFromJson(JSONObject jsonObj) throws JSONException {

        String RecId = jsonObj.getString("id");
        String Username = jsonObj.getString("Username");
        String Password = jsonObj.getString("Password");
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


        modelPerson = new ModelPerson();
        modelPerson.setRegId(RecId);
        modelPerson.setUsername(Username);
        modelPerson.setPassword(Password);
        modelPerson.setLastName(Lastname);
        modelPerson.setFirstName(Firstname);
        modelPerson.setMiddleName(Middlename);
        modelPerson.setCivilStatus(Civilstatus);
        modelPerson.setDateofBirth(Birthdate);
        modelPerson.setPlaceofBirth(Placeofbirth);
        modelPerson.setCitizenship(Citizenship);
        modelPerson.setOccupation(Cccupation);
        modelPerson.setGender(Gender);
        modelPerson.setVoter(Voter);
        modelPerson.setHousehold(HouseHoldNo);
        modelPerson.setStreet(Street);
        modelPerson.setZone(Zone);
        modelPerson.setBarangay(Barangay);
        modelPerson.setProvince(Province);
        modelPerson.setMunicipality(Municipality);
        modelPerson.setOther(Other);
        modelPerson.setAge(Age);

//        Toast.makeText(getApplicationContext(),modelPerson.getLastName(),Toast.LENGTH_LONG).show();
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));


    }

    public void createAccountClicked(View view) {

        saveTag = "Save";
        modelPerson =  new ModelPerson();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(new Intent(getApplicationContext(), PersonInfoActivity.class));
        finish();
    }
}
