package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class Login extends AppCompatActivity {

    private String error = null;
    private Button button_login;
    private EditText usernameLogin;
    private EditText passwordLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ListView customerLV;
        button_login = (Button) findViewById(R.id.button_login);
        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);

        String URL = "http://10.0.2.2:8080/loginCustomerAccount";


        button_login.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        android.util.Log.e("click", usernameLogin.getText().toString());

                        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.PUT,
                                URL + "/" + usernameLogin.getText().toString() + "/" + passwordLogin.getText().toString(),
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        SingletonClass.getInstance().setCurrentUsername(usernameLogin.getText().toString());
                                        android.util.Log.e("LOGIN PAGE", usernameLogin.getText().toString());
                                        setContentView(R.layout.activity_customer_appointment);
                                        try {
                                            android.util.Log.e("data",response.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        android.util.Log.e("here", "String.valueOf(n)");
                                        JSONObject object = response;
                                        startActivity(new Intent(Login.this, ViewAccount.class));

                                    }
                                },



                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        android.util.Log.e("ERROR", error.toString());
                                    }
                                }
                        );

                        requestQueue.add(jsonObjectRequest);
                        startActivity(new Intent(Login.this, ViewAccount.class));
                    }
                }
        );



    }

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


