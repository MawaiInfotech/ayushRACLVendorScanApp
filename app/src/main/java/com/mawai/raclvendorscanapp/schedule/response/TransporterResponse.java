package com.mawai.raclvendorscanapp.schedule.response;

import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.TransporterModel;

import java.util.List;

public class TransporterResponse {

    private Boolean status;
    private String message;
    private List<TransporterModel> data;

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

    public List<TransporterModel> getData() {
        return data;
    }

    public void setData(List<TransporterModel> data) {
        this.data = data;
    }
}
