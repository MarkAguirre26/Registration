package virtual.software.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import static virtual.software.registration.TemporaryData.isSaved;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;

public class AddressActivity extends AppCompatActivity {

    EditText txtHousehold, txtStreet, txtZone, txBarangay, txtCity;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initComponents();

        txtZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] items = {"Purok Sampaguita", "Purok Gumamela", "Purok Jasmin", "Purok Camia", "Purok Rosal", "Purok Sunflower", "Purok Dahlia"
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                //set the title for alert dialog
                builder.setTitle("Choose names: ");

                //set items to alert dialog. i.e. our array , which will be shown as list view in alert dialog
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        //setting the button text to the selected itenm from the list
//                button.setText(items[item]);
                    }
                });

                //Creating CANCEL button in alert dialog, to dismiss the dialog box when nothing is selected
                builder.setCancelable(false)
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //When clicked on CANCEL button the dalog will be dismissed
                                dialog.dismiss();
                            }
                        });

                //Creating alert dialog
                AlertDialog alert = builder.create();

                //Showingalert dialog
                alert.show();

            }
        });
    }

    private void initComponents() {
        this.setTitle("Address Information");
        txtHousehold = findViewById(R.id.txtHousehold);
        txtStreet = findViewById(R.id.txtStreet);
        txtZone = findViewById(R.id.txtZone);
        txBarangay = findViewById(R.id.txtBarangay);
        txtCity = findViewById(R.id.txtCity);


        if (saveTag.equals(getResources().getString(R.string.edit))) {
            txtHousehold.setText(modelPerson.getHousehold());
            txtStreet.setText(modelPerson.getStreet());
            txtZone.setText(modelPerson.getZone());
            txBarangay.setText(modelPerson.getBarangay());
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
        modelPerson.setMunicipality("-");
        modelPerson.setBarangay(txBarangay.getText().toString());
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
