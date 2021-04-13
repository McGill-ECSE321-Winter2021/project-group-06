package ca.mcgill.ecse321.vehiclerepairshop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Reminder extends AppCompatActivity {
    private static final String TAG = "Reminder";
    long millis = System.currentTimeMillis();
    java.sql.Date today = new java.sql.Date(millis);
    java.sql.Time nowTime = new java.sql.Time(millis);
    private TextView offeredService_reminder;
    private Button button_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        offeredService_reminder = (TextView) findViewById(R.id.offeredService_reminder);
        button_ok = (Button) findViewById(R.id.button_ok);

        String URL_GET_APT = "http://10.0.2.2:8080/getAppointmentByCustomer/" + SingletonClass.getInstance().getCurrentUsername() ;
        final JSONArray[] allAppointments = {new JSONArray()};

        RequestQueue requestQueueAPT = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL_GET_APT,
                null,
                new Response.Listener<JSONArray>() {
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
                                        offeredService_reminder.setText(service);
                                        return;
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
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("ERROR", error.toString());
                    }
                }
        );

        requestQueueAPT.add(jsonArrayRequest);
        button_ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Reminder.this, MainMenu.class));

                    }
                }
        );

    }

}


