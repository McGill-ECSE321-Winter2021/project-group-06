package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BusinessInfoListAdapter extends ArrayAdapter<BusinessInfo> {

    private static final String TAG = "BusinessInfoListAdapter";

    private Context mContext;
    int mResource;



    /**
     * Default constructor for the BusinessInfoListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public BusinessInfoListAdapter (Context context, int resource, ArrayList<BusinessInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String name = getItem(position).getBusinessName();
        String address = getItem(position).getBusinessAddress();
        String phoneNumber = getItem(position).getBusinessPhoneNumber();
        String email = getItem(position).getBusinessEmail();


        BusinessInfo businessInfo = new BusinessInfo(name,address,phoneNumber,email);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

          TextView tvName = (TextView) convertView.findViewById(R.id.business_name);
          TextView tvAddress = (TextView) convertView.findViewById(R.id.business_address);
//        TextView tvTime = (TextView) convertView.findViewById(R.id.timeTextView2);
//        TextView tvDate = (TextView) convertView.findViewById(R.id.dateTextView3);
//
//

          tvName.setText(name);
          tvName.setText(address);
//        tvTime.setText("startTime: " + startTime + "\n endTime: " + endTime);
//        tvDate.setText("startDate: " + startDate + "\n endDate " + endDate);


        return convertView;
    }
}