package android.example.waterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.example.waterapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;


public class ModifyActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Modify the data");
        toolbar.setTitleTextColor(0XFFFFFFFF);




    }

    public void launchPatientActivity(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

}