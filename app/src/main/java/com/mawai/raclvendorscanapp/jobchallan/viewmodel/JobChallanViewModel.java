package com.mawai.raclvendorscanapp.jobchallan.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.jobchallan.repo.JobChallanRepo;
import com.mawai.raclvendorscanapp.jobchallan.response.JobChallanResponse;

import java.util.List;


public class JobChallanViewModel extends ViewModel {

    private JobChallanRepo jobChallanRepo;

    public JobChallanViewModel() {
        jobChallanRepo = JobChallanRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }


    public LiveData<JobChallanResponse> callGetChallanList(JobChallanModel jobChallanModel) {
        return jobChallanRepo.getChallanList(jobChallanModel);
    }

    public LiveData<JobChallanResponse> callGetChallanQtyData(JobChallanModel jobChallanModel) {
        return jobChallanRepo.getChallanQtyData(jobChallanModel);
    }

    public LiveData<JobChallanResponse> callGetSaveChallanData(List<JobChallanModel> jobChallanModel) {
        return jobChallanRepo.getSaveChallanData(jobChallanModel);
    }



}
