package hcmute.danbaonguyen19110036.foody.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hcmute.danbaonguyen19110036.foody.Fragment.BillFragment;
import hcmute.danbaonguyen19110036.foody.Fragment.FavoriteFragment;
import hcmute.danbaonguyen19110036.foody.Fragment.HomeFragment;
import hcmute.danbaonguyen19110036.foody.Fragment.NotificationFragment;
import hcmute.danbaonguyen19110036.foody.Fragment.UserFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new BillFragment();
            case 3:
                return new NotificationFragment();
            case 4:
                return new UserFragment();
        }

        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
