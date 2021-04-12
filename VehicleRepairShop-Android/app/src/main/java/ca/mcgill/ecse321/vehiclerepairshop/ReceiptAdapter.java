package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ReceiptAdapter extends ArrayAdapter<Receipt> {
    private static final String TAG = "ReceiptAdapter";

    private Context mContext;
    int mResource;


    public ReceiptAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Receipt> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the receipt Info
        int appointmentId = getItem(position).getAppointmentId();
        android.util.Log.e("i", "appointmentId ---------------------------------------------------------------------------------------------------------------------: " + appointmentId);
        android.util.Log.e("i", "appointmentId ---------------------------------------------------------------------------------------------------------------------: " + String.valueOf(appointmentId));
        String startTime = getItem(position).getStartTime();
        String endTime = getItem(position).getEndTime();
        String startDate = getItem(position).getStartDate();
        String endDate = getItem(position).getEndDate();
        String service = getItem(position).getService();
        double price = getItem(position).getPrice();

        Receipt receipt = new Receipt(appointmentId, startTime, endTime, startDate, endDate, service, price);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvAppointmentId = (TextView) convertView.findViewById(R.id.textReceiptView1);
        android.util.Log.e("i", "appointmentId ---------------------------------------------------------------------------------------------------------------------: " + R.id.textReceiptView1);
        android.util.Log.e("i", "appointmentId ---------------------------------------------------------------------------------------------------------------------: " + tvAppointmentId);
        TextView tvService = (TextView) convertView.findViewById(R.id.textReceiptView2);
        TextView tvStartDate = (TextView) convertView.findViewById(R.id.textReceiptView3);
        TextView tvStartTime = (TextView) convertView.findViewById(R.id.textReceiptView4);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.textReceiptView5);
        TextView tvEndDate = (TextView) convertView.findViewById(R.id.textReceiptView6);
        TextView tvEndTime = (TextView) convertView.findViewById(R.id.textReceiptView7);


        tvAppointmentId.setText("appId: " + String.valueOf(appointmentId));
        tvService.setText("service: "+ service);
        tvStartDate.setText("startDate: " + startDate);
        tvStartTime.setText("startTime: " + startTime);
        tvPrice.setText("price: $" + String.valueOf(price));
        tvEndDate.setText("endDate: " + endDate);
        tvEndTime.setText("endTime: " + endTime);

        return convertView;
    }
}
