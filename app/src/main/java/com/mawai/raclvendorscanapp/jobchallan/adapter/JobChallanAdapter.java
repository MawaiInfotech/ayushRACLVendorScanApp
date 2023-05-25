package com.mawai.raclvendorscanapp.jobchallan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.LayoutJobChallanBinding;
import com.mawai.raclvendorscanapp.databinding.LayoutScheduleListBinding;
import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.schedule.adapter.ScheduleViewHolder;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;

import java.util.List;

public class JobChallanAdapter extends RecyclerView.Adapter<JobChallanViewHolder> {

    private List<JobChallanModel> jobChallanModelList;
    Context context;

//    private RadioButtonClickListener radioButtonClickListener;

    public JobChallanAdapter(List<JobChallanModel> jobChallanModelList, Context context) {
        this.jobChallanModelList = jobChallanModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public JobChallanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutJobChallanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_job_challan, parent, false);
        return new JobChallanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull JobChallanViewHolder holder, int position) {
        JobChallanModel model = jobChallanModelList.get(position);
        holder.listBinding.setModel(model);

    }

    @Override
    public int getItemCount() {
        return jobChallanModelList.size();
    }
}
