package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {

    private static final String TAG = "AppointmentListAdapter";

    private Context mContext;
    int mResource;



    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public AppointmentListAdapter (Context context, int resource, ArrayList<Appointment> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String service = getItem(position).getService();
        String startTime = getItem(position).getStartTime();
        String startDate = getItem(position).getStartDate();
        String endTime = getItem(position).getEndTime();
        String endDate = getItem(position).getEndDate();

        Appointment appointment = new Appointment(startTime,endTime,startDate,endDate,service);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvService = (TextView) convertView.findViewById(R.id.serviceTextView1);
        TextView tvTime = (TextView) convertView.findViewById(R.id.timeTextView2);
        TextView tvDate = (TextView) convertView.findViewById(R.id.dateTextView3);


        tvService.setText(service);
        tvTime.setText("startTime: " + startTime + "\n endTime: " + endTime);
        tvDate.setText("startDate: " + startDate + "\n endDate " + endDate);


        return convertView;
    }
}