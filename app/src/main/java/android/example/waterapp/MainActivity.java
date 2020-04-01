package android.example.waterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static final String USERNAME_KEY =
            "com.example.android.twoactivities.extra.USERNAME_KEY";
    public static final String PASSWORD_KEY =
            "com.example.android.twoactivities.extra.PASSWORD_KEY";
    private EditText mMainUsername;
    private EditText mMainPassword;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%
        mMainUsername = findViewById(R.id.editText_username);
        mMainPassword = findViewById(R.id.editText_password);


        //%%%%%%%%%%%%%%%%%%%%%%%%%%%
        final TextView resultTextView = (TextView) findViewById(R.id.textview_main);
        // Instantiate the RequestQueue.

        //RequestQueue requestQueue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Formulate the request and handle the response.
        try {
            String url = "http://api.ipify.org/?format=json";
            //JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    resultTextView.setText("Response : " + response.toString());
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*        String url = "https://api.ipify.org/?format=json";//%%%%%%%%%%%%%%%%%%% mettre le lien vers la BDD
        JSONObject object = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Response: " + response.toString());
                        Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Response:failed ");
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });*/

        // Access the RequestQueue through your singleton class.
        //VolleyController.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        //requestQueue.add(jsonObjectRequest);
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%

/* OPERATIONS AVEC LA REQUESTQUEUE
// Get a RequestQueue
    RequestQueue queue = VolleyController.getInstance(this.getApplicationContext()).
            getRequestQueue();

// Add a request (in this example, called stringRequest) to your RequestQueue.
VolleyController.getInstance(this).addToRequestQueue(stringRequest);
*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public void verifycredentials(View view) {
        // insérer ici le code qui sert à vérifier les données d'identification entrées par l'utilisateur
        boolean credentials;
        credentials = false;
        String username = mMainUsername.getText().toString();
        String password = mMainPassword.getText().toString();

        if(true){
            credentials=true; // vérifications des données, si c'est juste, on met credentials à 1
        }
        if (credentials == true){
            Intent intent = new Intent(this, OverviewActivity.class);
            startActivity(intent);
        }
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%

}