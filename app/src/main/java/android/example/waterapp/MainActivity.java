package android.example.waterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static final String USERNAME_KEY =
            "com.example.android.twoactivities.extra.USERNAME_KEY";
    public static final String PASSWORD_KEY =
            "com.example.android.twoactivities.extra.PASSWORD_KEY";
    private EditText mMainUsername;
    private EditText mMainPassword;
    //private RequestQueue requestQueue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();
    private static final String LOG_TAG = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%
        mMainUsername = findViewById(R.id.editText_username);
        mMainPassword = findViewById(R.id.editText_password);
        sharedResponse("");
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%

        final TextView resultTextView = (TextView) findViewById(R.id.textview_main);


//   %%%%%%%%%%%%%%% JSONARRAY GET REQUEST
        // Formulate the request and handle the response.

        try {
            final String URL = "http://10.0.2.2:8080/demo/all"; // il faut mettre 10.0.2.2 pour avoir localhost dans l'émulateur andoid : http://10.0.2.2:8080/demo/all

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    resultTextView.setText("Response : " + response.toString());
                   //Log.i(LOG_TAG, "coucou wtf: " + response.toString());
                    //Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                    sharedResponse(response.toString());

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }




/*//POST method
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("name", "envoi depuis appli");
            object.put("forename", "lululu");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        final String URL = "http://10.0.2.2:8080/demo/add";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //resultTextView.setText("String Response : " + response.toString());
                        Toast.makeText(getApplicationContext(), "Post request sent !", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error posting");
            }
        });
        VolleyController.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);*/



    }

//%%%%%%%%%%%%%%% JSONOBJECT GET REQUEST
/*                try {
            final String URL = "http://api.ipify.org/?format=json"; //https://api.ipify.org/?format=json

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
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
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
//%%%%%%%%%%%%%%% String GET REQUEST
/*              try {
            final String URL = "http://10.0.2.2:8080/demo/all"; //https://api.ipify.org/?format=json

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    resultTextView.setText("Response : " + response);
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


        // %%%%%%%%%%%%%// Post Request For JSONObject
        // ne fonctionne probablement pas... à voir avec les méthodes d'addition de patient quand on merge
       /* JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("parameter", "value");
            object.put("parameter", "value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        final String URL = "http://10.0.2.2:8080/demo/add";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultTextView.setText("String Response : " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error getting response");
            }
        });
        VolleyController.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    }
/* OPERATIONS AVEC LA REQUESTQUEUE
// Get a RequestQueue
    RequestQueue requestQueue = VolleyController.getInstance(this.getApplicationContext()).
            getRequestQueue();

// Add a request (in this example, called stringRequest) to your RequestQueue.
VolleyController.getInstance(this).addToRequestQueue(stringRequest);
*/

    public void sharedResponse(String response) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = m.edit();
        Log.i(LOG_TAG, "coucou00725: " + response);
        editor.putString("Response", response);
        editor.commit();
    }



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