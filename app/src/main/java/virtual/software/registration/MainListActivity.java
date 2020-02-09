package virtual.software.registration;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static virtual.software.registration.Endpoint.GET_USERS;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;

public class MainListActivity extends AppCompatActivity {


    private static final String url = GET_USERS;
    private ProgressDialog pDialog;
    private List<DataModel> usersList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;
    ImageView profile_imageview;

    private static final String TAG = MainActivity.class.getSimpleName();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, usersList);
        listView.setAdapter(adapter);


        this.setTitle("Personal Information");

        profile_imageview = findViewById(R.id.profile_imageview);


        pDialog = new ProgressDialog(this);

        checkPermissions();

        retrieveJSON();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                saveTag = "save";

                Log.d("saveTasg", saveTag);
                modelPerson = new ModelPerson();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(new Intent(getApplicationContext(), PersonInfoActivity.class));
                finish();


            }
        });
    }


    private void checkPermissions() {
        String per[] = {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        };

        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String s : per) {
            if (ContextCompat.checkSelfPermission(this, s) == PackageManager.PERMISSION_GRANTED) {
                continue;
            }
            listPermissionsNeeded.add(s);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    private void retrieveJSON() {
        showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);
        // Creating volley request obj
        JsonArrayRequest usersReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                DataModel d = new DataModel();
                                d.setRecid(obj.getString("id"));
                                d.setName(obj.getString("RName"));
                                d.setBarangay(obj.getString("Barangay"));
                                d.setBarangay(obj.getString("Municipality"));
                                d.setImgURL(obj.getString("imgURL") + ".jpg");


                                // adding movie to movies array
                                usersList.add(d);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        setupListview();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(usersReq);

    }

    private void setupListview() {
        removeSimpleProgressDialog();  //will remove progress dialog
//        adapter = new ListAdapter(this, );
        adapter.notifyDataSetChanged();
//        listView.setAdapter(adapter);
    }

    public void removeSimpleProgressDialog() {
        try {
            if (pDialog != null) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                    pDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {

        final android.app.Dialog dialog = new android.app.Dialog(MainListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.diloag_confirm_exit_layout);
        Button yesDialogButton = (Button) dialog.findViewById(R.id.btn_confirm_yes_dialog);
        yesDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAffinity();
            }
        });

        dialog.show();

        Button noDialogButton = (Button) dialog.findViewById(R.id.btn_confirm_no_dialog);
        noDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }


    public void showSimpleProgressDialog(Context context, String title,
                                         String msg, boolean isCancelable) {
        try {
            if (pDialog == null) {
                pDialog = ProgressDialog.show(context, title, msg);
                pDialog.setCancelable(isCancelable);
            }

            if (!pDialog.isShowing()) {
                pDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
