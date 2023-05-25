package com.mawai.raclvendorscanapp.schedule.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mawai.raclvendorscanapp.databinding.LayoutScheduleListBinding;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    LayoutScheduleListBinding listBinding;
    public ScheduleViewHolder(@NonNull LayoutScheduleListBinding listBinding) {
        super(listBinding.getRoot());
        this.listBinding = listBinding;
    }
}
