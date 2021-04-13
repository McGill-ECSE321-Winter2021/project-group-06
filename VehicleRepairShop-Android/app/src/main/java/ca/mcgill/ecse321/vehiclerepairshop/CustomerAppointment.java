package ca.mcgill.ecse321.vehiclerepairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class CustomerAppointment extends AppCompatActivity {

    ListView appointmentLV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointment);

        appointmentLV = (ListView) findViewById(R.id.appointmentList);
        String URL = "http://10.0.2.2:8080/getAppointmentByCustomer/cust1";
        final JSONArray[] allAppointments = {new JSONArray()};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        allAppointments[0] = response;
                        ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
                        android.util.Log.e("appointment1",allAppointments[0].toString());
                        for (int n = 0; n < allAppointments[0].length(); n++){
                            try {
                                android.util.Log.e("n",String.valueOf(n));
                                JSONObject object = allAppointments[0].getJSONObject(n);
                                String service = object.getJSONObject("offeredService").getString("name");
                                String startTime = object.getJSONObject("timeSlot").getString("startTime");
                                String endTime = object.getJSONObject("timeSlot").getString("endTime");
                                String startDate = object.getJSONObject("timeSlot").getString("startDate");
                                String endDate = object.getJSONObject("timeSlot").getString("endDate");
                                Appointment appointment = new Appointment(startTime,endTime,startDate,endDate,service);
                                appointmentList.add(appointment);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        AppointmentListAdapter appointmentListAdaptor = new AppointmentListAdapter(context, R.layout.adapter_appointment_layout, appointmentList);
                        appointmentLV.setAdapter(appointmentListAdaptor);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("ERROR",error.toString());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);


    }
    public void returnToMain(View view) {
        //starts a new activity
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }


}