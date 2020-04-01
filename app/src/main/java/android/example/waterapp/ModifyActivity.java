package android.example.waterapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.example.waterapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class ModifyActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    public Patient patient;
    public final static String EXTRA_PATIENT = "com.example.mysampleapp.PATIENT";
    private static final String LOG_TAG = "test2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Modify the data");
        toolbar.setTitleTextColor(0XFFFFFFFF);


        Intent intent = getIntent();
        patient = intent.getParcelableExtra("com.example.mysampleapp.PATIENT");
        this.updateTextViewForename(patient.getForename());
        this.updateTextViewLastname(patient.getName());
//        this.updateTextViewDate(patient.getAge()); changer avec la date de naissance et pas l'age
        this.updateCheckGenre(patient.getGender());
        this.updateCheckMedic(patient.getMedication1(), patient.getMedication2(), patient.getMedication3());
        this.updateCheckDiseases(patient.getDisease1());
        this.updateTextViewRoom(String.valueOf(patient.getRoom()));
        this.updateTextViewSize(String.valueOf(patient.getSize()));
        this.updateTextViewWeight(String.valueOf(patient.getWeight()));


    }

    private void updateTextViewWeight(String w) {
        EditText editText = (EditText) findViewById(R.id.weight_text);
        editText.setText(w);
    }

    private void updateTextViewSize(String size) {
        EditText editText = (EditText) findViewById(R.id.size_text);
        editText.setText(size);
    }

    private void updateTextViewRoom(String room) {
        EditText editText = (EditText) findViewById(R.id.room_number_text);
        editText.setText(room);
    }

    private void updateCheckDiseases(Integer disease1) {
        if (disease1.equals((1))){
            CheckBox checkBox = (CheckBox) findViewById(R.id.disease1);
            checkBox.setChecked(Boolean.parseBoolean("True"));
        }
    }

    private void updateCheckMedic(Integer medication1, Integer medication2, Integer medication3) {
        if (medication1.equals(1)){
            CheckBox checkBox = (CheckBox) findViewById(R.id.med1);
            checkBox.setChecked(Boolean.parseBoolean("True"));
        }
        if (medication2.equals(1)){
            CheckBox checkBox = (CheckBox) findViewById(R.id.med2);
            checkBox.setChecked(Boolean.parseBoolean("True"));
        }
        if (medication3.equals(1)){
            CheckBox checkBox = (CheckBox) findViewById(R.id.med3);
            checkBox.setChecked(Boolean.parseBoolean("True"));
        }
    }

    private void updateCheckGenre(String toThis) {
        if (toThis.equals("M")){
            RadioButton radioButton = (RadioButton) findViewById(R.id.male);
            radioButton.setChecked(Boolean.parseBoolean("TRUE"));
        }
        if (toThis.equals("F")){
            RadioButton radioButton = (RadioButton) findViewById(R.id.female);
            radioButton.setChecked(Boolean.parseBoolean("TRUE"));
        }
        if (toThis.equals("Other")){
            RadioButton radioButton = (RadioButton) findViewById(R.id.other);
            radioButton.setChecked(Boolean.parseBoolean("TRUE"));
        }
    }

/*    private void updateTextViewDate(Integer toThis) {
        EditText textInputEditText = (EditText) findViewById(R.id.date);
        textInputEditText.setText(toThis);
    }*/

    private void updateTextViewLastname(String toThis) {
        TextInputEditText textInputEditText = (TextInputEditText) findViewById(R.id.lastname_text);
        textInputEditText.setText(toThis);
    }

    private void updateTextViewForename(String toThis) {
        TextInputEditText textInputEditText = (TextInputEditText) findViewById(R.id.forename_text) ;
        textInputEditText.setText(toThis);
    }

    public void launchPatientActivityFromModif(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        Log.i(LOG_TAG, "allez " + patient.getGender() );
        intent.putExtra(EXTRA_PATIENT, patient);
        startActivity(intent);
    }

}