package com.mawai.raclvendorscanapp.schedule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.LayoutScheduleListBinding;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.utills.Common;

import java.util.List;

public class ScheduleAdapter  extends RecyclerView.Adapter<ScheduleViewHolder> {

    private List<ScheduleModel> scheduleModelList;
    Context context;

//    private RadioButtonClickListener radioButtonClickListener;

    public ScheduleAdapter(List<ScheduleModel> scheduleModelList, Context context) {
        this.scheduleModelList = scheduleModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutScheduleListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_schedule_list, parent, false);
        return new ScheduleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleModel model = scheduleModelList.get(position);
        holder.listBinding.setModel(model);

        holder.listBinding.btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ScheduleModel", model);
                if(model.getPo_no().substring(0,2).equals("JW")){
                    if (model.getRem_challan_qty()>0){
                        Navigation.findNavController(v).navigate(R.id.action_vendorScanFragment_to_tableFormatFragment,bundle);
                    }else{
                        Toast.makeText(context, "Remaining Challan Qty Should Be Greater than Zero", Toast.LENGTH_LONG).show();
//                    View view = toast.getView();
//                    view.setBackgroundResource(R.drawable.login_btn_box_desgin);
//                    TextView text = (TextView) view.findViewById(android.R.id.message);
//                    text.setTextSize(15);
//                    /*Here you can do anything with above textview like text.setTextColor(Color.parseColor("#000000"));*/
//                    text.setTextColor(Color.parseColor("#FFFFFF"));
//                    toast.show();
                    }
                }else {
                    Navigation.findNavController(v).navigate(R.id.action_vendorScanFragment_to_tableFormatFragment,bundle);
                }


            }
        });

        holder.listBinding.btnJobchallan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ScheduleModel", model);
                Navigation.findNavController(v).navigate(R.id.action_vendorScanFragment_to_jobChallanFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleModelList.size();
    }
}
