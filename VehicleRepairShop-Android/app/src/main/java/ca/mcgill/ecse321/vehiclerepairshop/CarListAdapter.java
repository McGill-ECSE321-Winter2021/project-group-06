package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CarListAdapter extends ArrayAdapter<Car> {

    private static final String TAG = "CarListAdapter";

    private Context mContext;
    int mResource;


    /**
     * Default constructor for the BusinessInfoListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public CarListAdapter(Context context, int resource, ArrayList<Car> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    /**
     * get current page view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String licensePlate = getItem(position).getLicensePlate();
        String owner = getItem(position).getMotorType();
        int year = getItem(position).getYear();
        String model = getItem(position).getModel();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvLicensePlate = (TextView) convertView.findViewById(R.id.licensePlateText);
        TextView tvModel = (TextView) convertView.findViewById(R.id.modelText);
        TextView tvYear = (TextView) convertView.findViewById(R.id.yearText);
        TextView tvOwner = (TextView) convertView.findViewById(R.id.ownerText);


        tvLicensePlate.setText(licensePlate);
        tvModel.setText(model);
        tvYear.setText(String.valueOf(year));
        tvOwner.setText(owner);


        return convertView;
    }
}