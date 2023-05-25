package com.mawai.raclvendorscanapp.schedule.lstbindata.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mawai.raclvendorscanapp.databinding.LayoutLstBinDataBinding;
import com.mawai.raclvendorscanapp.databinding.LayoutScheduleListBinding;

public class LstBinDataViewHolder extends RecyclerView.ViewHolder {

    LayoutLstBinDataBinding listBinding;
    public LstBinDataViewHolder(@NonNull LayoutLstBinDataBinding listBinding) {
        super(listBinding.getRoot());
        this.listBinding = listBinding;
    }
}
