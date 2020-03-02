package virtual.software.registration;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataModel> movieItems;
    private ImageLoader imageLoader;// = MySingleton.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<DataModel> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        imageLoader = MySingleton.getInstance(activity).getImageLoader();

    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.users_list, null);

        if (imageLoader == null)
            imageLoader = MySingleton.getInstance(activity).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.nameList);
//        TextView barngay = (TextView) convertView.findViewById(R.id.barangayList);
//        TextView city = (TextView) convertView.findViewById(R.id.cityList);
        TextView recId = (TextView) convertView.findViewById(R.id.recIdList);

        DataModel m = movieItems.get(position);
        thumbNail.setImageUrl(Endpoint.GET_USER_PHOTO + m.getImgURL(), imageLoader);
        recId.setText(m.getRecid()+" - ");
        name.setText(m.getName());
//        barngay.setText(String.valueOf(m.getBarangay()));
//        city.setText(String.valueOf(m.getCity()));



        return convertView;
    }

}