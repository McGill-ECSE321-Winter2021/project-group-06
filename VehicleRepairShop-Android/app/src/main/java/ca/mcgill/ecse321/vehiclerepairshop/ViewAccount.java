package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ViewAccount extends AppCompatActivity {

    private String error = null;
    private Button button_logout;
    private Button button_manage;
    private Button button_back;
    private TextView usernameInput;
    private TextView nameInput;


    /**
     * create an instance in order to call the backend
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_account);

        button_back = (Button) findViewById(R.id.button_back);
        button_logout = (Button) findViewById(R.id.button_logout);
        button_manage = (Button) findViewById(R.id.button_manage);
        usernameInput = (TextView) findViewById(R.id.usernameInput);
        nameInput = (TextView) findViewById(R.id.nameInput);

        String URL_GET_CUSTOMER = "http://10.0.2.2:8080/getCustomerAccountByUsername";
        String URL_LOGOUT = "http://10.0.2.2:8080/logoutCustomerAccount";

        RequestQueue requestQueue = Volley.newRequestQueue(ViewAccount.this);
        android.util.Log.e("getCurrentUser", "--------------------------------------" + SingletonClass.getInstance().getCurrentUsername());
        JsonObjectRequest jsonObjectRequestGet = new JsonObjectRequest(
                Request.Method.GET,
                URL_GET_CUSTOMER + "/" + SingletonClass.getInstance().getCurrentUsername(),
                null,
                new Response.Listener<JSONObject>() {
                    /**
                     * correct response
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        android.util.Log.e("GET  PAGE", usernameInput.getText().toString());
                        try {
                            android.util.Log.e("data", response.getString("name"));
                            usernameInput.setText(response.getString("username"));
                            nameInput.setText(response.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        android.util.Log.e("here", "String.valueOf(n)");
                        JSONObject object = response;
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
        requestQueue.add(jsonObjectRequestGet);


        button_logout.setOnClickListener(
                new View.OnClickListener() {
                    /**
                     * view login page after logout
                     * @param view
                     */
                    public void onClick(View view) {
                        android.util.Log.e("click", usernameInput.getText().toString());

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.PUT,
                                URL_LOGOUT + "/" + SingletonClass.getInstance().getCurrentUsername(),
                                null,
                                new Response.Listener<JSONObject>() {
                                    /**
                                     * correct response
                                     * @param response
                                     */
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        SingletonClass.getInstance().setCurrentUsername("");
                                        android.util.Log.e("LOGOUT  PAGE", usernameInput.getText().toString());
                                        try {
                                            android.util.Log.e("data", response.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        android.util.Log.e("here", "String.valueOf(n)");
                                        JSONObject object = response;
                                        startActivity(new Intent(ViewAccount.this, Login.class));
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

        button_manage.setOnClickListener(
                new View.OnClickListener() {
                    /**
                     * go to manage account page
                     * @param view
                     */
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ViewAccount.this, ManageAccount.class));

                    }
                }
        );


        button_back.setOnClickListener(
                new View.OnClickListener() {
                    /**
                     * go back to main menu page
                     * @param view
                     */
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ViewAccount.this, MainMenu.class));

                    }
                }
        );


    }

    /**
     * @param error
     */
    public void parseVolleyError(VolleyError error) {
        try {
            String responseBody = new String(error.networkResponse.data, "utf-8");
            JSONObject data = new JSONObject(responseBody);
            JSONArray errors = data.getJSONArray("errors");
            JSONObject jsonMessage = errors.getJSONObject(0);
            String message = jsonMessage.getString("message");
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
        } catch (UnsupportedEncodingException errorr) {
        }
    }

}


