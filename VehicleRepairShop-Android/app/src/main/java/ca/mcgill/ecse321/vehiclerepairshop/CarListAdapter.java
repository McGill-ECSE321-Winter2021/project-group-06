package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CarListAdapter extends ArrayAdapter<Car> {

    private static final String TAG = "BusinessInfoListAdapter";

    private Context mContext;
    int mResource;



    /**
     * Default constructor for the BusinessInfoListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CarListAdapter (Context context, int resource, ArrayList<Car> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String name = getItem(position).getLicensePlate();
        String address = getItem(position).getOwner();
        String phoneNumber = getItem(position).getYear();
        String email = getItem(position).getMotorType();


        Car car = new Car(name,address,phoneNumber,email);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

//        TextView tvService = (TextView) convertView.findViewById(R.id.serviceTextView1);
//        TextView tvTime = (TextView) convertView.findViewById(R.id.timeTextView2);
//        TextView tvDate = (TextView) convertView.findViewById(R.id.dateTextView3);
//
//
//        tvService.setText(name);
//        tvTime.setText("startTime: " + startTime + "\n endTime: " + endTime);
//        tvDate.setText("startDate: " + startDate + "\n endDate " + endDate);


        return convertView;
    }
}