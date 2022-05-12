package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.NotificationModel;

public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NotificationModel> notificationModelList;

    public NotificationAdapter(Context context, int layout, List<NotificationModel> notificationModelList) {
        this.context = context;
        this.layout = layout;
        this.notificationModelList = notificationModelList;
    }

    @Override
    public int getCount() {
        return notificationModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            AnhXa(holder,view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageViewNotification.setImageResource(notificationModelList.get(i).getFood().getPath());
        holder.textViewDescription.setText(notificationModelList.get(i).getDescription());
        return view;
    }

    private class ViewHolder{
        public ImageView imageViewNotification;
        public TextView textViewDescription;
    }
    public void AnhXa(ViewHolder holder, View view){
        holder.imageViewNotification = view.findViewById(R.id.img_notification);
        holder.textViewDescription = view.findViewById(R.id.notification_description);
    }
}
