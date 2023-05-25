package com.mawai.raclvendorscanapp.room.dao.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;

import java.util.List;

@Dao
public interface AssemblyChallanDao {

    @Insert
    void insertDetails(JobChallanModel data);

    @Query("delete from JobChallanModel")
    void deleteAllData();

    @Query("select * from JobChallanModel")
    List<JobChallanModel> getJobChallanListFull();
}
