package hcmute.danbaonguyen19110036.foody.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import hcmute.danbaonguyen19110036.foody.Adapter.NotificationAdapter;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;


public class NotificationFragment extends Fragment {

    public ListView listViewNotification;
    public NotificationAdapter notificationAdapter;
    public FoodDao foodDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        listViewNotification = view.findViewById(R.id.listview_notification);
        foodDao = DatabaseApplication.Instance().createFoodDao();
        notificationAdapter=new NotificationAdapter(getActivity(),R.layout.layout_notification,SaveVariable.notificationModelList);
        listViewNotification.setAdapter(notificationAdapter);
        return view;
    }
}