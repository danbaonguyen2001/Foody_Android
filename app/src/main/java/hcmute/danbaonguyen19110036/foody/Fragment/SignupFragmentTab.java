package hcmute.danbaonguyen19110036.foody.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.danbaonguyen19110036.foody.R;

public class SignupFragmentTab extends Fragment {
    private static EditText nameUserR,phoneUserR,passUserR,confirmpassUserR;
    public static String username,phoneNumber,password,confirmPassword;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_tab_fragment, container, false);
        nameUserR=view.findViewById(R.id.edtFullname);
        phoneUserR=view.findViewById(R.id.edtPhone);
        passUserR=view.findViewById(R.id.edtPassword);
        confirmpassUserR=view.findViewById(R.id.edtConfirmPass);
        return view;
    }
    public static void getValue(){
        username = nameUserR.getText().toString();
        phoneNumber = phoneUserR.getText().toString();
        password = passUserR.getText().toString();
        confirmPassword = confirmpassUserR.getText().toString();
    }
}
