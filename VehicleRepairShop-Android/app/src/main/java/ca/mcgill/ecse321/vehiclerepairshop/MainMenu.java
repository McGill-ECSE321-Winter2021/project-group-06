package ca.mcgill.ecse321.vehiclerepairshop;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainMenu  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


    }

    public void toViewAccount(View view) {
        //starts a new activity
        Intent intent = new Intent(this, ViewAccount.class);
        startActivity(intent);
    }

    public void toAppointments(View view) {
        //starts a new activity
        Intent intent = new Intent(this, CustomerAppointment.class);
        startActivity(intent);
    }

    public void toBusinessInfo(View view) {
        //starts a new activity
        Intent intent = new Intent(this, BusinessInfoRequestObject.class);
        startActivity(intent);
    }

    public void toCars(View view) {
        //starts a new activity
        Intent intent = new Intent(this, CarRequestObject.class);
        startActivity(intent);
    }

    public void toServices(View view) {
        //starts a new activity
        Intent intent = new Intent(this, OfferedServiceAction.class);
        startActivity(intent);
    }

    public void toReceipts(View view) {
        //starts a new activity
        Intent intent = new Intent(this, ReceiptAction.class);
        startActivity(intent);
    }


}
