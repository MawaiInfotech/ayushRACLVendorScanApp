package com.mawai.raclvendorscanapp.jobchallan.response;

import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;

import java.util.List;

public class JobChallanResponse {

    private Boolean status;
    private String message;
    private List<JobChallanModel> data;

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

    public List<JobChallanModel> getData() {
        return data;
    }

    public void setData(List<JobChallanModel> data) {
        this.data = data;
    }
}
