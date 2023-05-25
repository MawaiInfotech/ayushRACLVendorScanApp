package com.mawai.raclvendorscanapp.retrofit;



import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.jobchallan.response.JobChallanResponse;
import com.mawai.raclvendorscanapp.schedule.lstbindata.model.LstBinDataModel;
import com.mawai.raclvendorscanapp.schedule.lstbindata.response.LstBinDataResponse;
import com.mawai.raclvendorscanapp.schedule.model.ItemModel;
import com.mawai.raclvendorscanapp.schedule.model.ScheduleModel;
import com.mawai.raclvendorscanapp.schedule.response.ItemResponse;
import com.mawai.raclvendorscanapp.schedule.response.ScheduleResponse;
import com.mawai.raclvendorscanapp.login.model.LoginModel;
import com.mawai.raclvendorscanapp.login.response.LoginResponse;
import com.mawai.raclvendorscanapp.schedule.response.TransporterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @GET("api/Account/unitdetails")
//    Call<UnitResponse> unitList();
//
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/vendorlogin")
    Call<LoginResponse> getLogin(@Body LoginModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/scheduledata")
    Call<ScheduleResponse> getScheduleData(@Body ScheduleModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/savedata")
    Call<ScheduleResponse> getSaveData(@Body ScheduleModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/schedulelist")
    Call<ScheduleResponse> getScheduleList(@Body ScheduleModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/lstbindata")
    Call<LstBinDataResponse> getLstBinData(@Body LstBinDataModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/scanbin")
    Call<LstBinDataResponse> getScanBin(@Body LstBinDataModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/getbinitemlist")
    Call<ItemResponse> getBinItemList(@Body ItemModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/Binasn/transporterlist")
    Call<TransporterResponse> getTransporterList();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/lstchallandata")
    Call<JobChallanResponse> getChallanList(@Body JobChallanModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/challanqtydata")
    Call<JobChallanResponse> getChallanQtyData(@Body JobChallanModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Binasn/savechallandata")
    Call<JobChallanResponse> savechallanData(@Body List<JobChallanModel> model);


//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/fgreceive/save_fgreceive")
//    Call<FGReceiveResponse> saveFgReceive(@Body FGReceiveModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Palletpacking/scan_palletbarcode")
//    Call<PackingTypeResponse> chekPalletBarCode(@Body PackingTypeModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Palletpacking/get_allpalletno")
//    Call<PackingTypeResponse> getAllPalletNo(@Body PackingTypeModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Palletpacking/get_databypalletno")
//    Call<PackingTypeResponse> getDataByPalletNo(@Body PackingTypeModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Palletpacking/submit_palletpacking")
//    Call<PackingTypeResponse> submitPalletPacking(@Body PackingTypeModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Invoicemapping/scan_invoicebarcode")
//    Call<InvoiceMappingResponse> scanInvoiceBarcode(@Body InvoiceMappingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Invoicemapping/get_allinvoiceno")
//    Call<InvoiceMappingResponse> getAllInvoiceNo(@Body InvoiceMappingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Invoicemapping/invoicelist")
//    Call<InvoiceListResponse> getInvoiceList(@Body InvoiceListModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Invoicemapping/get_databyinvoiceno")
//    Call<InvoiceMappingResponse> getDataByInvoiceNo(@Body InvoiceMappingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/Invoicemapping/submit_invoicemapping")
//    Call<InvoiceMappingResponse> submitInvoiceMapping(@Body InvoiceMappingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @GET("api/Account/printerdetails")
//    Call<PrinterResponse> printerDetailsList();




}
