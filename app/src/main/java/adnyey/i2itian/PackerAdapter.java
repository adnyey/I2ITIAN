package adnyey.i2itian;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Mahajan-PC on 01-05-2017.
 */

public class PackerAdapter extends ArrayAdapter<Packer> {
    private final Context context;
    private final List<Packer> data;
    int resourceId;

    public PackerAdapter(Context context,int layoutResourceId, List<Packer> data) {
        super(context, -1, data);
        this.context = context;
        this.resourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resourceId, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.list_title);
        ImageView badge = (ImageView) rowView.findViewById(R.id.tic_sponsor);
        TextView textView2 = (TextView) rowView.findViewById(R.id.list_tag);
        try {
            if (data.get(position).getPro_spon().toString().equals("Internal")) {
                badge.setVisibility(GONE);
            } else {
                badge.setVisibility(VISIBLE);
            }
            textView1.setText(data.get(position).getPro_title());
            textView2.setText(data.get(position).getPro_domain());
        }
        catch(NullPointerException e){e.printStackTrace();}

        return rowView;
    }
}