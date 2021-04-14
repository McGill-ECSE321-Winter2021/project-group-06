package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Login extends AppCompatActivity {

    private String error = null;
    private Button button_login;
    private EditText usernameLogin;
    private EditText passwordLogin;


    private static final String TAG = "Login";
    long millis = System.currentTimeMillis();
    java.sql.Date today = new java.sql.Date(millis);


    /**
     * create an instance to link to the backend
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ListView customerLV;
        button_login = (Button) findViewById(R.id.button_login);
        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);

        String URL = "http://10.0.2.2:8080/loginCustomerAccount";


        button_login.setOnClickListener(
                new View.OnClickListener() {
                    /**
                     * login
                     * @param view
                     */
                    public void onClick(View view) {
                        android.util.Log.e("click", usernameLogin.getText().toString());

                        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.PUT,
                                URL + "/" + usernameLogin.getText().toString() + "/" + passwordLogin.getText().toString(),
                                null,
                                new Response.Listener<JSONObject>() {
                                    /**
                                     * correct response
                                     * @param response
                                     */
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        SingletonClass.getInstance().setCurrentUsername(usernameLogin.getText().toString());
                                        android.util.Log.e("LOGIN PAGE", usernameLogin.getText().toString());
                                        setContentView(R.layout.activity_customer_appointment);
                                        try {
                                            android.util.Log.e("data", response.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        android.util.Log.e("here", "String.valueOf(n)");
                                        JSONObject object = response;
                                        activateReminder();
                                    }
                                },


                                new Response.ErrorListener() {
                                    /**
                                     * error response
                                     * @param error
                                     */
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        android.util.Log.e("ERROR", error.toString());
                                    }
                                }
                        );

                        requestQueue.add(jsonObjectRequest);
                    }
                }
        );


    }

    /**
     * see reminder if there should be one
     */
    public void activateReminder() {

        String URL_GET_APT = "http://10.0.2.2:8080/getAppointmentByCustomer/" + usernameLogin.getText().toString();
        final JSONArray[] allAppointments = {new JSONArray()};

        RequestQueue requestQueueAPT = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL_GET_APT,
                null,
                new Response.Listener<JSONArray>() {
                    /**
                     * correct response
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONArray response) {
                        allAppointments[0] = response;
                        ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
                        Log.e("appointment1", allAppointments[0].toString());
                        for (int n = 0; n < allAppointments[0].length(); n++) {
                            try {
                                Log.e("n", String.valueOf(n));
                                JSONObject object = allAppointments[0].getJSONObject(n);

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                                Calendar c = Calendar.getInstance();
                                Calendar c1 = Calendar.getInstance();
                                try {
                                    java.util.Date tempDate = sdf.parse(object.getJSONObject("timeSlot").getString("endDate"));
                                    java.sql.Date appEndDate = new java.sql.Date(tempDate.getTime());
                                    Time appEndTime = Time.valueOf(object.getJSONObject("timeSlot").getString("endTime"));

                                    c.setTime(sdf.parse(appEndDate.toString()));

                                    String service = object.getJSONObject("offeredService").getString("name");
                                    int reminderDate = object.getJSONObject("offeredService").getInt("reminderDate");
                                    c.add(Calendar.DATE, reminderDate);
                                    c1.add(Calendar.DATE, reminderDate + 5);


                                    if (today.after(c.getTime()) && today.before(c1.getTime())) {
                                        startActivity(new Intent(Login.this, Reminder.class));
                                    } else {
                                        startActivity(new Intent(Login.this, MainMenu.class));
                                    }
                                } catch (ParseException excpt) {
                                    excpt.printStackTrace();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },

                new Response.ErrorListener() {
                    /**
                     * error response
                     * @param error
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("ERROR", error.toString());
                    }
                }
        );

        requestQueueAPT.add(jsonArrayRequest);

    }


}


