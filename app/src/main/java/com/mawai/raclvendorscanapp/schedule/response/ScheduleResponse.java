package com.mawai.raclvendorscanapp.schedule.response;

import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;

import java.util.List;

public class ScheduleResponse {

    private Boolean status;
    private String message;
    private List<ScheduleModel> data;

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

    public List<ScheduleModel> getData() {
        return data;
    }

    public void setData(List<ScheduleModel> data) {
        this.data = data;
    }
}
