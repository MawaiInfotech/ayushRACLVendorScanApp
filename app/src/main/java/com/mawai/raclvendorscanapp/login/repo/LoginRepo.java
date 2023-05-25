package com.mawai.raclvendorscanapp.login.repo;

import androidx.lifecycle.MutableLiveData;


import com.mawai.raclvendorscanapp.login.model.LoginModel;
import com.mawai.raclvendorscanapp.login.response.LoginResponse;
import com.mawai.raclvendorscanapp.retrofit.APIClient;
import com.mawai.raclvendorscanapp.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepo {

    private static LoginRepo loginRepo;
    private final ApiService apiServiceLogin;

    public static LoginRepo getInstance() {

        if (loginRepo == null)
            loginRepo = new LoginRepo();
        return loginRepo;
    }

    private LoginRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }




    public MutableLiveData<LoginResponse> getLoginDetail(LoginModel loginModel) {
        final MutableLiveData<LoginResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getLogin(loginModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }
}
