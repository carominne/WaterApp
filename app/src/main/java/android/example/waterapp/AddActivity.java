package android.example.waterapp;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddActivity extends AppCompatActivity {

    private static final int ID1 = 1000;
    private static final int ID2 = 1001;
    private static final int ID3 = 1002;
    public Date currentTime = Calendar.getInstance().getTime();
    private EditText mlastname;
    private EditText mforename;
    private EditText mroom;
    private EditText mdehdration;
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
        mroom = findViewById(R.id.room_number_text);
        mweight = findViewById(R.id.weight_text);
        mheight = findViewById(R.id.size_text);
        mgender = findViewById(R.id.radioGroup);
        mmale = findViewById(R.id.male);
        mmale.setId(ID1);
        mfemale = findViewById(R.id.female);
        mfemale.setId(ID2);
/*        mOther = findViewById(R.id.other);
        mOther.setId(ID3);*/
        mbirthday = findViewById(R.id.date);
        mdisease1 = findViewById(R.id.disease1);
        mmedication1 = findViewById(R.id.med1);
        mmedication2 = findViewById(R.id.med2);
        mmedication3 = findViewById(R.id.med3);
        m1 = 0;
        m2 = 0;
        m3 = 0;
        d1 = 0;

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
            postRequest();
            Toast.makeText(this, "Patient added to the database", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, OverviewActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mlastname.getText().toString());
            intent.putExtra(EXTRA_MESSAGE2, mforename.getText().toString());
            intent.putExtra("var", 4);
            AddActivity.this.startActivity(intent);
            AddActivity.this.finish();
        }
    }

    public void postRequest(){

        //%%%%%%%%%%%%%%%%%%% POST request

       JSONObject postObject = new JSONObject();
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
            Log.d("coucou", "coucou2" + getSelectedGender(mgender));
            postObject.put("room", mroom.getText());
            postObject.put("name", mlastname.getText().toString());
            postObject.put("forename", mforename.getText().toString());
            postObject.put("dehydrationState", 0);
            postObject.put("heartbeat", 0);
            postObject.put("spo2", 0);
            postObject.put("gender", getSelectedGender(mgender));
            postObject.put("birthday", mbirthday.getText().toString());
            postObject.put("weight", mweight.getText());
            postObject.put("height", mheight.getText());
            postObject.put("medication1", m1);
            postObject.put("medication2", m2);
            postObject.put("medication3", m3);
            postObject.put("disease1", d1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String postURL = "http://10.0.2.2:8080/demo/add";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, postURL, postObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //resultTextView.setText("String Response : " + response.toString());
                        Toast.makeText(getApplicationContext(), "Post request sent !", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //resultTextView.setText("Error POSTing");
                Toast.makeText(getApplicationContext(), "FATAL ERROR YOU DUMB BITCH", Toast.LENGTH_LONG).show();
            }
        });
        VolleyController.getInstance(getApplicationContext()).addToRequestQueue(postRequest);
    }

    private String getSelectedGender(RadioGroup gender) {
        Integer i = gender.getCheckedRadioButtonId();
        Log.d("coucou", "coucou" + i);
        if (i==ID1){
            return "M";
        }
        if (i==ID2){
            return "F";
        }
/*        if (i==ID3){
            return "Other";
        }*/
        return null;
    }


    public void launchOverviewActivity_Cancel(View view) {


            Intent intent = new Intent(this, OverviewActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mlastname.getText().toString());
            intent.putExtra(EXTRA_MESSAGE2, mforename.getText().toString());
            startActivityForResult(intent, TEXT_REQUEST);
        }
    }

