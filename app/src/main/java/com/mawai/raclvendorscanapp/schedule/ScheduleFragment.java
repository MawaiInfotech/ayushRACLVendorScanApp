package com.mawai.raclvendorscanapp.schedule;

import static android.view.KeyEvent.*;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.FragmentScheduleBinding;
import com.mawai.raclvendorscanapp.login.model.LoginModel;
import com.mawai.raclvendorscanapp.login.response.LoginResponse;
import com.mawai.raclvendorscanapp.schedule.adapter.ScheduleAdapter;
import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.model.TransporterModel;
import com.mawai.raclvendorscanapp.schedule.viewmodel.ScheduleViewModel;
import com.mawai.raclvendorscanapp.utills.Common;
import com.mawai.raclvendorscanapp.utills.SessionManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class ScheduleFragment extends Fragment {


    public ScheduleFragment() {
        // Required empty public constructor
    }


    FragmentScheduleBinding binding;
    SessionManager sessionManager;
    ScheduleViewModel scheduleViewModel;
    ScheduleAdapter scheduleAdapter;
    List<ScheduleModel> modellist = new ArrayList();
    private long mLastClickTime = 0;

    SpinnerDialog spinnerDialog,spinnerDialogTransporter,spinnerDialogScheduleNumber;

    private Map<String, String> divisionMap = new HashMap<String, String>();
    ArrayList<String> items = new ArrayList<>();
    List<ItemModel> divisionsModelArrayList = new ArrayList<>();

    private Map<String, String> divisionMapTransPorter = new HashMap<String, String>();
    ArrayList<String> itemsTransPorter = new ArrayList<>();
    List<TransporterModel> transPorterArrayList = new ArrayList<>();

    private Map<String, String> divisionMapScheduleNumber = new HashMap<String, String>();
    ArrayList<String> itemsScheduleNumber = new ArrayList<>();
    List<ScheduleModel> scheduleNumberArrayList = new ArrayList<>();

    String vendor_code = "";
    String scheduleNumber = "";
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("onCreateView()","onCreateView()");
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_vendor_scan, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        scheduleViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ScheduleViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());

//        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                View view = v.getCurrentFocus();
//                if (v != null) {
//                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//                Toast.makeText(getContext(), "sdgdagadfgsfd", Toast.LENGTH_SHORT).show();
//            }
//        });

//        binding.edtHeatCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                scheduleViewModel.setText(String.valueOf(s));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                scheduleViewModel.setText(s.toString());
//            }
//        });



//        scheduleViewModel.getText().observe(getActivity(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                // When text change
//                // set result text on text view
//                binding.showtext.setText(s);
//                LstBinDataModel unitModel = new LstBinDataModel();
//                unitModel.setVendorcode(sessionManager.getString("user_code"));
//                unitModel.setScan_by(sessionManager.getString("user_name"));
//                unitModel.setItemcode("");
//                unitModel.setScanbincode(s.toString().trim());
//                unitModel.setSch_no("");
//                unitModel.setBin_capacity(sessionManager.getInt("bin_capacity"));
//                unitModel.setPo_no("");
//                unitModel.setAmd_no("");
//                unitModel.setProc_cd("");
//                unitModel.setOlditem_code("");
//                unitModel.setBal_sch_qty(0);
//                unitModel.setRem_schal_qty(0);
//                unitModel.setScheduleqty(0);
//                scheduleViewModel.callGetScanBin(unitModel).observe(getViewLifecycleOwner(), (lstBinDataResponse) -> {
//                    if (lstBinDataResponse.getStatus()){
//                        binding.edtHeatCode.setError(null);
//                        binding.edtHeatCode.clearFocus();
//
//                    }else {
//                        binding.edtHeatCode.setError(lstBinDataResponse.getMessage());
////                      Toast.makeText(getContext(), "" + lstBinDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

//         binding.edtBillNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//
//                if (!b) {
//                    // Validate youredittext
//                   System.out.println(view.toString());
//
//
//                }else {
//                    System.out.println("Ayush");
//                }
//            }
//        });




//        binding.edtHeatCode.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if(event.getAction() == ACTION_DOWN) {
//                    switch (keyCode) {
//                        case KEYCODE_BACK:
////                            getPresenter().onBackPressed();
//                            break;
//                    }
//                }
//                return false;
//            }
//        });




//        binding.edtHeatCode.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
//
//                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                        actionId == EditorInfo.IME_ACTION_DONE ||
//                        event != null &&
//                                event.getAction() == KeyEvent.ACTION_DOWN &&
//                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
//                                event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//                    if (event == null || !event.isShiftPressed()) {
//                        // the user is done typing.
//                        System.out.println("Ayush");
//                        Toast.makeText(getContext(), "Ayush", Toast.LENGTH_SHORT).show();
//                        if (textView != null) {
//                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
//                        }
//                        return true; // consume.
//                    }
//                }
//                return false;
//            }
//        });

        binding.edtItemCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.isConnectedCodeToInternet(getContext())) {
                    if (!TextUtils.isEmpty(binding.edtScheduleCode.getText().toString().trim())){
                        ItemModel unitModel = new ItemModel();
                        unitModel.setVendorcode(sessionManager.getString("user_code"));
                        unitModel.setScan_by(sessionManager.getString("user_name"));
                        unitModel.setScanbincode("");
                        unitModel.setSch_no(scheduleNumber);
                        unitModel.setItemcode("");
                        getBinItemList(unitModel);
                    }else {
                        Toast.makeText(getContext(), "Please Select Schedule Name", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Check Your Internet", Toast.LENGTH_LONG).show();
                }
            }
        });


        binding.edtScheduleCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 spinnerDialogScheduleNumber.showSpinerDialog();
            }
        });

        binding.edtTransporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialogTransporter.showSpinerDialog();
            }
        });

        spinnerDialogTransporter = new SpinnerDialog(getActivity(), itemsTransPorter, "Select or Search Transporter", "Close");// With No Animation

        final List<String> datass = new ArrayList<>();

        spinnerDialogTransporter.bindOnSpinerListener((item, position) -> {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

            Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
//            binding.edtUniqueNumberSelect.setText(item);
            binding.edtTransporter.setText(item);
            datass.add(divisionMapTransPorter.get(transPorterArrayList.get(position).getVendor_code()));
            Log.d("", "" + transPorterArrayList.get(position).getVendor_code());

            vendor_code = transPorterArrayList.get(position).getVendor_code();

        });

        spinnerDialogScheduleNumber = new SpinnerDialog(getActivity(), itemsScheduleNumber, "Select or Search Schedule Name", "Close");// With No Animation

        final List<String> datasss = new ArrayList<>();

        spinnerDialogScheduleNumber.bindOnSpinerListener((item, position) -> {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

            Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
//            binding.edtUniqueNumberSelect.setText(item);
            binding.edtScheduleCode.setText(item);
            datasss.add(divisionMapScheduleNumber.get(scheduleNumberArrayList.get(position).getSchd_no()));
            Log.d("", "" + scheduleNumberArrayList.get(position).getSchd_no());

            scheduleNumber = scheduleNumberArrayList.get(position).getSchd_no();

        });

        spinnerDialog = new SpinnerDialog(getActivity(), items, "Select or Search Item Number", "Close");// With No Animation

        final List<String> datas = new ArrayList<>();

        spinnerDialog.bindOnSpinerListener((item, position) -> {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

            Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
//            binding.edtUniqueNumberSelect.setText(item);
            binding.edtItemCode.setText(item);
            datas.add(divisionMap.get(divisionsModelArrayList.get(position).getBin_item_code()));
            Log.d("", "" + divisionsModelArrayList.get(position).getItem_desc());


            sessionManager.save(SessionManager.BIN_CODE,divisionsModelArrayList.get(position).getBin_code());
            sessionManager.save(SessionManager.BIN_CAPACITY,divisionsModelArrayList.get(position).getBin_capacity());
//
            ScheduleModel unitModel = new ScheduleModel();
            unitModel.setVendorcode(sessionManager.getString("user_code"));
            unitModel.setScan_by(sessionManager.getString("user_name"));
            unitModel.setScanbincode("");
            unitModel.setSch_no(scheduleNumber);
            unitModel.setItemcode(binding.edtItemCode.getText().toString().trim());
            getScheduleData(unitModel);
        });


        if (Common.isConnectedCodeToInternet(getContext())){
            getTransporterList();
            ScheduleModel unitModel = new ScheduleModel();
            unitModel.setVendorcode(sessionManager.getString("user_code"));
            unitModel.setSch_no("");
            getScheduleList(unitModel);
        }else {
            Toast.makeText(getContext(), "Check Your Internet", Toast.LENGTH_LONG).show();
        }


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.edtBillNo.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter Bill No.", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(binding.edtChallanNo.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter Challan No.", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(binding.edtBillChallanDate.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter Bill/Challan Date", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(binding.edtInvoiceAmount.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter Invoice Amount", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(binding.edtTransporter.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter Transporter Name", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(binding.edtVehicleNo.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter Vehicle No.", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(binding.edtBatchCode.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter BatchCode", Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(binding.edtHeatCode.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please Enter HeatCode", Toast.LENGTH_LONG).show();
                }else {
                    if (!modellist.isEmpty()){
                        if(!modellist.get(0).getPo_no().substring(0,2).equals("JW")){
                            if (modellist.get(0).getBin_qty()>0){
                                ScheduleModel scheduleModel = new ScheduleModel();
                                scheduleModel.setVendorcode(sessionManager.getString("user_code"));
                                scheduleModel.setBillno(binding.edtBillNo.getText().toString().trim());
                                scheduleModel.setChallanno(binding.edtChallanNo.getText().toString().trim());
                                scheduleModel.setChallan_date(binding.edtBillChallanDate.getText().toString().trim());
                                scheduleModel.setInvoice_amt(Integer.parseInt(binding.edtInvoiceAmount.getText().toString().trim()));
                                scheduleModel.setTransporter_id(vendor_code);
                                scheduleModel.setShc_amd_no(modellist.get(0).getShc_amd_no());
                                scheduleModel.setSch_no(scheduleNumber);
                                scheduleModel.setVehicle_no(binding.edtVehicleNo.getText().toString().trim());
                                scheduleModel.setItem_cd(binding.edtItemCode.getText().toString().trim());
                                scheduleModel.setHeat_code(binding.edtHeatCode.getText().toString().trim());
                                scheduleModel.setBatch_code(binding.edtBatchCode.getText().toString().trim());
                                getSaveData(scheduleModel);
                            }else {
                                Toast.makeText(getContext(), "Bin Qty Should be greater Than Zero", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            if (modellist.get(0).getIntered_challan_qty()==modellist.get(0).getBin_qty()){
                                ScheduleModel scheduleModel = new ScheduleModel();
                                scheduleModel.setVendorcode(sessionManager.getString("user_code"));
                                scheduleModel.setBillno(binding.edtBillNo.getText().toString().trim());
                                scheduleModel.setChallanno(binding.edtChallanNo.getText().toString().trim());
                                scheduleModel.setChallan_date(binding.edtBillChallanDate.getText().toString().trim());
                                scheduleModel.setInvoice_amt(Integer.parseInt(binding.edtInvoiceAmount.getText().toString().trim()));
                                scheduleModel.setTransporter_id(vendor_code);
                                scheduleModel.setSch_no(scheduleNumber);
                                scheduleModel.setShc_amd_no(modellist.get(0).getShc_amd_no());
                                scheduleModel.setVehicle_no(binding.edtVehicleNo.getText().toString().trim());
                                scheduleModel.setItem_cd(binding.edtItemCode.getText().toString().trim());
                                scheduleModel.setHeat_code(binding.edtHeatCode.getText().toString().trim());
                                scheduleModel.setBatch_code(binding.edtBatchCode.getText().toString().trim());
                                getSaveData(scheduleModel);
                            }else {
                                Toast.makeText(getContext(), "Inserted Challan Qty and Bin Qty Should Be Equal Then You Can Save The Data", Toast.LENGTH_LONG).show();
                            }
                        }
                    }else {
                        Toast.makeText(getContext(), "Please Select Item Code", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return binding.getRoot();
    }




    private void getScheduleData(ScheduleModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        scheduleViewModel.callGetScheduleData(loginModel).observe(getViewLifecycleOwner(), (scheduleResponse) -> {
            progressDialog.dismiss();
            if (scheduleResponse!=null) {
                if (scheduleResponse.getStatus()) {
                    modellist = scheduleResponse.getData();
                    scheduleAdapter = new ScheduleAdapter(modellist, getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.scheduleRecyclerView.setLayoutManager(linearLayoutManager);
                    binding.scheduleRecyclerView.setHasFixedSize(true);
                    binding.scheduleRecyclerView.setAdapter(scheduleAdapter);
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                    
                } else {
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getScheduleList(ScheduleModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        scheduleViewModel.callGetScheduleList(loginModel).observe(getViewLifecycleOwner(), (scheduleResponse) -> {
            progressDialog.dismiss();
            if (scheduleResponse!=null) {
                if (scheduleResponse.getStatus()) {
                    scheduleNumberArrayList.clear();
                    itemsScheduleNumber.clear();
                    divisionMapScheduleNumber.clear();
                    if (!scheduleResponse.getData().isEmpty()) {
                        scheduleNumberArrayList = scheduleResponse.getData();
                        for (ScheduleModel divisionsModel : scheduleNumberArrayList) {
                            itemsScheduleNumber.add(divisionsModel.getName());
                            divisionMapScheduleNumber.put(divisionsModel.getName(), divisionsModel.getSchd_no());
                        }
                    } else {
                        Toast.makeText(getContext(), "Transporter List is Empty", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getSaveData(ScheduleModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        scheduleViewModel.callGetSaveData(loginModel).observe(getViewLifecycleOwner(), (scheduleResponse) -> {
            progressDialog.dismiss();
            if (scheduleResponse!=null) {
                if (scheduleResponse.getStatus()) {
                    binding.edtBillNo.setText("");
                    binding.edtChallanNo.setText("");
                    binding.edtBillChallanDate.setText("");
                    binding.edtInvoiceAmount.setText("");
                    binding.edtTransporter.setText("");
                    binding.edtVehicleNo.setText("");
                    binding.edtItemCode.setText("");
                    binding.edtScheduleCode.setText("");
                    binding.edtBatchCode.setText("");
                    binding.edtHeatCode.setText("");
                    vendor_code = "";
                    scheduleNumber = "";;
                    modellist.clear();
                    scheduleAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "" + scheduleResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }
//    nm52442@gmail.com
//    8750987678
    private void getTransporterList() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        scheduleViewModel.callGetTransporterList().observe(getViewLifecycleOwner(), (transporterResponse) -> {
            progressDialog.dismiss();
            if (transporterResponse!=null) {
                if (transporterResponse.getStatus()) {
                    transPorterArrayList.clear();
                    itemsTransPorter.clear();
                    divisionMapTransPorter.clear();
                    if (!transporterResponse.getData().isEmpty()) {
                        transPorterArrayList = transporterResponse.getData();
                        for (TransporterModel divisionsModel : transPorterArrayList) {
                            itemsTransPorter.add(divisionsModel.getName());
                            divisionMapTransPorter.put(divisionsModel.getName(), divisionsModel.getVendor_code());
                        }
                    } else {
                        Toast.makeText(getContext(), "Transporter List is Empty", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "" + transporterResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getBinItemList(ItemModel model) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        scheduleViewModel.callGetBinItemList(model).observe(getViewLifecycleOwner(), (itemResponse) -> {
            progressDialog.dismiss();
            if (itemResponse != null) {
                if (itemResponse.getStatus()) {
                    divisionsModelArrayList.clear();
                    items.clear();
                    divisionMap.clear();
                    if (!itemResponse.getData().isEmpty()) {
                        divisionsModelArrayList = itemResponse.getData();
                        for (ItemModel divisionsModel : divisionsModelArrayList) {
                            items.add(divisionsModel.getBin_item_code());
                            divisionMap.put(divisionsModel.getBin_item_code(), divisionsModel.getItem_desc());
                        }
                    } else {
                        Toast.makeText(getContext(), "Invoice List is Empty", Toast.LENGTH_LONG).show();
                    }
                    spinnerDialog.showSpinerDialog();
                } else {
                    Toast.makeText(getContext(), "" + itemResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getContext(), "Invoice List Call Is Not Working From Server Side", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated()","onViewCreated()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("onViewStateRestored()","onViewStateRestored()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart()","onStart()");
        if (!TextUtils.isEmpty(binding.edtItemCode.getText().toString().trim())){
            ScheduleModel unitModel = new ScheduleModel();
            unitModel.setVendorcode(sessionManager.getString("user_code"));
            unitModel.setScan_by(sessionManager.getString("user_name"));
            unitModel.setScanbincode("");
            unitModel.setItemcode(binding.edtItemCode.getText().toString().trim());
            unitModel.setSch_no(scheduleNumber);
            getScheduleData(unitModel);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause()","onPause()");
//        binding.edtItemCode.setText("");
    }


}