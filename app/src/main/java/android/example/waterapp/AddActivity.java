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


public class AddActivity extends AppCompatActivity {

    public Date currentTime = Calendar.getInstance().getTime();
    private EditText mlastname;
    private EditText mforename;
    public static final String EXTRA_MESSAGE =
            "com.example.android.WaterApp.extra.MESSAGE";
    public static final String EXTRA_MESSAGE2 =
            "com.example.android.WaterApp.extra.MESSAGE2";
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_a_new_patient);
        toolbar.setTitleTextColor(0XFFFFFFFF);
        mlastname= findViewById(R.id.lastname_text);
        mforename = findViewById(R.id.forename_text);



    }

    public void launchOverviewActivity(View view) {
        Log.d("message", "Next activity launched!");
        Intent intent = new Intent(this, OverviewActivity.class);
        intent.putExtra(EXTRA_MESSAGE, mlastname.getText().toString());
        intent.putExtra(EXTRA_MESSAGE2, mforename.getText().toString());
        startActivityForResult(intent, TEXT_REQUEST);
    }

}