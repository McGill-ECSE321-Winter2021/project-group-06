package ca.mcgill.ecse321.vehiclerepairshop;


import android.content.Context;
//import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OfferedServiceAdapter extends ArrayAdapter<OfferedService>{
    private static final String TAG = "OfferedServiceListAdapter";

    private Context mContext;
    int mResource;

    public OfferedServiceAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OfferedService> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

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
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvId = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvDuration = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.textView4);

        tvId.setText(Id);
        tvName.setText(name);
        tvDuration.setText(String.valueOf(duration));
        tvPrice.setText(String.valueOf(price));

        return convertView;




    }
}