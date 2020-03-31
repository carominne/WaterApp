package android.example.waterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.example.waterapp.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PatientActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "test2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent intent = getIntent();
        Patient patient = intent.getParcelableExtra("com.example.mysampleapp.PATIENT");
        this.updateTextViewName(patient.getForename() + " "+ patient.getName());
        this.updateTextViewAge(String.valueOf(patient.getAge()));
        this.updateTextViewRoom(String.valueOf(patient.getRoom()));
        this.updateTextViewGender(patient.getGender());
        this.updateTextViewDehydration(String.valueOf(patient.getDehydrationState()));
                Log.i(LOG_TAG, "coucou " + patient.getGender() + " age "+ patient.getAge() + " id "+ patient.getId() + " room "+ patient.getRoom()+ " name"+ patient.getName() + " forename" + patient.getForename() + " deh"+ patient.getDehydrationState() + "med "+ patient.getMedication1() + " disease "+ patient.getDisease1());
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
        if (toThis == "0") {
            textView.setText("Low");
        }
        if (toThis == "1") {
            textView.setText("Medium");
        }
        if (toThis == "2") {
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
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_modifiy:
                Intent intent = new Intent(this, ModifyActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);
                break;
            case R.id.action_stat:
                Toast.makeText(this, "Need activity for statistics", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_back:
                Intent intent2 = new Intent(this, OverviewActivity.class);
                startActivityForResult(intent2, TEXT_REQUEST);
                break;
            case R.id.action_delete:
//                ajouter la requete vers la vbb
                Toast.makeText(this, "Patient deleted", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(this, OverviewActivity.class);
                startActivityForResult(intent3, TEXT_REQUEST);
        }
        return true;
    }
}