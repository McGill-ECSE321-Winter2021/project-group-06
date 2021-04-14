package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OfferedServiceAction extends AppCompatActivity {

    private static final String TAG = "OfferedService";

    /**
     * create an instance in order to call the backend
     *
     * @param savedInstanceState
     */
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
                    /**
                     * correct response
                     * @param response
                     */
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
                    /**
                     * error response
                     * @param error
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("Error", error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);


    }

    /**
     * go back to main menu page
     *
     * @param view
     */
    public void returnToMain(View view) {
        //starts a new activity
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
