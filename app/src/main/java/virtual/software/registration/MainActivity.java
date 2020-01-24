package virtual.software.registration;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static virtual.software.registration.TemporaryData.encodedImage;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;


public class MainActivity extends AppCompatActivity {

    private static final int SELECTED_PIC = 1;
    ImageView profile_imageview;
    Bitmap bitmap;
    String image_name = "imageNameHere";
    private static final int REQUEST_CAPTURE_IMAGE = 100;


    private ProgressDialog progressDialog;
    private String ImageName = "image_name";
    private String ImagePath = "image_path";
    private boolean check = true;
    int SELECT_PICTURE = 101;
    int CAPTURE_IMAGE = 102;


    JSONObject jsonObject;
    RequestQueue rQueue;


    TextView txtNameInfo,
            txtAgeGenderInfo,
            tvBirthdate,
            tvPlaceofbirth,
            tvCitizenship,
            tvCccupation,
            tvVoter,
            tvAddress;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        checkPermissions();


        getUserData();
    }

    private void initComponents() {
        this.setTitle("Personal Information");

        profile_imageview = findViewById(R.id.profile_imageview);

        txtNameInfo = findViewById(R.id.txtNameInfo);
        txtAgeGenderInfo = findViewById(R.id.txtAgeGenderInfo);
        tvBirthdate = findViewById(R.id.tvBirthdate);
        tvPlaceofbirth = findViewById(R.id.tvPlaceofbirth);
        tvCitizenship = findViewById(R.id.tvCitizenship);
        tvCccupation = findViewById(R.id.tvCccupation);
        tvVoter = findViewById(R.id.tvVoter);
        tvAddress = findViewById(R.id.tvAddress);


//        Toast.makeText(getApplicationContext(), modelPerson.getRegId(), Toast.LENGTH_SHORT).show();

    }


    private void newAccount() {
        startActivity(new Intent(getApplicationContext(), PersonInfoActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }


    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent,
                    REQUEST_CAPTURE_IMAGE);
        }
    }


    @Override
    public void onBackPressed() {

        final android.app.Dialog dialog = new android.app.Dialog(MainActivity.this);
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


    public void editClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));

        if (modelPerson.getRegId() == null) {
            Toast.makeText(getApplicationContext(), "Function not available this time", Toast.LENGTH_SHORT).show();
            return;
        }

        saveTag = getResources().getString(R.string.edit);

        startActivity(new Intent(getApplicationContext(), PersonInfoActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Make sure the request was successful
        Log.d("Vicky", "I'm out.");
        if (requestCode == REQUEST_CAPTURE_IMAGE &&
                resultCode == RESULT_OK) {

            if (data != null && data.getExtras() != null) {


                bitmap = (Bitmap) data.getExtras().get("data");
                profile_imageview.setImageBitmap(bitmap);
                BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
                profile_imageview.setBackground(ob);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);


                uploadImage(bitmap);


            }
//                Uri selectedImageUri = data.getData();


        }
    }

    public void galleryClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
        openCameraIntent();
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, SELECT_PICTURE);
    }

//    @SuppressLint("MissingPermission")
//    public String getMyPhoneNumber() {
//        return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
//                .getLine1Number();
//    }


    public void uploadClicked(View view) {


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


    public void viewImageClicked(View view) {

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        startActivity(new Intent(getApplicationContext(), ImageViewwerActivity.class));
        bitmap = ((BitmapDrawable) profile_imageview.getDrawable()).getBitmap();
        TemporaryData.bitmap = bitmap;

    }

    private void getUserData() {


        txtNameInfo.setText(modelPerson.getFirstName() + " " + modelPerson.getMiddleName() + " " + modelPerson.getLastName());
        txtAgeGenderInfo.setText(modelPerson.getGender() + ", " + modelPerson.getAge() + ", " + modelPerson.getCivilStatus());
        tvBirthdate.setText(modelPerson.getDateofBirth());
        tvPlaceofbirth.setText(modelPerson.getPlaceofBirth());
        tvCitizenship.setText(modelPerson.getCitizenship());
        tvCccupation.setText(modelPerson.getOccupation());
        tvVoter.setText(modelPerson.getVoter());
        tvAddress.setText(modelPerson.getHousehold() + ", " + modelPerson.getStreet() + ", " + modelPerson.getZone() + ", " + modelPerson.getBarangay() + ", " + modelPerson.getMunicipality());


        getUserPhoto();
    }

    private void getUserPhoto() {
        String photo = Endpoint.GET_USER_PHOTO + modelPerson.getFirstName() + modelPerson.getMiddleName() + modelPerson.getLastName() + ".jpg";
        Log.d("photo", photo);
        Picasso.get().load(photo.replace(" ", "")).into(profile_imageview);

    }

    public void refreshClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));

    }


    private void uploadImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        try {
            jsonObject = new JSONObject();
            String imgname = String.valueOf(Calendar.getInstance().getTimeInMillis());
            jsonObject.put("name", imgname);
            //  Log.e("Image name", etxtUpload.getText().toString().trim());
            jsonObject.put("image", encodedImage);
            // jsonObject.put("aa", "aa");
        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Endpoint.POST_UPLOAD_IMAGE, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("response", jsonObject.toString());
                        rQueue.getCache().clear();
                        Toast.makeText(getApplication(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("responseError", volleyError.toString());

            }
        });

        rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(jsonObjectRequest);

    }

        private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }


}

