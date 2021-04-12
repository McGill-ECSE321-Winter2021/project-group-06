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


public class BusinessInfoRequestObject extends AppCompatActivity {

    ListView businessInfoLV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_info);

        businessInfoLV = (ListView) findViewById(R.id.businessInfoList);
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
                        ArrayList<BusinessInfo> businessInfoList = new ArrayList<BusinessInfo>();
                        android.util.Log.e("businessInfo1",allBusinessInfo[0].toString());
                        for (int n = 0; n < allBusinessInfo[0].length(); n++){
                            try {
                                android.util.Log.e("n",String.valueOf(n));
                                JSONObject object = allBusinessInfo[0].getJSONObject(n);

                                //check the string values, written blindly
                                String businessName = object.getJSONObject("businessInformation").getString("name");
                                String businessAddress = object.getJSONObject("businessInformation").getString("address");
                                String businessPhoneNumber = object.getJSONObject("businessInformation").getString("phoneNumber");
                                String businessEmail = object.getJSONObject("businessInformation").getString("email");
                                BusinessInfo businessInfo = new BusinessInfo(businessName,businessAddress,businessPhoneNumber,businessEmail);
                                businessInfoList.add(businessInfo);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        BusinessInfoListAdapter businessInfoListAdapter = new BusinessInfoListAdapter(context, R.layout.adapter_appointment_layout, businessInfoList);
                        businessInfoLV.setAdapter(businessInfoListAdapter);

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
        // Do something in response to button click
    }



}