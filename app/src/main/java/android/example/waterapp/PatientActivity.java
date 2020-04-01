package android.example.waterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.example.waterapp.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PatientActivity extends AppCompatActivity {

    public Patient patient;
    public final static String EXTRA_PATIENT = "com.example.mysampleapp.PATIENT";
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "test2";
    private String medication = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent intent = getIntent();
        patient = intent.getParcelableExtra("com.example.mysampleapp.PATIENT");
        this.updateTextViewName(patient.getForename() + " "+ patient.getName());
        this.updateTextViewAge(String.valueOf(patient.getAge()));
        this.updateTextViewRoom(String.valueOf(patient.getRoom()));
        this.updateTextViewGender(patient.getGender());
        this.updateTextViewDehydration(String.valueOf(patient.getDehydrationState()));
        this.updateTextViewDisease(String.valueOf(patient.getDisease1()));
        this.updateTextViewMedication(String.valueOf(patient.getMedication1()), String.valueOf(patient.getMedication2()), String.valueOf(patient.getMedication3()));
        Log.i(LOG_TAG, "coucou " + patient.getGender() + " age "+ patient.getAge() + " id "+ patient.getId() + " room "+ patient.getRoom()+ " name"+ patient.getName() + " forename" + patient.getForename() + " deh"+ patient.getDehydrationState() + "med "+ patient.getMedication1() + " disease "+ patient.getDisease1());
    }

    private void updateTextViewMedication(String med1, String med2, String med3) {
        TextView textView = (TextView) findViewById(R.id.patient_medication);
        Boolean x = med2.equals("1");
        Log.i(LOG_TAG, "coucou" + x);
        if (med1.equals("1")){
            medication += "Medication 1\n";
        }
        if (med2.equals("1")){
            Log.i(LOG_TAG, med2);
            medication += "Medication 2\n";
        }
        if (med3.equals("1")){
            medication += "Medication 3\n";
        }
        if (med2.equals("0") && med1.equals("0") && med3.equals("0")){
            medication += "No medication taken\n";
        }
        textView.setText(medication);
    }

    private void updateTextViewDisease(String dis1) {
        TextView textView = (TextView) findViewById(R.id.patient_background);
        String disease = "";

        if (dis1.equals("1")){
            disease += "Renal Failure\n";
        }
        if (dis1.equals("0")){
            disease += "No particular disease\n";
        }
        textView.setText(disease);
    }

    public void updateTextViewAge(String toThis) {
        TextView textView = (TextView) findViewById(R.id.patient_age);
        textView.setText(toThis);
    }

    public void updateTextViewName(String toThis) {
        TextView textView = (TextView) findViewById(R.id.patientName);
        textView.setText(toThis);
    }

    public void updateTextViewGender(String toThis) {
        TextView textView = (TextView) findViewById(R.id.patient_gender);
        textView.setText(toThis);
    }


    public void updateTextViewRoom(String toThis) {
        TextView textView = (TextView) findViewById(R.id.patient_room_number);
        textView.setText(toThis);
    }

    public void updateTextViewDehydration(String toThis) {
        TextView textView = (TextView) findViewById(R.id.patient_dehydration_level);
        if (toThis.equals("0")) {
            textView.setText("Low");
        }
        if (toThis.equals("1")) {
            textView.setText("Medium");
        }
        if (toThis.equals("2")) {
            textView.setText("High");
        }

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(true);
        menu.getItem(4).setVisible(false);
        menu.getItem(5).setVisible(true);
        menu.getItem(6).setVisible(true);
        menu.getItem(7).setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_modifiy:
                Intent intent = new Intent(this, ModifyActivity.class);
                intent.putExtra(EXTRA_PATIENT, patient);
                Log.i(LOG_TAG, "STP " + patient.getGender() );
                startActivity(intent);
                break;
            case R.id.action_stat:
                Toast.makeText(this, "Need activity for statistics", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_back:
                Intent intent2 = new Intent(this, OverviewActivity.class);
                startActivityForResult(intent2, TEXT_REQUEST);
                break;
            case R.id.action_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you really want to delete this patient?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //                ajouter la requete vers la vbb
                                Toast.makeText(PatientActivity.this, "Patient deleted", Toast.LENGTH_LONG).show();
                                Intent mainIntent;
                                mainIntent = new Intent(PatientActivity.this, OverviewActivity.class);
                                PatientActivity.this.startActivity(mainIntent);
                                PatientActivity.this.finish();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

        }
        return true;
    }
}