package com.mawai.raclvendorscanapp.schedule.lstbindata.response;

import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.model.ItemModel;

import java.util.List;

public class LstBinDataResponse {

    private Boolean status;
    private String message;
    private List<LstBinDataModel> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LstBinDataModel> getData() {
        return data;
    }

    public void setData(List<LstBinDataModel> data) {
        this.data = data;
    }
}
