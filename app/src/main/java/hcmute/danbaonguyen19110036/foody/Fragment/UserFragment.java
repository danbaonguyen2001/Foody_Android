package hcmute.danbaonguyen19110036.foody.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hcmute.danbaonguyen19110036.foody.Activity.CartActivity;
import hcmute.danbaonguyen19110036.foody.Activity.HomeActivity;
import hcmute.danbaonguyen19110036.foody.Activity.LoginActivity;
import hcmute.danbaonguyen19110036.foody.R;


public class UserFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }
}