package virtual.software.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static virtual.software.registration.TemporaryData.isSaved;
import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;

public class PersonInfoActivity extends AppCompatActivity {

    EditText txtLastName, txtFirstName, txtMiddleName, txtCivilStatus, txtDateofBirth, txtPlaceofBirth, txtCitizenship, txtOccupation;
    RadioButton rbMale, rbFeMale, rbVoterYes, rbVoeterNo;

    PersonController personController;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        initComponents();

    }

    private void initComponents() {
        this.setTitle("Personal Information");


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("PWD");
        categories.add("Senior");
        categories.add("4P's");
        categories.add("SK");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        txtLastName = findViewById(R.id.txtLastName);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtMiddleName = findViewById(R.id.txtMiddleName);
        txtCivilStatus = findViewById(R.id.txtCivilStatus);
        txtDateofBirth = findViewById(R.id.txtDateofBirth);
        txtPlaceofBirth = findViewById(R.id.txtPlaceofBirth);
        txtCitizenship = findViewById(R.id.txtCitizenship);
        txtOccupation = findViewById(R.id.txtOccupation);
        rbMale = findViewById(R.id.rbMale);
        rbFeMale = findViewById(R.id.rbFeMale);
        rbVoterYes = findViewById(R.id.rbVoterYes);
        rbVoeterNo = findViewById(R.id.rbVoterNo);
        spinner = findViewById(R.id.spinner);



        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        if (saveTag.equals(getResources().getString(R.string.edit))) {
            txtLastName.setText(modelPerson.getLastName());
            txtFirstName.setText(modelPerson.getFirstName());
            txtMiddleName.setText(modelPerson.getMiddleName());
            txtCivilStatus.setText(modelPerson.getCivilStatus());
            txtDateofBirth.setText(modelPerson.getDateofBirth());
            txtPlaceofBirth.setText(modelPerson.getPlaceofBirth());
            txtCitizenship.setText(modelPerson.getCitizenship());
            txtOccupation.setText(modelPerson.getOccupation());

            spinner.setSelection(dataAdapter.getPosition(modelPerson.getOther()));
            if (modelPerson.getGender().equals("Female")) {
                rbFeMale.setChecked(true);
                rbMale.setChecked(false);
            } else {
                rbFeMale.setChecked(false);
                rbMale.setChecked(true);
            }

            if (modelPerson.getVoter().toLowerCase().equals(String.valueOf(R.string.yes))) {
                rbVoterYes.setChecked(true);
                rbVoeterNo.setChecked(false);
            } else {
                rbVoterYes.setChecked(false);
                rbVoeterNo.setChecked(true);
            }

        }

    }


    private String getGender(RadioButton male, RadioButton female) {
        if (male.isChecked()) {
            return "Male";
        }
        if (female.isChecked()) {
            return "Female";
        }
        return "";
    }


    private String getYesNo(RadioButton yes, RadioButton no) {
        if (yes.isChecked()) {
            return "Yes";
        }
        if (no.isChecked()) {
            return "No";
        }
        return "";
    }


    public void next_Clicked(View view) {

        view.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));


        modelPerson.setLastName(txtLastName.getText().toString());
        modelPerson.setFirstName(txtFirstName.getText().toString());
        modelPerson.setMiddleName(txtMiddleName.getText().toString());
        modelPerson.setCivilStatus(txtCivilStatus.getText().toString());
        modelPerson.setDateofBirth(txtDateofBirth.getText().toString());
        modelPerson.setPlaceofBirth(txtPlaceofBirth.getText().toString());
        modelPerson.setCitizenship(txtCitizenship.getText().toString());
        modelPerson.setOccupation(txtOccupation.getText().toString());
        modelPerson.setGender(getGender(rbMale, rbFeMale));
        modelPerson.setVoter(getYesNo(rbVoterYes, rbVoeterNo));
        modelPerson.setOther(spinner.getSelectedItem().toString());


        personController = new PersonController(modelPerson);
        if (!personController.isPersonalInfoNotEmpty()) {
            Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        modelPerson = modelPerson;
        //GO to next screen
        startActivity(new Intent(getApplicationContext(), AddressActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }


}
