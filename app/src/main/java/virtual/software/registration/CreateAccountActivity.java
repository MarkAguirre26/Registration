package virtual.software.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static virtual.software.registration.Endpoint.POST_USER;
import static virtual.software.registration.TemporaryData.isSaved;

public class CreateAccountActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword, txtRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initComponents();
    }

    private void initComponents() {
        txtUsername = findViewById(R.id.txtUsernameCreate);
        txtPassword = findViewById(R.id.txtPasswordCreate);
        txtRePassword = findViewById(R.id.txtRePasswordCreate);
    }

    public void loginClicked(View view) {

        loginPage();

    }

    public Boolean createUserAccount() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_USER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("userAccount", response.toString());

                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                        loginPage();
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
                        params.put("new_user", "");
                        params.put("Username", txtUsername.getText().toString());
                        params.put("Password", txtPassword.getText().toString());
                        params.put("Position", "-");
                        params.put("Usertype", "Official");
                        return params;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        };
        new Thread(runnable).start();


        return isSaved;
    }

    private void loginPage() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void createAccountClicked(View view) {

        createUserAccount();
    }

    @Override
    public void onBackPressed() {
        loginPage();
    }
}
