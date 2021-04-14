package ca.mcgill.ecse321.vehiclerepairshop;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


public class MainMenu extends AppCompatActivity {

    /**
     * go to main menu page
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


    }

    /**
     * view account page
     *
     * @param view
     */
    public void toViewAccount(View view) {
        //starts a new activity
        Intent intent = new Intent(this, ViewAccount.class);
        startActivity(intent);
    }

    /**
     * view appointments page
     *
     * @param view
     */
    public void toAppointments(View view) {
        //starts a new activity
        Intent intent = new Intent(this, CustomerAppointment.class);
        startActivity(intent);
    }

    /**
     * view business info page
     *
     * @param view
     */
    public void toBusinessInfo(View view) {
        //starts a new activity
        Intent intent = new Intent(this, BusinessInfoRequestObject.class);
        startActivity(intent);
    }

    /**
     * view cars page
     *
     * @param view
     */
    public void toCars(View view) {
        //starts a new activity
        Intent intent = new Intent(this, CarRequestObject.class);
        startActivity(intent);
    }

    /**
     * view offered services page
     *
     * @param view
     */
    public void toServices(View view) {
        //starts a new activity
        Intent intent = new Intent(this, OfferedServiceAction.class);
        startActivity(intent);
    }

    /**
     * view receipt page
     *
     * @param view
     */
    public void toReceipts(View view) {
        //starts a new activity
        Intent intent = new Intent(this, ReceiptAction.class);
        startActivity(intent);
    }


}
