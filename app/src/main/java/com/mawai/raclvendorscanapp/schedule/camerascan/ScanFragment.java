package com.mawai.raclvendorscanapp.schedule.camerascan;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.schedule.adapter.ScheduleAdapter;
import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.viewmodel.ScheduleViewModel;
import com.mawai.raclvendorscanapp.utills.SessionManager;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScanFragment extends Fragment implements ZXingScannerView.ResultHandler{


    public ScanFragment() {
        // Required empty public constructor
    }
    ScheduleViewModel scheduleViewModel;
    SessionManager sessionManager;
    ProgressDialog dialog;
    private ZXingScannerView mScannerView;
    AlertDialog alert;
    ScheduleModel scheduleModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_scan, container, false);
//       visionScannerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reprint_option, container, false);
        scheduleViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ScheduleViewModel.class);
        //visionScannerBinding.setLifecycleOwner(this);
        sessionManager = new SessionManager(getContext());
        mScannerView = new ZXingScannerView(getContext());

        Bundle bundle = getArguments();
        assert bundle != null;
        scheduleModel = (ScheduleModel) bundle.getSerializable("ScheduleModel");
//        if (scheduleModel!=null){
//            binding.setModel(scheduleModel);
//        }else {
//            Toast.makeText(getContext(), "Bundle Data Not Found", Toast.LENGTH_LONG).show();
//        }


        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        if (rawResult.getBarcodeFormat() != null) {
            mScannerView.stopCamera();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            final View customLayout = getLayoutInflater().inflate(R.layout.layout_dialog, null);
            builder.setView(customLayout);
            TextView editText = customLayout.findViewById(R.id.data);
            editText.setText(new StringBuilder().append("Your Bar code is ").append(rawResult.getText()).toString());
            Button yes_btn = customLayout.findViewById(R.id.yes_btn);
            Button no_btn = customLayout.findViewById(R.id.no_btn);
            yes_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!rawResult.getText().toString().isEmpty()){
                        alert.dismiss();
                        getScanBin(rawResult.getText());
                        Toast.makeText(getContext(), "" + rawResult.getText(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(), "Barcode Is Empty", Toast.LENGTH_LONG).show();
                    }
                }
            });


            no_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mScannerView.startCamera();
                    alert.dismiss();
                }
            });
            alert = builder.create();
            // alert.setCanceledOnTouchOutside(false);
            alert.setCancelable(false);
            alert.show();

        } else {
            Toast.makeText(getContext()
                    , "oops...You did not scan anything", Toast.LENGTH_LONG).show();
        }
        mScannerView.resumeCameraPreview(this);
    }

    private void getScanBin(String text) {
        LstBinDataModel unitModel = new LstBinDataModel();
        unitModel.setVendorcode(sessionManager.getString("user_code"));
        unitModel.setScan_by(sessionManager.getString("user_name"));
        unitModel.setScanbincode(text);
        unitModel.setItemcode(scheduleModel.getItem_cd());
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
        getScanBin(unitModel);
    }

    private void getScanBin(LstBinDataModel scheduleModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        scheduleViewModel.callGetScanBin(scheduleModel).observe(getViewLifecycleOwner(), (scheduleResponse) -> {
            progressDialog.dismiss();
            if (scheduleResponse!=null) {
                if (scheduleResponse.getStatus()) {

                    mScannerView.startCamera();
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    mScannerView.startCamera();
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                mScannerView.startCamera();
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }
}