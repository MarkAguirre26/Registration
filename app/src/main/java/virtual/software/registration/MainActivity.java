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


import com.squareup.picasso.Picasso;

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
import java.util.List;

import okhttp3.OkHttpClient;

import static virtual.software.registration.TemporaryData.encodedImage;
import static virtual.software.registration.TemporaryData.jsonObject;
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

    public void newAccountClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
        saveTag = "save";
        newAccount();

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
                Log.d("Vicky", "I'm in.");


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
        new UploadImages().execute();

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

    private class UploadImages extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Log.d("Vicky", "encodedImage = " + encodedImage);
//            jsonObject = new JSONObject();
                jsonObject.put("imageString", encodedImage);
                jsonObject.put("imageName", "+917358513024");
                String data = jsonObject.toString();
                String yourURL = "http://192.168.254.108/bio/config/img_upload_to_server.php?imageString=" + encodedImage + "&imageName=dito";

                URL url = new URL(yourURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setFixedLengthStreamingMode(data.getBytes().length);
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(data);
                Log.d("Vicky", "Data to php = " + data);
                writer.flush();
                writer.close();
                out.close();
                connection.connect();

                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        in, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                in.close();
                String result = sb.toString();
                Log.d("Vicky", "Response from php = " + result);
                //Response = new JSONObject(result);
                connection.disconnect();
            } catch (Exception e) {
                Log.d("Vicky", "Error Encountered");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

        }

    }


}

