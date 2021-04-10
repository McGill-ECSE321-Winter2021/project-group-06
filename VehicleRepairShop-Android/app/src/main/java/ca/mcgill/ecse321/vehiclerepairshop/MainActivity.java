package ca.mcgill.ecse321.vehiclerepairshop;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private String error = null;
    private Button button_login;
    private EditText usernameLogin;
    private EditText passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_login = (Button)findViewById(R.id.button_login);
        usernameLogin   = (EditText)findViewById(R.id.usernameLogin);
        passwordLogin   = (EditText)findViewById(R.id.passwordLogin);

//        FloatingActionButton fab = findViewById(R.id.fab);
        button_login.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        error = "";
                        HttpUtils.put("loginCustomerAccount/" + usernameLogin.getText().toString() + "/" + passwordLogin.getText().toString(), new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                refreshErrorMessage();
                                usernameLogin.setText("");
                                passwordLogin.setText("");
                            }
                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                try {
                                    error += errorResponse.get("message").toString();
                                } catch (JSONException e) {
                                    error += e.getMessage();
                                }
                                refreshErrorMessage();
                            }
                        });
                    }
        });

        refreshErrorMessage();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void loginCustomer(View v) {
//        error = "";
//        HttpUtils.put("loginCustomerAccount/" + usernameLogin.getText().toString() + "/" + passwordLogin.getText().toString(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                refreshErrorMessage();
//                usernameLogin.setText("");
//                passwordLogin.setText("");
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
//    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}
