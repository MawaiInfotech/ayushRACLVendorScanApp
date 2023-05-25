package com.mawai.raclvendorscanapp.schedule.repo;

import androidx.lifecycle.MutableLiveData;

import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.lstbindata.response.LstBinDataResponse;
import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.response.ItemResponse;
import com.mawai.raclvendorscanapp.schedule.response.ScheduleResponse;
import com.mawai.raclvendorscanapp.retrofit.APIClient;
import com.mawai.raclvendorscanapp.retrofit.ApiService;
import com.mawai.raclvendorscanapp.schedule.response.TransporterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepo {

    private static ScheduleRepo scheduleRepo;
    private final ApiService apiServiceLogin;

    public static ScheduleRepo getInstance() {

        if (scheduleRepo == null)
            scheduleRepo = new ScheduleRepo();
        return scheduleRepo;
    }

    private ScheduleRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }




    public MutableLiveData<ScheduleResponse> getScheduleData(ScheduleModel scheduleModel) {
        final MutableLiveData<ScheduleResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getScheduleData(scheduleModel).enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<LstBinDataResponse> getLstBinData(LstBinDataModel scheduleModel) {
        final MutableLiveData<LstBinDataResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getLstBinData(scheduleModel).enqueue(new Callback<LstBinDataResponse>() {
            @Override
            public void onResponse(Call<LstBinDataResponse> call, Response<LstBinDataResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LstBinDataResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<LstBinDataResponse> getScanBin(LstBinDataModel scheduleModel) {
        final MutableLiveData<LstBinDataResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getScanBin(scheduleModel).enqueue(new Callback<LstBinDataResponse>() {
            @Override
            public void onResponse(Call<LstBinDataResponse> call, Response<LstBinDataResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LstBinDataResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<ItemResponse> getBinItemList(ItemModel scheduleModel) {
        final MutableLiveData<ItemResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getBinItemList(scheduleModel).enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }


    public MutableLiveData<TransporterResponse> getTransporterList() {
        final MutableLiveData<TransporterResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getTransporterList().enqueue(new Callback<TransporterResponse>() {
            @Override
            public void onResponse(Call<TransporterResponse> call, Response<TransporterResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TransporterResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<ScheduleResponse> getSaveData(ScheduleModel scheduleModel) {
        final MutableLiveData<ScheduleResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getSaveData(scheduleModel).enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }

    public MutableLiveData<ScheduleResponse> getScheduleList(ScheduleModel scheduleModel) {
        final MutableLiveData<ScheduleResponse> loginresult = new MutableLiveData<>();
        apiServiceLogin.getScheduleList(scheduleModel).enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                loginresult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                loginresult.setValue(null);
            }
        });
        return loginresult;
    }
}
