package com.mawai.raclvendorscanapp.schedule.lstbindata;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.FragmentLstBinDataBinding;
import com.mawai.raclvendorscanapp.schedule.adapter.ScheduleAdapter;
import com.mawai.raclvendorscanapp.schedule.lstbindata.adapter.LstBinDataAdapter;
import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.viewmodel.ScheduleViewModel;
import com.mawai.raclvendorscanapp.utills.Common;
import com.mawai.raclvendorscanapp.utills.SessionManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class LstBinDataFragment extends Fragment {


    FragmentLstBinDataBinding binding;
    SessionManager sessionManager;
    public LstBinDataFragment() {

    }
    ScheduleViewModel scheduleViewModel;
    LstBinDataAdapter  lstBinDataAdapter;
    List<LstBinDataModel> modellist = new ArrayList();
    ScheduleModel scheduleModel;
    AlertDialog alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_table_format, container, false);
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lst_bin_data, container, false);
        scheduleViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ScheduleViewModel.class);
        binding.setLifecycleOwner(this);
        binding.scanBarcode.requestFocus();
        disableSoftInputFromAppearing(binding.scanBarcode);
//         binding.setListener(this);

        sessionManager = new SessionManager(getContext());

        Bundle bundle = getArguments();
        scheduleModel = (ScheduleModel) bundle.getSerializable("ScheduleModel");
        if (scheduleModel!=null){
            binding.setModel(scheduleModel);
        }else {
            Toast.makeText(getContext(), "Bundle Data Not Found", Toast.LENGTH_LONG).show();
        }

        if (Common.isConnectedCodeToInternet(getContext())){
            LstBinDataModel unitModel = new LstBinDataModel();
            unitModel.setVendorcode(sessionManager.getString("user_code"));
            unitModel.setScan_by(sessionManager.getString("user_name"));
            unitModel.setScanbincode(sessionManager.getString("bin_code"));
            unitModel.setItemcode(scheduleModel.getItem_cd());
            getLstBinData(unitModel);
        }else {
            Toast.makeText(getContext(), "Check Your Internet", Toast.LENGTH_LONG).show();
        }

//        binding.btnJobchallan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_tableFormatFragment_to_jobChallanFragment);
//            /*    Bundle bundle = new Bundle();
//                bundle.putSerializable("ScheduleModel", scheduleModel);
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_tableFormatFragment_to_scanFragment,bundle);*/
//            }
//        });


        binding.scanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ScheduleModel", scheduleModel);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_tableFormatFragment_to_scanFragment,bundle);
            }
        });

        binding.scanBarcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty()) {
                        LstBinDataModel unitModel = new LstBinDataModel();
                        unitModel.setVendorcode(sessionManager.getString("user_code"));
                        unitModel.setScan_by(sessionManager.getString("user_name"));
                        unitModel.setItemcode(scheduleModel.getItem_cd());
                        unitModel.setScanbincode(s.toString().trim());
                        unitModel.setSch_no(scheduleModel.getSch_no());
                        unitModel.setBin_capacity(sessionManager.getInt("bin_capacity"));
                        unitModel.setPo_no(scheduleModel.getPo_no());
                        unitModel.setAmd_no(scheduleModel.getAmd_no());
                        unitModel.setShc_amd_no(scheduleModel.getShc_amd_no());
                        unitModel.setProc_cd(scheduleModel.getProc_cd());
                        unitModel.setOlditem_code("");
                        unitModel.setBal_sch_qty(scheduleModel.getBalance_schd());
                        unitModel.setRem_schal_qty(scheduleModel.getRem_challan_qty());
                        unitModel.setScheduleqty(scheduleModel.getTot_schd());
                        showAlertDailog(unitModel);
                    } else {
                        Toast.makeText(getContext(), "Scan Barcode Is Empty", Toast.LENGTH_LONG).show();
                    }
            }
        });

        return binding.getRoot();

    }

    private void getLstBinData(LstBinDataModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        scheduleViewModel.callGetLstBinData(loginModel).observe(getViewLifecycleOwner(), (lstBinDataResponse) -> {
            progressDialog.dismiss();
            if (lstBinDataResponse!=null) {
                if (lstBinDataResponse.getStatus()) {

                    modellist = lstBinDataResponse.getData();
                    lstBinDataAdapter = new LstBinDataAdapter(modellist, getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.lstbindataRecyclerView.setLayoutManager(linearLayoutManager);
                    binding.lstbindataRecyclerView.setHasFixedSize(true);
                    binding.lstbindataRecyclerView.setAdapter(lstBinDataAdapter);
                    Toast.makeText(getContext(), "" + lstBinDataResponse.getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "" + lstBinDataResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }


    private void showAlertDailog(LstBinDataModel lstBinDataModel){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        builder.setView(customLayout);

        TextView editText = customLayout.findViewById(R.id.data);
        editText.setText(new StringBuilder().append("Your Bar code is ").append(lstBinDataModel.getScanbincode()));

        Button yes_btn = customLayout.findViewById(R.id.yes_btn);
        Button no_btn = customLayout.findViewById(R.id.no_btn);

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScanBin(lstBinDataModel);
                alert.dismiss();

            }
        });


        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                binding.scanBarcode.setText("");
            }
        });
        alert = builder.create();
        // alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.show();

    }

    private void getScanBin(LstBinDataModel lstBinDataModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        scheduleViewModel.callGetScanBin(lstBinDataModel).observe(getViewLifecycleOwner(), (lstBinDataResponse) -> {
            progressDialog.dismiss();
            if (lstBinDataResponse!=null) {
                if (lstBinDataResponse.getStatus()) {
                    modellist = lstBinDataResponse.getData();
                    lstBinDataAdapter = new LstBinDataAdapter(modellist, getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.lstbindataRecyclerView.setLayoutManager(linearLayoutManager);
                    binding.lstbindataRecyclerView.setHasFixedSize(true);
                    binding.lstbindataRecyclerView.setAdapter(lstBinDataAdapter);
                    binding.scanBarcode.setText("");
                    binding.scanBarcode.requestFocus();
                    Toast.makeText(getContext(), "" + lstBinDataResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    binding.scanBarcode.setText("");
                    binding.scanBarcode.requestFocus();
                    Toast.makeText(getContext(), "" + lstBinDataResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                binding.scanBarcode.setText("");
                binding.scanBarcode.requestFocus();
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void disableSoftInputFromAppearing(EditText editText) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setShowSoftInputOnFocus(false);
        } else {
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // ignore
            }
        }
    }
}