package ca.mcgill.ecse321.vehiclerepairshop;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Log.d(TAG, "onCreate: Started.");
        setSupportActionBar(toolbar);

        ListView mListView = (ListView) findViewById(R.id.listView);

        FloatingActionButton fab = findViewById(R.id.fab);

        //Create the offeredService Objects
        OfferedService service1 = new OfferedService("service1","wash",10,9.99);
        OfferedService service2 = new OfferedService("service2","wash2",10,9.99);
        OfferedService service3 = new OfferedService("service3","wash3",10,9.99);
        OfferedService service4 = new OfferedService("service4","wash4",10,9.99);
        OfferedService service5 = new OfferedService("service5","wash5",10,9.99);
        OfferedService service6 = new OfferedService("service6","wash6",10,9.99);


        //Add the offeredService objects to an ArrayList
        ArrayList<OfferedService> offeredServiceArrayList = new ArrayList<>();
        offeredServiceArrayList.add(service1);
        offeredServiceArrayList.add(service2);
        offeredServiceArrayList.add(service3);
        offeredServiceArrayList.add(service4);
        offeredServiceArrayList.add(service5);
        offeredServiceArrayList.add(service6);

        OfferedServiceAdapter serviceAdapter = new OfferedServiceAdapter(this, R.layout.fragment_service,offeredServiceArrayList);
        mListView.setAdapter(serviceAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}