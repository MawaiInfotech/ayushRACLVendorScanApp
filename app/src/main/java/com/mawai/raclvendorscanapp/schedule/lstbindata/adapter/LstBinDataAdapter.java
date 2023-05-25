package com.mawai.raclvendorscanapp.schedule.lstbindata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.LayoutLstBinDataBinding;
import com.mawai.raclvendorscanapp.databinding.LayoutScheduleListBinding;
import com.mawai.raclvendorscanapp.schedule.adapter.ScheduleViewHolder;
import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;

import java.util.List;

public class LstBinDataAdapter extends RecyclerView.Adapter<LstBinDataViewHolder> {

    private List<LstBinDataModel> lstBinDataModelList;
    Context context;

//    private RadioButtonClickListener radioButtonClickListener;

    public LstBinDataAdapter(List<LstBinDataModel> lstBinDataModelList, Context context) {
        this.lstBinDataModelList = lstBinDataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public LstBinDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutLstBinDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_lst_bin_data, parent, false);
        return new LstBinDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LstBinDataViewHolder holder, int position) {
        LstBinDataModel model = lstBinDataModelList.get(position);
        holder.listBinding.setModel(model);

    }

    @Override
    public int getItemCount() {
        return lstBinDataModelList.size();
    }
}
