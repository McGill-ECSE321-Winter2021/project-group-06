package ca.mcgill.ecse321.vehiclerepairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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


public class CarRequestObject extends AppCompatActivity {

    ListView CarLV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car);

        CarLV = (ListView) findViewById(R.id.carList);
        String URL = "http://10.0.2.2:8080/getAllBusinessInformation/";
        final JSONArray[] allCars = {new JSONArray()};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        allCars[0] = response;
                        ArrayList<Car> carList = new ArrayList<Car>();
                        android.util.Log.e("car1",allCars[0].toString());
                        for (int n = 0; n < allCars[0].length(); n++){
                            try {
                                android.util.Log.e("n",String.valueOf(n));
                                JSONObject object = allCars[0].getJSONObject(n);

                                //check the string values, written blindly
                                String licensePlate = object.getJSONObject("car").getString("licensePlate");
                                String owner = object.getJSONObject("car").getString("owner");
                                String year = object.getJSONObject("car").getString("year");
                                String motorType = object.getJSONObject("car").getString("motorType");
                                Car car = new Car(licensePlate,owner,year,motorType);
                                carList.add(car);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        CarListAdapter carListAdapter = new CarListAdapter(context, R.layout.adapter_appointment_layout, carList);
                        CarLV.setAdapter(carListAdapter);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("Car ERROR",error.toString());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);


    }


}