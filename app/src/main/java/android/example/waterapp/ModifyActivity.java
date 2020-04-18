package android.example.waterapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.example.waterapp.R;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class ModifyActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private static final int ID1 = 1000;
    private static final int ID2 = 1001;
    private static final int ID3 = 1002;
    public Patient patient;
    public final static String EXTRA_PATIENT = "com.example.mysampleapp.PATIENT";
    private static final String LOG_TAG = "test2";
    private EditText mlastname;
    private EditText mforename;
    private EditText mroom;
    private RadioGroup mgender;
    private RadioButton mmale;
    private RadioButton mfemale;
    private RadioButton mOther;

    private EditText mbirthday;
    private CheckBox mdisease1;
    private CheckBox mmedication1;
    private CheckBox mmedication2;
    private CheckBox mmedication3;
    private Integer m1 ;
    private Integer m2 ;
    private Integer m3 ;
    private Integer d1 ;
    private EditText mweight;
    private EditText mheight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Modify the data");
        toolbar.setTitleTextColor(0XFFFFFFFF);


        Intent intent = getIntent();
        patient = intent.getParcelableExtra("com.example.myexampleapp.PATIENT");
        this.updateTextViewForename(patient.getForename());
        this.updateTextViewLastname(patient.getName());
        this.updateTextViewDate(patient.getBirthday());
        this.updateCheckGenre(patient.getGender());
        this.updateCheckMedic(patient.getMedication1(), patient.getMedication2(), patient.getMedication3());
        this.updateCheckDiseases(patient.getDisease1());
        this.updateTextViewRoom(String.valueOf(patient.getRoom()));
        this.updateTextViewSize(String.valueOf(patient.getHeight()));
        this.updateTextViewWeight(String.valueOf(patient.getWeight()));
        mheight = (EditText) findViewById(R.id.size_text);
        mweight = (EditText) findViewById(R.id.weight_text);
        mbirthday = (EditText) findViewById(R.id.date);
        mlastname = (EditText) findViewById(R.id.lastname_text);
        mforename = (EditText) findViewById(R.id.forename_text);
        mroom = (EditText) findViewById(R.id.room_number_text);
        mgender = findViewById(R.id.radioGroup);
        mmale = findViewById(R.id.male);
        mfemale = findViewById(R.id.female);
    //    mOther = findViewById(R.id.other);
        mdisease1 = findViewById(R.id.disease1);
        mmedication1 = findViewById(R.id.med1);
        mmedication2 = findViewById(R.id.med2);
        mmedication3 = findViewById(R.id.med3);
        m1 = patient.getMedication1();
        m2 = patient.getMedication2();
        m3 = patient.getMedication3();
        d1 = patient.getDisease1();
    }

    private Integer medCheck(CheckBox m) {

        if (m.isChecked()){
            return 1;
        }
        else{
            return 0;
        }
    }

    private Integer disCheck(CheckBox d) {

        if (d.isChecked()){
            return 1;
        }
        else{
            return 0;
        }
    }
    private String getSelectedGender(RadioGroup gender) {
        switch (gender.getCheckedRadioButtonId()){
            case R.id.male:
                return "M";
            case  R.id.female:
                return "F";
/*            case  R.id.other:
                return "Other";*/
        }
        return null;

        /*Integer i = gender.getCheckedRadioButtonId();
        Log.d("coucou", "coucou" + i);
        if (i==ID1){
            return "M";
        }
        if (i==ID2){
            return "F";
        }
        if (i==ID3){
            return "Other";
        }
        return null;*/
    }

    private void updateTextViewDate(String birthday) {
        EditText editText = (EditText) findViewById(R.id.date);
        editText.setText(birthday);
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
       /* if (toThis.equals("Other")){
            RadioButton radioButton = (RadioButton) findViewById(R.id.other);
            radioButton.setChecked(Boolean.parseBoolean("TRUE"));
        }*/
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
        putRequest();
        intent.putExtra("id", patient.getId());
        intent.putExtra("button", patient.getButton());
        //intent.putExtra(EXTRA_PATIENT, patient);
        startActivity(intent);

    }

    public void launchPatientActivityFromModifCancel(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        Log.i(LOG_TAG, "allez " + patient.getGender() );
        intent.putExtra("id", patient.getId());
        intent.putExtra("button", patient.getButton());
        //intent.putExtra(EXTRA_PATIENT, patient);
        startActivity(intent);

    }

    private void putRequest() {

        JSONObject putObject = new JSONObject();
        try {
            //input your API parameters
            if (mmedication1!= null){
                m1 = medCheck(mmedication1);
            }
            if (mmedication2!= null){
                m2 = medCheck(mmedication2);
            }
            if (mmedication3!= null){
                m3 = medCheck(mmedication3);
            }

            if (mdisease1 != null){
                d1 = disCheck(mdisease1);
            }
            mmale.setId(ID1);
            mfemale.setId(ID2);
        //    mOther.setId(ID3);


            putObject.put("room", mroom.getText());
            putObject.put("name", mlastname.getText().toString());
            putObject.put("forename", mforename.getText().toString());
            putObject.put("gender", getSelectedGender(mgender));
            putObject.put("birthday", mbirthday.getText().toString());
            putObject.put("weight", mweight.getText().toString());
            putObject.put("height", mheight.getText().toString());
            putObject.put("medication1", m1);
            putObject.put("medication2", m2);
            
            putObject.put("medication3", m3);
            putObject.put("disease1", d1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String putURL = "http://10.0.2.2:8080/demo/patientAll/" + patient.getId();
        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, putURL, putObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //resultTextView.setText("String Response : " + response.toString());
                        Toast.makeText(getApplicationContext(), "Patient updated !", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR FATAL YOU DUMB BITCH", Toast.LENGTH_LONG).show();
                Log.d("error", "error " + error);
                //resultTextView.setText("Error PUTing");
            }
        });
        VolleyController.getInstance(getApplicationContext()).addToRequestQueue(putRequest);
    }

}