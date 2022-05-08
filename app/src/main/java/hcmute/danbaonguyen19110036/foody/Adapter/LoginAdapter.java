package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hcmute.danbaonguyen19110036.foody.Fragment.LoginFragmentTab;
import hcmute.danbaonguyen19110036.foody.Fragment.SignupFragmentTab;


public class LoginAdapter extends FragmentStatePagerAdapter {
    private Context context;


    public LoginAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }





    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoginFragmentTab();
            case 1:
                return new SignupFragmentTab();
        }
        return null;
    }
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Login";
                break;
            case 1:
                title = "Sign-up";
                break;
        }
        return title;
    }
}
