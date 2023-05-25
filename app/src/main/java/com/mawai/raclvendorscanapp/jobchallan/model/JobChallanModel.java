package com.mawai.raclvendorscanapp.jobchallan.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JobChallanModel")
public class JobChallanModel {

//    @PrimaryKey(autoGenerate = true)
//    @NonNull
//    @ColumnInfo(name = "id")
//    private int id;

    @ColumnInfo(name = "vendorcode")
    private String vendorcode;

    @ColumnInfo(name = "scanbincode")
    private String scanbincode;

    @ColumnInfo(name = "itemcode")
    private String itemcode;

    @ColumnInfo(name = "bin_capacity")
    private int bin_capacity;

    @ColumnInfo(name = "sch_no")
    private String sch_no;

    @ColumnInfo(name = "po_no")
    private String po_no;

    @ColumnInfo(name = "amd_no")
    private String amd_no;

    @ColumnInfo(name = "proc_cd")
    private String proc_cd;

    @ColumnInfo(name = "bal_sch_qty")
    private int bal_sch_qty;

    @ColumnInfo(name = "rem_schal_qty")
    private int rem_schal_qty;

    @ColumnInfo(name = "scheduleqty")
    private int scheduleqty;

    @ColumnInfo(name = "qt")
    private int qt;

    @ColumnInfo(name = "olditem_code")
    private String olditem_code;


    @ColumnInfo(name = "challan_no")
    private String challan_no;

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "chalno")
    private String chalno;

    @ColumnInfo(name = "scan_by")
    private String scan_by;

    @ColumnInfo(name = "item")
    private String item;

    @ColumnInfo(name = "enterQty")
    private int enterQty;


    @ColumnInfo(name = "item_cd")
    private String item_cd;

    @ColumnInfo(name = "ccgp_chalno")
    private String ccgp_chalno;

    @ColumnInfo(name = "ccgp_qty")
    private int ccgp_qty;

    @ColumnInfo(name = "challan_qty")
    private int challan_qty;

    @ColumnInfo(name = "total_enter_qty")
    private int total_enter_qty;

    @ColumnInfo(name = "challan_item")
    private String challan_item;



    public JobChallanModel() {
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public String getVendorcode() {
        return vendorcode;
    }

    public void setVendorcode(String vendorcode) {
        this.vendorcode = vendorcode;
    }

    public String getScanbincode() {
        return scanbincode;
    }

    public void setScanbincode(String scanbincode) {
        this.scanbincode = scanbincode;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public int getBin_capacity() {
        return bin_capacity;
    }

    public void setBin_capacity(int bin_capacity) {
        this.bin_capacity = bin_capacity;
    }

    public String getSch_no() {
        return sch_no;
    }

    public void setSch_no(String sch_no) {
        this.sch_no = sch_no;
    }

    public String getPo_no() {
        return po_no;
    }

    public void setPo_no(String po_no) {
        this.po_no = po_no;
    }

    public String getAmd_no() {
        return amd_no;
    }

    public void setAmd_no(String amd_no) {
        this.amd_no = amd_no;
    }

    public String getProc_cd() {
        return proc_cd;
    }

    public void setProc_cd(String proc_cd) {
        this.proc_cd = proc_cd;
    }

    public int getBal_sch_qty() {
        return bal_sch_qty;
    }

    public void setBal_sch_qty(int bal_sch_qty) {
        this.bal_sch_qty = bal_sch_qty;
    }

    public int getRem_schal_qty() {
        return rem_schal_qty;
    }

    public void setRem_schal_qty(int rem_schal_qty) {
        this.rem_schal_qty = rem_schal_qty;
    }

    public int getScheduleqty() {
        return scheduleqty;
    }

    public void setScheduleqty(int scheduleqty) {
        this.scheduleqty = scheduleqty;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public String getOlditem_code() {
        return olditem_code;
    }

    public void setOlditem_code(String olditem_code) {
        this.olditem_code = olditem_code;
    }

    public String getChallan_no() {
        return challan_no;
    }

    public void setChallan_no(String challan_no) {
        this.challan_no = challan_no;
    }

    @NonNull
    public String getChalno() {
        return chalno;
    }

    public void setChalno(@NonNull String chalno) {
        this.chalno = chalno;
    }

    public String getScan_by() {
        return scan_by;
    }

    public void setScan_by(String scan_by) {
        this.scan_by = scan_by;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getEnterQty() {
        return enterQty;
    }

    public void setEnterQty(int enterQty) {
        this.enterQty = enterQty;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public void setItem_cd(String item_cd) {
        this.item_cd = item_cd;
    }

    public String getCcgp_chalno() {
        return ccgp_chalno;
    }

    public void setCcgp_chalno(String ccgp_chalno) {
        this.ccgp_chalno = ccgp_chalno;
    }

    public int getCcgp_qty() {
        return ccgp_qty;
    }

    public void setCcgp_qty(int ccgp_qty) {
        this.ccgp_qty = ccgp_qty;
    }

    public int getChallan_qty() {
        return challan_qty;
    }

    public void setChallan_qty(int challan_qty) {
        this.challan_qty = challan_qty;
    }

    public int getTotal_enter_qty() {
        return total_enter_qty;
    }

    public void setTotal_enter_qty(int total_enter_qty) {
        this.total_enter_qty = total_enter_qty;
    }

    public String getChallan_item() {
        return challan_item;
    }

    public void setChallan_item(String challan_item) {
        this.challan_item = challan_item;
    }
}
