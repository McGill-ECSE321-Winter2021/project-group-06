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


public class OfferedServiceAdapter extends ArrayAdapter<OfferedService> {
    private static final String TAG = "OfferedServiceListAdapter";

    private Context mContext;
    int mResource;

    /**
     * @param context
     * @param resource
     * @param objects
     */
    public OfferedServiceAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OfferedService> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    /**
     * view page offered service page with attributes
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the offeredService Info
        String Id = getItem(position).getOfferedServiceId();
        String name = getItem(position).getOfferedServiceName();
        int duration = getItem(position).getOfferedServiceDuration();
        double price = getItem(position).getOfferedServicePrice();

        OfferedService service = new OfferedService(Id, name, duration, price);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvId = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvDuration = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.textView4);

        tvId.setText(Id);
        tvName.setText(name);
        tvDuration.setText("duration: " + String.valueOf(duration));
        tvPrice.setText("price: " + String.valueOf(price));

        return convertView;
        
    }
}