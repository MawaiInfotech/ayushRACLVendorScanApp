package com.mawai.raclvendorscanapp.login;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.FragmentLoginBinding;
import com.mawai.raclvendorscanapp.login.model.LoginModel;
import com.mawai.raclvendorscanapp.login.response.LoginResponse;
import com.mawai.raclvendorscanapp.login.viewmodel.LoginViewModel;
import com.mawai.raclvendorscanapp.utills.SessionManager;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }
    SessionManager sessionManager;
    FragmentLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_login, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(LoginViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());

        if (sessionManager.getBoolean(SessionManager.CHKBOX)) {
            binding.setModel(new LoginModel(sessionManager.getString(SessionManager.USER_CODE),sessionManager.getString(SessionManager.PASSWORD)));
            binding.savedUsernamePassword.setChecked(true);
        } else {
            binding.setModel(new LoginModel());
        }

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(binding.edtUserid.getText().toString().trim()) &&
                        !TextUtils.isEmpty(binding.edtPassword.getText().toString().trim())
                       ) {


//                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
                    if (binding.savedUsernamePassword.isChecked()) {
                        sessionManager.save(SessionManager.USER_CODE, binding.edtUserid.getText().toString().trim());
                        sessionManager.save(SessionManager.PASSWORD, binding.edtPassword.getText().toString().trim());
//                        sessionManager.save(SessionManager.UNIT_NAME, binding.edtunitname.getText().toString().trim());
                        sessionManager.save(SessionManager.CHKBOX, Boolean.TRUE);
                    } else {
                        sessionManager.save(SessionManager.USER_CODE, "");
                        sessionManager.save(SessionManager.PASSWORD, "");
//                        sessionManager.save(SessionManager.UNIT_NAME, "");
                        sessionManager.save(SessionManager.CHKBOX, Boolean.FALSE);
                    }

                    LoginModel unitModel = new LoginModel();
                    unitModel.setVendorcode(binding.edtUserid.getText().toString().trim());
                    unitModel.setPassword(binding.edtPassword.getText().toString().trim());
                    unitModel.setVendorname("");
                    if (unitModel != null) {
                        getLoginCall(unitModel);
                    } else {
                        Toast.makeText(getContext(), "Please Enter Vendor Code And Password", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Please Enter Vendor Code And Password", Toast.LENGTH_LONG).show();
                }

            }
        });


        return binding.getRoot();
    }

    private void getLoginCall(LoginModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        loginViewModel.callGetLoginDetail(loginModel).observe(getViewLifecycleOwner(), (LoginResponse loginResponse) -> {
            progressDialog.dismiss();
            if (loginResponse!=null) {
                if (loginResponse.getStatus()) {
                    sessionManager.save(SessionManager.USER_CODE,binding.edtUserid.getText().toString().trim());
                    sessionManager.save(SessionManager.USER_NAME,loginResponse.getData().getVendorname());
//                    sessionManager.save(SessionManager.UNIT_NAME, binding.edtunitname.getText().toString().trim());

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
                    Toast.makeText(getContext(), "Login Successfully" , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "" + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }
}