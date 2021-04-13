package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ManageAccount extends AppCompatActivity {

    private Button button_save;
    private Button button_cancel;
    private TextView usernameInput;
    private TextView nameInput;
    private EditText newNameInput;
    private EditText newPasswordInput;
    private EditText confirmPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_account);

        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_save = (Button) findViewById(R.id.button_save);
        usernameInput = (TextView) findViewById(R.id.usernameInput);
        nameInput = (TextView) findViewById(R.id.nameInput);
        newNameInput = (EditText) findViewById(R.id.newNameInput);
        newPasswordInput = (EditText) findViewById(R.id.newPasswordInput);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPasswordInput);

        String URL_GET_CUSTOMER = "http://10.0.2.2:8080/getCustomerAccountByUsername";
        String URL_UPDATE = "http://10.0.2.2:8080/updateCustomerAccount";

        RequestQueue requestQueue = Volley.newRequestQueue(ca.mcgill.ecse321.vehiclerepairshop.ManageAccount.this);

        JsonObjectRequest jsonObjectRequestGet = new JsonObjectRequest(
                Request.Method.GET,
                URL_GET_CUSTOMER + "/" + SingletonClass.getInstance().getCurrentUsername(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            usernameInput.setText(response.getString("username"));
                            nameInput.setText(response.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject object = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("ERROR", error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequestGet);

        button_save.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (newPasswordInput.getText().toString().equals(confirmPasswordInput.getText().toString())) {
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                    Request.Method.PUT,
                                    URL_UPDATE + "/" + SingletonClass.getInstance().getCurrentUsername() + "/" + newPasswordInput.getText().toString() + "/" + newNameInput.getText().toString(),
                                    null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                android.util.Log.e("data", response.getString("name"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            JSONObject object = response;
                                            startActivity(new Intent(ManageAccount.this, MainMenu.class));
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
                        } else {
                            android.util.Log.e("Passwords do not match", usernameInput.getText().toString());
                        }
                    }
                }
        );

        button_cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ManageAccount.this, MainMenu.class));

                    }
                }
        );
    }

}


