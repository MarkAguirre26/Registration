package virtual.software.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static virtual.software.registration.Endpoint.GET_USERS;
import static virtual.software.registration.TemporaryData.encodedImage;
import static virtual.software.registration.TemporaryData.modelPerson;

public class UploadImageActivity extends AppCompatActivity {

    // Log tag


    private static final int REQUEST_CAPTURE_IMAGE = 100;
    Bitmap bitmap;

    JSONObject jsonObject;
    RequestQueue rQueue;
    PersonController personController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
    }

    public void uploadClicked(View view) {
        openCameraIntent();
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
//                profile_imageview.setImageBitmap(bitmap);
                BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
//                profile_imageview.setBackground(ob);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

                personController = new PersonController(modelPerson, getApplicationContext());
                if (!personController.isAddressInfoNotEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                personController.savePerson().contains("true");
                uploadImage(bitmap);
                closeandGotoMainList();


            }
//                Uri selectedImageUri = data.getData();


        }


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


    private void uploadImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        try {
            jsonObject = new JSONObject();
            String imgname = String.valueOf(Calendar.getInstance().getTimeInMillis());
            jsonObject.put("name", modelPerson.getLastName() + modelPerson.getFirstName() + modelPerson.getMiddleName());
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
//                        Toast.makeText(getApplication(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("responseError", volleyError.toString());

            }
        });

        rQueue = Volley.newRequestQueue(UploadImageActivity.this);
        rQueue.add(jsonObjectRequest);

    }


    private void closeandGotoMainList() {
        startActivity(new Intent(getApplicationContext(), MainListActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finishAffinity();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), AddressActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    public void showDialog() {
        final android.app.Dialog dialog = new android.app.Dialog(UploadImageActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.diloag_congratulation_layout);

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                closeandGotoMainList();
            }
        });

        dialog.show();

    }


}
