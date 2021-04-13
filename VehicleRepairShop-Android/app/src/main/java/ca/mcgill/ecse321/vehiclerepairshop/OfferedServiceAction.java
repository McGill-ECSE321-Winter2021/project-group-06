package ca.mcgill.ecse321.vehiclerepairshop;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ListView;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfferedServiceAction extends AppCompatActivity {

    private static final String TAG = "OfferedService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offered_service_view);

        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);
        String URL = "http://10.0.2.2:8080/getAllOfferedServices";
        final JSONArray[] allOfferedServices = {new JSONArray()};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        allOfferedServices[0] = response;
                        ArrayList<OfferedService> offeredServiceArrayList = new ArrayList<OfferedService>();
                        android.util.Log.e("offeredService1", allOfferedServices[0].toString());
                        for (int n = 0; n < allOfferedServices[0].length(); n++) {
                            try {
                                android.util.Log.e("n", String.valueOf(n));
                                JSONObject object = allOfferedServices[0].getJSONObject(n);
                                String id = object.getString("offeredServiceId");
                                String name = object.getString("name");
                                int duration = object.getInt("duration");
                                double price = object.getDouble("price");
                                OfferedService offeredService = new OfferedService(id, name, duration, price);
                                offeredServiceArrayList.add(offeredService);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        OfferedServiceAdapter serviceAdapter = new OfferedServiceAdapter(context, R.layout.activity_offered_service, offeredServiceArrayList);
                        mListView.setAdapter(serviceAdapter);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("Error",error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

//                //Create the offeredService Objects
//                OfferedService service1 = new OfferedService("service1", "wash", 10, 9.99);
//        OfferedService service2 = new OfferedService("service2", "wash2", 10, 9.99);
//        OfferedService service3 = new OfferedService("service3", "wash3", 10, 9.99);
//        OfferedService service4 = new OfferedService("service4", "wash4", 10, 9.99);
//        OfferedService service5 = new OfferedService("service5", "wash5", 10, 9.99);
//        OfferedService service6 = new OfferedService("service6", "wash6", 10, 9.99);
//
//
//        //Add the offeredService objects to an ArrayList
//        ArrayList<OfferedService> offeredServiceArrayList = new ArrayList<>();
//        offeredServiceArrayList.add(service1);
//        offeredServiceArrayList.add(service2);
//        offeredServiceArrayList.add(service3);
//        offeredServiceArrayList.add(service4);
//        offeredServiceArrayList.add(service5);
//        offeredServiceArrayList.add(service6);
//
//        System.out.println(offeredServiceArrayList);
//
//        OfferedServiceAdapter serviceAdapter = new OfferedServiceAdapter(this, R.layout.activity_offered_service, offeredServiceArrayList);
//        mListView.setAdapter(serviceAdapter);

    }
    public void returnToMain(View view) {
        //starts a new activity
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
