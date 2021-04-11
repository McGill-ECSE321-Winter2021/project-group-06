package ca.mcgill.ecse321.vehiclerepairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import java.util.Arrays;

public class CustomerAppointment extends AppCompatActivity {

    ListView appointmentLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointment);

        appointmentLV = (ListView) findViewById(R.id.appointmentList);
        String URL = "http://10.0.2.2:8080/getAppointmentByCustomer/cust1";
        String values[] = {"c","c++","Javaaaaa"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(values));
        appointmentLV.setAdapter(arrayAdapter);
        android.util.Log.e("King","King");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        android.util.Log.e("responseKing","response");
                        android.util.Log.e("appointment",response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("responseKingError","responseError");
                        android.util.Log.e("ERROR",error.toString());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }


//    public void refreshList(View view) {
//        refreshAppointmentList(personAdapter ,personNames, "people");
//    }
//
//    private void refreshAppointmentList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName) {
//        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                names.clear();
//                names.add("Please select...");
//                for( int i = 0; i < response.length(); i++){
//                    try {
//                        names.add(response.getJSONObject(i).getString("name"));
//                    } catch (Exception e) {
//                        error += e.getMessage();
//                    }
//                    refreshErrorMessage();
//                }
//                adapter.notifyDataSetChanged();
//            }
//

}