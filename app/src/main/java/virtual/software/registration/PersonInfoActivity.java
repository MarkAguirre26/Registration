package virtual.software.registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static virtual.software.registration.TemporaryData.modelPerson;
import static virtual.software.registration.TemporaryData.saveTag;

public class PersonInfoActivity extends AppCompatActivity {

    EditText txtLastName, txtFirstName, txtMiddleName, txtDateofBirth,
            txtPlaceofBirth, txtCitizenship, txtOccupation,
            txtUsername_reg, txtPassword_reg;
    RadioButton rbMale, rbFeMale, rbVoterYes, rbVoeterNo;

    PersonController personController;
    Spinner spinner, spinnerMaritalStatus;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        initComponents();

        txtDateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PersonInfoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDateofBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();
            }
        });
    }

    private void initComponents() {
        this.setTitle("Personal Information");


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("PWD");
        categories.add("Senior");
        categories.add("4P's");
        categories.add("SK");

//        List<String> categoriesCivilStatus = new ArrayList<String>();
//        categories.add("Married");
//        categories.add("Single");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapterCivilStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesCivilStatus);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dataAdapterCivilStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        txtLastName = findViewById(R.id.txtLastName);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtMiddleName = findViewById(R.id.txtMiddleName);
        txtDateofBirth = findViewById(R.id.txtDateofBirth);
        txtPlaceofBirth = findViewById(R.id.txtPlaceofBirth);
        txtCitizenship = findViewById(R.id.txtCitizenship);
        txtOccupation = findViewById(R.id.txtOccupation);
        rbMale = findViewById(R.id.rbMale);
        rbFeMale = findViewById(R.id.rbFeMale);
        rbVoterYes = findViewById(R.id.rbVoterYes);
        rbVoeterNo = findViewById(R.id.rbVoterNo);
        spinner = findViewById(R.id.spinner);
        spinnerMaritalStatus = findViewById(R.id.spinnerMaritalStatus);
        txtUsername_reg = findViewById(R.id.txtUsername_reg);
        txtPassword_reg = findViewById(R.id.txtPassword_reg);


        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
//        spinnerMaritalStatus.setAdapter(dataAdapterCivilStatus);


        if (saveTag.equals(getResources().getString(R.string.edit))) {

            txtUsername_reg.setText(modelPerson.getUsername());
            txtPassword_reg.setText(modelPerson.getPassword());
            txtLastName.setText(modelPerson.getLastName());
            txtFirstName.setText(modelPerson.getFirstName());
            txtMiddleName.setText(modelPerson.getMiddleName());
            txtDateofBirth.setText(modelPerson.getDateofBirth());
            txtPlaceofBirth.setText(modelPerson.getPlaceofBirth());
            txtCitizenship.setText(modelPerson.getCitizenship());
            txtOccupation.setText(modelPerson.getOccupation());

            spinner.setSelection(dataAdapter.getPosition(modelPerson.getOther()));
//            spinnerMaritalStatus.setSelection(dataAdapterCivilStatus.getPosition(modelPerson.getCivilStatus()));
            String compareValue = modelPerson.getCivilStatus();
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.civil_status, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMaritalStatus.setAdapter(adapter);
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                spinnerMaritalStatus.setSelection(spinnerPosition);
            }

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

        if (saveTag.equals(getResources().getString(R.string.save))) {
            modelPerson = new ModelPerson();
        }


        modelPerson.setUsername(txtUsername_reg.getText().toString());
        modelPerson.setPassword(txtPassword_reg.getText().toString());
        modelPerson.setLastName(txtLastName.getText().toString());
        modelPerson.setFirstName(txtFirstName.getText().toString());
        modelPerson.setMiddleName(txtMiddleName.getText().toString());
        modelPerson.setCivilStatus(spinnerMaritalStatus.getSelectedItem().toString());
        modelPerson.setDateofBirth(txtDateofBirth.getText().toString());
        modelPerson.setPlaceofBirth(txtPlaceofBirth.getText().toString());
        modelPerson.setCitizenship(txtCitizenship.getText().toString());
        modelPerson.setOccupation(txtOccupation.getText().toString());
        modelPerson.setGender(getGender(rbMale, rbFeMale));
        modelPerson.setVoter(getYesNo(rbVoterYes, rbVoeterNo));
        modelPerson.setOther(spinner.getSelectedItem().toString());




        personController = new PersonController(modelPerson, getApplicationContext());
        if (!personController.isPersonalInfoNotEmpty()) {
            Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
            return;
        }


        //GO to next screen
        startActivity(new Intent(getApplicationContext(), AddressActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void onBackPressed() {


        if (saveTag.equals(String.valueOf(R.string.save))) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }


}
