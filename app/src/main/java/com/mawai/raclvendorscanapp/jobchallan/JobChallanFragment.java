package com.mawai.raclvendorscanapp.jobchallan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mawai.raclvendorscanapp.R;
import com.mawai.raclvendorscanapp.databinding.FragmentJobChallanBinding;
import com.mawai.raclvendorscanapp.jobchallan.adapter.JobChallanAdapter;
import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.jobchallan.viewmodel.JobChallanViewModel;
import com.mawai.raclvendorscanapp.room.dao.dao.AssemblyChallanDao;
import com.mawai.raclvendorscanapp.room.dao.maindb.MainDatabaseConn;
import com.mawai.raclvendorscanapp.schedule.adapter.ScheduleAdapter;
import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.viewmodel.ScheduleViewModel;
import com.mawai.raclvendorscanapp.utills.Common;
import com.mawai.raclvendorscanapp.utills.SessionManager;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class JobChallanFragment extends Fragment {

    FragmentJobChallanBinding binding;
    SpinnerDialog spinnerDialog;
    private final Map<String, String> divisionMap = new HashMap<String, String>();
    ArrayList<String> items = new ArrayList<>();
    List<JobChallanModel> divisionsModelArrayList = new ArrayList<>();
    private long mLastClickTime = 0;
    SessionManager sessionManager;
    JobChallanViewModel jobChallanViewModel;
    ScheduleModel scheduleModel;
    private MainDatabaseConn db;
    List<JobChallanModel> jobChallanModelList = new ArrayList<>();
    JobChallanAdapter jobChallanAdapter;
    String item;

    boolean isSame = Boolean.TRUE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job_challan, container, false);
        jobChallanViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(JobChallanViewModel.class);
        binding.setLifecycleOwner(this);
        db = MainDatabaseConn.getDatabase(getContext());

        sessionManager = new SessionManager(getContext());

        db.assemblyScanDao().deleteAllData();

        Bundle bundle = getArguments();
        scheduleModel = (ScheduleModel) bundle.getSerializable("ScheduleModel");
        if (scheduleModel != null) {
//            binding.setModel(scheduleModel);
        } else {
            Toast.makeText(getContext(), "Bundle Data Not Found", Toast.LENGTH_LONG).show();
        }

        binding.edtChallanList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.isConnectedCodeToInternet(getContext())) {
                    JobChallanModel unitModel = new JobChallanModel();
                    unitModel.setVendorcode(sessionManager.getString("user_code"));
                    unitModel.setScan_by(sessionManager.getString("user_name"));
                    unitModel.setItemcode(scheduleModel.getItem_cd());
                    getChallanList(unitModel);
                } else {
                    Toast.makeText(getContext(), "Check Your Internet", Toast.LENGTH_LONG).show();
                }
            }
        });


        spinnerDialog = new SpinnerDialog(getActivity(), items, "Select or Search Challan Number", "Close");// With No Animation

        final List<String> datas = new ArrayList<>();

        spinnerDialog.bindOnSpinerListener((item, position) -> {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

            Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
//            binding.edtUniqueNumberSelect.setText(item);
            binding.edtChallanList.setText(item);

            JobChallanModel unitModel = new JobChallanModel();
            unitModel.setVendorcode(sessionManager.getString("user_code"));
            unitModel.setScan_by(sessionManager.getString("user_name"));
            unitModel.setItemcode(scheduleModel.getItem_cd());
            unitModel.setChallan_no(binding.edtChallanList.getText().toString().trim());
            getChallanQtyData(unitModel);

        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.isConnectedCodeToInternet(getContext())) {
                    if (TextUtils.isEmpty(binding.edtChallanList.getText().toString().trim())) {
                        Toast.makeText(getContext(), "Please Select Challan List", Toast.LENGTH_LONG).show();
                    } else if (TextUtils.isEmpty(binding.edtQty.getText().toString().trim())) {
                        Toast.makeText(getContext(), "Please Enter The Quantity", Toast.LENGTH_LONG).show();
                    } else {

                        int quantity = Integer.parseInt(binding.edtQuantity.getText().toString().trim());
                        int enterQty = Integer.parseInt(binding.edtQty.getText().toString().trim());
                        int totalEnterQty = 0;

                        jobChallanModelList = db.assemblyScanDao().getJobChallanListFull();

                        if (!jobChallanModelList.isEmpty()) {
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                totalEnterQty = jobChallanModelList.stream()
                                        .map(x -> x.getEnterQty())
                                        .reduce(0, Integer::sum);
                            }
                            if ((enterQty <= quantity) && (totalEnterQty+enterQty <= scheduleModel.getBin_qty())) {
                                JobChallanModel jobChallanModel = new JobChallanModel();
                                jobChallanModel.setChalno(binding.edtChallanList.getText().toString().trim());
                                jobChallanModel.setQt(Integer.parseInt(binding.edtQuantity.getText().toString().trim()));
                                jobChallanModel.setEnterQty(Integer.parseInt(binding.edtQty.getText().toString().trim()));
                                jobChallanModel.setItemcode(item);
                                new LoginInsertion(db.assemblyScanDao()).execute(jobChallanModel);
                            } else {
                                Toast.makeText(getContext(), "Enter Quantity Should Be Less Than Bin Qty And Selected Quantity", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            if ((enterQty <= quantity) && (enterQty <= scheduleModel.getBin_qty())) {
                                JobChallanModel jobChallanModel = new JobChallanModel();
                                jobChallanModel.setChalno(binding.edtChallanList.getText().toString().trim());
                                jobChallanModel.setQt(Integer.parseInt(binding.edtQuantity.getText().toString().trim()));
                                jobChallanModel.setEnterQty(Integer.parseInt(binding.edtQty.getText().toString().trim()));
                                jobChallanModel.setItemcode(item);
                                new LoginInsertion(db.assemblyScanDao()).execute(jobChallanModel);
                            } else {
                                Toast.makeText(getContext(), "Enter Quantity Should Be Less Than Bin Qty And Selected Quantity", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Check Your Internet", Toast.LENGTH_LONG).show();
                }

            }
        });

        binding.btnFinalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobChallanModelList = db.assemblyScanDao().getJobChallanListFull();
                if (!jobChallanModelList.isEmpty()){
                    ArrayList<JobChallanModel>  jobChallanModelArrayList = new ArrayList<>();

                    for (JobChallanModel model :jobChallanModelList){

                        JobChallanModel jobChallanModel = new JobChallanModel();
                        jobChallanModel.setPo_no(scheduleModel.getPo_no());
                        jobChallanModel.setItem_cd(scheduleModel.getItem_cd());
                        jobChallanModel.setCcgp_chalno(model.getChalno());
                        jobChallanModel.setCcgp_qty(model.getQt());
                        jobChallanModel.setChallan_qty(model.getEnterQty());
                        jobChallanModel.setTotal_enter_qty(scheduleModel.getBin_qty());
                        jobChallanModel.setOlditem_code("");
                        jobChallanModel.setChallan_item(model.getItemcode());

                        jobChallanModelArrayList.add(jobChallanModel);
                    }

                    getSaveChallanData(jobChallanModelArrayList);
                }else {
                    Toast.makeText(getContext(), "Please Save the Data In Table Then You Can Final Submit", Toast.LENGTH_LONG).show();
                }
            }
        });

        return binding.getRoot();
    }

    private void getChallanList(JobChallanModel model) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        jobChallanViewModel.callGetChallanList(model).observe(getViewLifecycleOwner(), (jobChallanResponse) -> {
            progressDialog.dismiss();
            if (jobChallanResponse != null) {
                if (jobChallanResponse.getStatus()) {
                    divisionsModelArrayList.clear();
                    items.clear();
                    divisionMap.clear();
                    if (!jobChallanResponse.getData().isEmpty()) {
                        divisionsModelArrayList = jobChallanResponse.getData();
                        for (JobChallanModel divisionsModel : divisionsModelArrayList) {
                            items.add(divisionsModel.getChalno());
                            divisionMap.put(divisionsModel.getChalno(), divisionsModel.getVendorcode());
                        }
                    } else {
                        Toast.makeText(getContext(), "Challan List is Empty", Toast.LENGTH_LONG).show();
                    }
                    spinnerDialog.showSpinerDialog();
                } else {
                    Toast.makeText(getContext(), "" + jobChallanResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getContext(), "Challan List Call Is Not Working From Server Side", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void getChallanQtyData(JobChallanModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        jobChallanViewModel.callGetChallanQtyData(loginModel).observe(getViewLifecycleOwner(), (jobChallanResponse) -> {
            progressDialog.dismiss();
            if (jobChallanResponse != null) {
                if (jobChallanResponse.getStatus()) {
                    binding.edtQuantity.setText(String.valueOf(jobChallanResponse.getData().get(0).getQt()));
                    item = jobChallanResponse.getData().get(0).getItem();
                    Toast.makeText(getContext(), "" + jobChallanResponse.getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "" + jobChallanResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getContext(), "Server Not Respond", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getSaveChallanData(List<JobChallanModel> loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        jobChallanViewModel.callGetSaveChallanData(loginModel).observe(getViewLifecycleOwner(), (jobChallanResponse) -> {
            progressDialog.dismiss();
            if (jobChallanResponse != null) {
                if (jobChallanResponse.getStatus()) {
                    db.assemblyScanDao().deleteAllData();
                    jobChallanModelList = db.assemblyScanDao().getJobChallanListFull();
                    jobChallanAdapter = new JobChallanAdapter(jobChallanModelList, getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.jobChallanRecyclerView.setLayoutManager(linearLayoutManager);
                    binding.jobChallanRecyclerView.setHasFixedSize(true);
                    binding.jobChallanRecyclerView.setAdapter(jobChallanAdapter);
                    Toast.makeText(getContext(), "" + jobChallanResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "" + jobChallanResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getContext(), "Server Not Respond", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class LoginInsertion extends AsyncTask<JobChallanModel, Void, Void> {

        private AssemblyChallanDao assemblyChallanDao;

        private LoginInsertion(AssemblyChallanDao assemblyChallanDao) {
            this.assemblyChallanDao = assemblyChallanDao;
        }

        @Override
        protected Void doInBackground(JobChallanModel... data) {

            ArrayList<JobChallanModel> jobChallanModelLists = new ArrayList(Arrays.asList(data));
            System.out.println(jobChallanModelLists.get(0).getChalno() + "\n" + jobChallanModelLists.get(0).getQt() + "\n" + jobChallanModelLists.get(0).getEnterQty());

            try {
                assemblyChallanDao.insertDetails(data[0]);
            } catch (SQLiteConstraintException e) {
                Log.e("UNIQUE", "UNIQUE");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "You Can Select Another Challan Number Because You have already used this Challan Number", Toast.LENGTH_LONG).show();
                    }
                });
            }


//            getActivity().runOnUiThread(new Runnable() {
//                public void run() {
//                    jobChallanModelList = db.assemblyScanDao().getJobChallanListFull();
//                    if (jobChallanModelList.isEmpty()){
//                        assemblyChallanDao.insertDetails(data[0]);
//                    }else {
//                        for (JobChallanModel jobChallanModel:jobChallanModelList)
//                        {
//                            if (jobChallanModel.getChalno().equals(jobChallanModelLists.get(0).getChalno())){
//                                Toast.makeText(getContext(), "You Can Select Another Challan Number", Toast.LENGTH_LONG).show();
//                                isSame = false;
//                            }
//                        }
//
//                        if (isSame){
//                            assemblyChallanDao.insertDetails(data[0]);
//                        }
//                        isSame = true;
//
//                    }
//                }
//            });


//            for (JobChallanModel jobChallanModel : jobChallanModelList){
//                JobChallanModel model= new JobChallanModel();
//                model.setChalno(jobChallanModelList.get(0).getChalno());
//                model.setEnterQty(jobChallanModelList.get(0).getEnterQty());
//                model.setQt(jobChallanModelList.get(0).getQt());
//
//                jobChallanModelList.add(jobChallanModel);
//            }


//            if (loginDao.getValidateUser(data[0].getInner_carter_scan()) == null)
//                loginDao.insertDetails(data[0]);
            // else
            //Toast.makeText(GoogleVisionScannerActivity.this, "This Code is Already Scanned ", Toast.LENGTH_LONG).show();

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            jobChallanModelList = db.assemblyScanDao().getJobChallanListFull();
            if (!jobChallanModelList.isEmpty()) {
//            jobChallanModelList = scheduleResponse.getData();
                jobChallanAdapter = new JobChallanAdapter(jobChallanModelList, getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                binding.jobChallanRecyclerView.setLayoutManager(linearLayoutManager);
                binding.jobChallanRecyclerView.setHasFixedSize(true);
                binding.jobChallanRecyclerView.setAdapter(jobChallanAdapter);
                binding.edtChallanList.setText("");
                binding.edtQty.setText("");
                binding.edtQuantity.setText("");
                item = "";
//                Toast.makeText(getContext(), "Data Found", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_LONG).show();
            }
        }
    }

}