package com.mawai.raclvendorscanapp.schedule.model;

import java.io.Serializable;

public class ScheduleModel implements Serializable {

   private String vendorcode;
   private String billno;
   private String challanno;
   private String challan_date;
   private int invoice_amt;
   private String transporter_id;
   private String vehicle_no;
   private String scanbincode;
   private String scan_by;
   private String itemcode;
   private String sl;
   private String po_no;
   private String sch_no;
   private String item_cd;
   private String item_desc;
   private String proc_cd;
   private String jw_code;
   private String amd_no;
   private String shc_amd_no;
   private String batch_code;
   private String schd_no;
   private String name;
   private String heat_code;
   private int tot_schd;
   private int balance_schd;
   private int rem_challan_qty;
   private int bin_qty;
   private int bal_qty;
   private int intered_challan_qty;

    public ScheduleModel() {
    }

    public String getAmd_no() {
        return amd_no;
    }

    public void setAmd_no(String amd_no) {
        this.amd_no = amd_no;
    }

    public String getShc_amd_no() {
        return shc_amd_no;
    }

    public void setShc_amd_no(String shc_amd_no) {
        this.shc_amd_no = shc_amd_no;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getChallanno() {
        return challanno;
    }

    public void setChallanno(String challanno) {
        this.challanno = challanno;
    }

    public String getChallan_date() {
        return challan_date;
    }

    public void setChallan_date(String challan_date) {
        this.challan_date = challan_date;
    }

    public int getInvoice_amt() {
        return invoice_amt;
    }

    public void setInvoice_amt(int invoice_amt) {
        this.invoice_amt = invoice_amt;
    }

    public String getTransporter_id() {
        return transporter_id;
    }

    public void setTransporter_id(String transporter_id) {
        this.transporter_id = transporter_id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getPo_no() {
        return po_no;
    }

    public void setPo_no(String po_no) {
        this.po_no = po_no;
    }

    public String getSch_no() {
        return sch_no;
    }

    public void setSch_no(String sch_no) {
        this.sch_no = sch_no;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public void setItem_cd(String item_cd) {
        this.item_cd = item_cd;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getProc_cd() {
        return proc_cd;
    }

    public void setProc_cd(String proc_cd) {
        this.proc_cd = proc_cd;
    }

    public String getJw_code() {
        return jw_code;
    }

    public void setJw_code(String jw_code) {
        this.jw_code = jw_code;
    }

    public int getTot_schd() {
        return tot_schd;
    }

    public void setTot_schd(int tot_schd) {
        this.tot_schd = tot_schd;
    }

    public int getBalance_schd() {
        return balance_schd;
    }

    public void setBalance_schd(int balance_schd) {
        this.balance_schd = balance_schd;
    }

    public int getRem_challan_qty() {
        return rem_challan_qty;
    }

    public void setRem_challan_qty(int rem_challan_qty) {
        this.rem_challan_qty = rem_challan_qty;
    }

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

    public String getScan_by() {
        return scan_by;
    }

    public void setScan_by(String scan_by) {
        this.scan_by = scan_by;
    }

    public int getBin_qty() {
        return bin_qty;
    }

    public void setBin_qty(int bin_qty) {
        this.bin_qty = bin_qty;
    }

    public int getBal_qty() {
        return bal_qty;
    }

    public void setBal_qty(int bal_qty) {
        this.bal_qty = bal_qty;
    }

    public int getIntered_challan_qty() {
        return intered_challan_qty;
    }

    public void setIntered_challan_qty(int intered_challan_qty) {
        this.intered_challan_qty = intered_challan_qty;
    }

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code;
    }

    public String getHeat_code() {
        return heat_code;
    }

    public void setHeat_code(String heat_code) {
        this.heat_code = heat_code;
    }

    public String getSchd_no() {
        return schd_no;
    }

    public void setSchd_no(String schd_no) {
        this.schd_no = schd_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
