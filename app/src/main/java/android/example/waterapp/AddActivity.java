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
import android.widget.RadioGroup;
import android.widget.Toast;

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
    private boolean False;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_a_new_patient);
        toolbar.setTitleTextColor(0XFFFFFFFF);
        mlastname = findViewById(R.id.lastname_text);
        mforename = findViewById(R.id.forename_text);


    }

    public void launchOverviewActivity_Add(View view) {


        EditText last = (EditText) findViewById(R.id.lastname_text);
        if (last.getText().toString().isEmpty()) {
            last.setError("Lastname is required");
        }
        EditText fore = (EditText) findViewById(R.id.forename_text);
        if (fore.getText().toString().isEmpty()) {
            fore.setError("Forename is required");
        }
        EditText date = (EditText) findViewById(R.id.date);
        if (date.getText().toString().isEmpty()) {
            date.setError("Date of birth is required");
        }
        RadioGroup gen = (RadioGroup) findViewById(R.id.radioGroup);
        if (gen.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_LONG).show();
        }
        EditText room = (EditText) findViewById(R.id.room_number_text);
        if (room.getText().toString().isEmpty()) {
            room.setError("Room number is required");
        }
        EditText size = (EditText) findViewById(R.id.size_text);
        if (size.getText().toString().isEmpty()) {
            size.setError("Size is required");
        }
        EditText weight = (EditText) findViewById(R.id.weight_text);
        if (weight.getText().toString().isEmpty()) {
            weight.setError("Size is required");
        }
        if (last.getText().toString().isEmpty()== False  && fore.getText().toString().isEmpty()==False && date.getText().toString().isEmpty()==False
                && gen.getCheckedRadioButtonId() != -1 && room.getText().toString().isEmpty()== False && weight.getText().toString().isEmpty()== False

        )
        {
            Toast.makeText(this, "Patient added to the database", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, OverviewActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mlastname.getText().toString());
            intent.putExtra(EXTRA_MESSAGE2, mforename.getText().toString());
            startActivityForResult(intent, TEXT_REQUEST);
        }
    }


    public void launchOverviewActivity_Cancel(View view) {


            Intent intent = new Intent(this, OverviewActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mlastname.getText().toString());
            intent.putExtra(EXTRA_MESSAGE2, mforename.getText().toString());
            startActivityForResult(intent, TEXT_REQUEST);
        }
    }

