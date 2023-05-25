package com.mawai.raclvendorscanapp.jobchallan.repo;

import androidx.lifecycle.MutableLiveData;

import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.jobchallan.response.JobChallanResponse;
import com.mawai.raclvendorscanapp.retrofit.APIClient;
import com.mawai.raclvendorscanapp.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobChallanRepo {

    private static JobChallanRepo jobChallanRepo;
    private final ApiService apiServiceLogin;

    public static JobChallanRepo getInstance() {

        if (jobChallanRepo == null)
            jobChallanRepo = new JobChallanRepo();
        return jobChallanRepo;
    }

    private JobChallanRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }




    public MutableLiveData<JobChallanResponse> getChallanList(JobChallanModel jobChallanModel) {
        final MutableLiveData<JobChallanResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getChallanList(jobChallanModel).enqueue(new Callback<JobChallanResponse>() {
            @Override
            public void onResponse(Call<JobChallanResponse> call, Response<JobChallanResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JobChallanResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<JobChallanResponse> getChallanQtyData(JobChallanModel jobChallanModel) {
        final MutableLiveData<JobChallanResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getChallanQtyData(jobChallanModel).enqueue(new Callback<JobChallanResponse>() {
            @Override
            public void onResponse(Call<JobChallanResponse> call, Response<JobChallanResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JobChallanResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<JobChallanResponse> getSaveChallanData(List<JobChallanModel> jobChallanModel) {
        final MutableLiveData<JobChallanResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.savechallanData(jobChallanModel).enqueue(new Callback<JobChallanResponse>() {
            @Override
            public void onResponse(Call<JobChallanResponse> call, Response<JobChallanResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JobChallanResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }


}
