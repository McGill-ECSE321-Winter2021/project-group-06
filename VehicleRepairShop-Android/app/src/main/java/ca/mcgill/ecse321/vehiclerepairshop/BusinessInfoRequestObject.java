package ca.mcgill.ecse321.vehiclerepairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BusinessInfoRequestObject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_info);

        String URL = "http://10.0.2.2:8080/getAllBusinessInformation/";
        final JSONArray[] allBusinessInfo = {new JSONArray()};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        allBusinessInfo[0] = response;
                        android.util.Log.e("businessInfo1",allBusinessInfo[0].toString());
                            try {
                                JSONObject object = allBusinessInfo[0].getJSONObject(0);

                                String businessName = object.getString("name");
                                String businessAddress = object.getString("address");
                                String businessPhoneNumber = object.getString("phoneNumber");
                                String businessEmail = object.getString("emailAddress");

                                TextView tvName = (TextView) findViewById(R.id.business_name);
                                TextView tvAddress = (TextView) findViewById(R.id.business_address);
                                TextView tvEmail = (TextView) findViewById(R.id.business_email);
                                TextView tvPhone = (TextView) findViewById(R.id.business_phone_number);
                                tvName.setText(businessName);
                                tvAddress.setText(businessAddress);
                                tvPhone.setText(businessPhoneNumber);
                                tvEmail.setText(businessEmail);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("Business Info ERROR",error.toString());
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