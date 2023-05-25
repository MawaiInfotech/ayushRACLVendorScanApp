package com.mawai.raclvendorscanapp.schedule.response;

import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;

import java.util.List;

public class ItemResponse {

    private Boolean status;
    private String message;
    private List<ItemModel> data;


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

    public List<ItemModel> getData() {
        return data;
    }

    public void setData(List<ItemModel> data) {
        this.data = data;
    }
}
