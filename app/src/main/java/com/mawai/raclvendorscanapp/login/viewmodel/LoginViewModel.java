package com.mawai.raclvendorscanapp.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.raclvendorscanapp.login.model.LoginModel;
import com.mawai.raclvendorscanapp.login.repo.LoginRepo;
import com.mawai.raclvendorscanapp.login.response.LoginResponse;

public class LoginViewModel extends ViewModel {

    private LoginRepo loginRepo;

    public LoginViewModel() {
        loginRepo = LoginRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }


    public LiveData<LoginResponse> callGetLoginDetail(LoginModel loginModel) {
        return loginRepo.getLoginDetail(loginModel);
    }
}
