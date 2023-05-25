package com.mawai.raclvendorscanapp.jobchallan.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mawai.raclvendorscanapp.databinding.LayoutJobChallanBinding;
import com.mawai.raclvendorscanapp.databinding.LayoutScheduleListBinding;

public class JobChallanViewHolder extends RecyclerView.ViewHolder {

    LayoutJobChallanBinding listBinding;
    public JobChallanViewHolder(@NonNull LayoutJobChallanBinding listBinding) {
        super(listBinding.getRoot());
        this.listBinding = listBinding;
    }
}
