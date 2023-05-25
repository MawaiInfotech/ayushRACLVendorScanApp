package com.mawai.raclvendorscanapp.schedule.model;

public class ItemModel {


    private String vendorcode;
    private String scanbincode;
    private String itemcode;
    private String sch_no;
    private String scan_by;
    private String bin_vendor_code;
    private String bin_code;
    private String bin_item_code;
    private String item_desc;
    private int bin_capacity;
    private String unit_cd;

    public ItemModel() {
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

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getScan_by() {
        return scan_by;
    }

    public void setScan_by(String scan_by) {
        this.scan_by = scan_by;
    }

    public String getBin_vendor_code() {
        return bin_vendor_code;
    }

    public void setBin_vendor_code(String bin_vendor_code) {
        this.bin_vendor_code = bin_vendor_code;
    }

    public String getBin_code() {
        return bin_code;
    }

    public void setBin_code(String bin_code) {
        this.bin_code = bin_code;
    }

    public String getBin_item_code() {
        return bin_item_code;
    }

    public void setBin_item_code(String bin_item_code) {
        this.bin_item_code = bin_item_code;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public int getBin_capacity() {
        return bin_capacity;
    }

    public void setBin_capacity(int bin_capacity) {
        this.bin_capacity = bin_capacity;
    }

    public String getUnit_cd() {
        return unit_cd;
    }

    public void setUnit_cd(String unit_cd) {
        this.unit_cd = unit_cd;
    }

    public String getSch_no() {
        return sch_no;
    }

    public void setSch_no(String sch_no) {
        this.sch_no = sch_no;
    }
}
