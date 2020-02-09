package virtual.software.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static virtual.software.registration.TemporaryData.isSaved;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;

public class AddressActivity extends AppCompatActivity {

    EditText txtHousehold, txtStreet, txtZone, txBarangay, txtProvince, txtCity;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Address Information");
        txtHousehold = findViewById(R.id.txtHousehold);
        txtStreet = findViewById(R.id.txtStreet);
        txtZone = findViewById(R.id.txtZone);
        txBarangay = findViewById(R.id.txtBarangay);
        txtProvince = findViewById(R.id.txtProvince);
        txtCity = findViewById(R.id.txtCity);




        if (saveTag.equals(getResources().getString(R.string.edit))) {
            txtHousehold.setText(modelPerson.getHousehold());
            txtStreet.setText(modelPerson.getStreet());
            txtZone.setText(modelPerson.getZone());
            txBarangay.setText(modelPerson.getBarangay());
            txtProvince.setText(modelPerson.getProvince());
            txtCity.setText(modelPerson.getMunicipality());

        }
    }


    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        startActivity(new Intent(getApplicationContext(), PersonInfoActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }




    public void save_Clicked(View view) {

        view.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));

        //1. initialized modelPerson from init method

        modelPerson.setHousehold(txtHousehold.getText().toString());
        modelPerson.setStreet(txtStreet.getText().toString());
        modelPerson.setZone(txtZone.getText().toString());
        modelPerson.setBarangay(txBarangay.getText().toString());
        modelPerson.setProvince(txtProvince.getText().toString());
        modelPerson.setMunicipality(txtCity.getText().toString());
//        modelPerson.setRegId(Utils.getDeviceIMEI(this));//Change the  temporary number
        //1. Pass the modelPerson to Controller for validation




//        go to upload image
        startActivity(new Intent(getApplicationContext(), UploadImageActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();



    }


    public void showErrorDialog() {
        final android.app.Dialog dialog = new android.app.Dialog(AddressActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.diloag_error_layout);

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_error_dlg);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }




}
