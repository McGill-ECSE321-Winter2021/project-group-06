package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ReceiptAction extends AppCompatActivity {
    private static final String TAG = "Receipt";
    long millis=System.currentTimeMillis();
    java.sql.Date today=new java.sql.Date(millis);
    java.sql.Time nowTime = new java.sql.Time(millis);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_view);

        ListView receiptLV = (ListView) findViewById(R.id.receiptListView);
        String URL = "http://10.0.2.2:8080/getAppointmentByCustomer/" + SingletonClass.getInstance().getCurrentUsername(); 
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
                        ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
                        Log.e("appointment1",allAppointments[0].toString());
                        for (int n = 0; n < allAppointments[0].length(); n++){
                            try {
                                Log.e("n",String.valueOf(n));
                                JSONObject object = allAppointments[0].getJSONObject(n);

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                                try {
                                    java.util.Date tempDate = sdf.parse(object.getJSONObject("timeSlot").getString("endDate"));
                                    java.sql.Date appEndDate = new java.sql.Date(tempDate.getTime());
                                    Time appEndTime = Time.valueOf(object.getJSONObject("timeSlot").getString("endTime"));

                                    if (today.after(appEndDate) || (today.equals(appEndDate) && nowTime.after(appEndTime))){
                                        int id = object.getInt("appointmentId");

                                        String service = object.getJSONObject("offeredService").getString("name");
                                        double price = object.getJSONObject("offeredService").getDouble("price");
                                        String startTime = object.getJSONObject("timeSlot").getString("startTime");
                                        String endTime = object.getJSONObject("timeSlot").getString("endTime");
                                        String startDate = object.getJSONObject("timeSlot").getString("startDate");
                                        String endDate = object.getJSONObject("timeSlot").getString("endDate");

                                        Receipt receipt = new Receipt(id,startTime,endTime,startDate,endDate,service,price);
                                        receiptList.add(receipt);
                                    }
                                }catch (ParseException excpt){
                                    excpt.printStackTrace();
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ReceiptAdapter receiptAdapter = new ReceiptAdapter(context, R.layout.activity_receipt, receiptList);
                        receiptLV.setAdapter(receiptAdapter);

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
}
