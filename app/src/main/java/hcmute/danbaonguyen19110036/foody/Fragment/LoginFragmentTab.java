package hcmute.danbaonguyen19110036.foody.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hcmute.danbaonguyen19110036.foody.R;

public class LoginFragmentTab extends Fragment  {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //connectData();
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);

        return view;
    }

}
