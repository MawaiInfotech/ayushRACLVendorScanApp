package com.mawai.raclvendorscanapp.schedule.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.lstbindata.response.LstBinDataResponse;
import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.repo.ScheduleRepo;
import com.mawai.raclvendorscanapp.schedule.response.ItemResponse;
import com.mawai.raclvendorscanapp.schedule.response.ScheduleResponse;
import com.mawai.raclvendorscanapp.schedule.response.TransporterResponse;

public class ScheduleViewModel extends ViewModel {

    private ScheduleRepo scheduleRepo;

    public ScheduleViewModel() {
        scheduleRepo = ScheduleRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    MutableLiveData<String> mutableLiveData
            = new MutableLiveData<>();

    // Create set text method
    public void setText(String s)
    {
        // Set value
        mutableLiveData.setValue(s);
    }

    // Create get text method
    public MutableLiveData<String> getText()
    {
        // return value
        return mutableLiveData;
    }

    public LiveData<ScheduleResponse> callGetScheduleData(ScheduleModel scheduleModel) {
        return scheduleRepo.getScheduleData(scheduleModel);
    }

    public LiveData<ScheduleResponse> callGetSaveData(ScheduleModel scheduleModel) {
        return scheduleRepo.getSaveData(scheduleModel);
    }

    public LiveData<ScheduleResponse> callGetScheduleList(ScheduleModel scheduleModel) {
        return scheduleRepo.getScheduleList(scheduleModel);
    }


    public LiveData<LstBinDataResponse> callGetLstBinData(LstBinDataModel scheduleModel) {
        return scheduleRepo.getLstBinData(scheduleModel);
    }

    public LiveData<LstBinDataResponse> callGetScanBin(LstBinDataModel scheduleModel) {
        return scheduleRepo.getScanBin(scheduleModel);
    }

    public LiveData<TransporterResponse> callGetTransporterList() {
        return scheduleRepo.getTransporterList();
    }

    public LiveData<ItemResponse> callGetBinItemList(ItemModel scheduleModel) {
        return scheduleRepo.getBinItemList(scheduleModel);
    }
}
