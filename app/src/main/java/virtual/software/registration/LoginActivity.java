package virtual.software.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

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

import static virtual.software.registration.Endpoint.LOGIN;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;
import static virtual.software.registration.LoginInfo.*;

public class LoginActivity extends AppCompatActivity {


    EditText txtUsername, txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        txtUsername.setText("Mark");
        txtPassword.setText("aaaa");
    }

    public void loginClicked(View view) {

        view.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
        login(view, txtUsername.getText().toString(), txtPassword.getText().toString());
    }


    private void login(final View view, final String username, final String password) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseHere", response);

                        if (response.contains("No Data")) {

                            Snackbar.make(view, "Invalid", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        } else {
                            JSONObject jObject = null;
                            try {
                                jObject = new JSONObject(response.replace("[", "").replace("]", ""));

//                                setModelPersonFromJson(jObject);
                                setLoginInfo(jObject);

                            } catch (JSONException e) {
                                Log.e("resultSaError", response);
                                e.printStackTrace();

                            }

//                            // userInfo.cn = response;
                            startActivity(new Intent(getApplicationContext(), MainListActivity.class));
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
                        params.put("login", "");
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

    private void setLoginInfo(JSONObject jObject) throws JSONException {
        RecID = jObject.getString("RecId");
        Username = jObject.getString("userid");
        Password = jObject.getString("password");
    }


    public void createAccountClicked(View view) {

        saveTag = "save";

        Log.d("saveTasg", saveTag);
        modelPerson = new ModelPerson();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class));
        finish();
    }
}
