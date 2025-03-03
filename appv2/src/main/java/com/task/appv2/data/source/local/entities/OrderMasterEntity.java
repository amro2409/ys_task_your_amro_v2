package com.task.appv2.data.source.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "OrderMasterEntity")

public class OrderMasterEntity {

    @NonNull
    @SerializedName("ORDR_SRL")
    @PrimaryKey
    private String orderSrl; // ORDR_SRL
    @SerializedName("BILL_NO")
    private String billNo; // BILL_NO
    @SerializedName("TRMNL_NM")
    private String trmnlNm; // TRMNL_NM
    @SerializedName("TRMNL_NO")
    private String trmnlNo; // TRMNL_NO

    @SerializedName("BILL_DATE")
    private String billDate; // BILL_DATE

    @SerializedName("BILL_TIME")
    private String billTime; // BILL_TIME

    @SerializedName("ORDR_NOTE")
    private String orderNote; // ORDR_NOTE

    @SerializedName("BILL_DOC_TYPE")
    private String billDocType; // BILL_DOC_TYPE

    @SerializedName("BILL_DOC_TYPE_NM")
    private String billDocTypeNm; // BILL_DOC_TYPE_NM

    @SerializedName("LAST_UPDT")
    private String lastUpdt; // LAST_UPDT

    @SerializedName("CNCL_FLG")
    private String cnclFlg; // CNCL_FLG

    @SerializedName("PRCSSD_FLG")
    private String prcssdFlg; // PRCSSD_FLG

    @SerializedName("PRCSSD_U_ID")
    private String prcssdUId; // PRCSSD_U_ID

    @SerializedName("PRCSSD_DATE")
    private String prcssdDate; // PRCSSD_DATE

    @SerializedName("AD_DATE")
    private String adDate; // AD_DATE

    @SerializedName("ORDR_TRC_FLG")
    private String ordrTrcFlg; // ORDR_TRC_FLG

    @SerializedName("PSSD_TM")
    private String pssdTm; // PSSD_TM

    @SerializedName("ORDR_STS")
    private String ordrSts; // ORDR_STS

    @SerializedName("BILL_SRL")
    private String billSrl; // BILL_SRL

    @SerializedName("BILL_SRVC_TYPE")
    private String billSrvcType; // BILL_SRVC_TYPE

    @SerializedName("BRN_NO")
    private String brnNo; // BRN_NO

    @SerializedName("HALL_NO")
    private String hallNo; // HALL_NO

    @SerializedName("PRCSS_TM")
    private String prcssTm; // PRCSS_TM

    @SerializedName("PRNT_DATE")
    private String prntDate; // PRNT_DATE

    @SerializedName("STG_NO")
    private String stgNo; // STG_NO

    @SerializedName("STG_ORDR")
    private String stgOrdr; // STG_ORDR

    @SerializedName("TBL_NO")
    private String tblNo; // TBL_NO

    @Ignore
    private List<OrderDetailEntity> ordrDtl;



    // Getters & Setters


    public List<OrderDetailEntity> getOrdrDtl() {
        return ordrDtl;
    }

    public void setOrdrDtl(List<OrderDetailEntity> ordrDtl) {
        this.ordrDtl = ordrDtl;
    }

    @NonNull
    public String getOrderSrl() {
        return orderSrl;
    }

    public void setOrderSrl(@NonNull String orderSrl) {
        this.orderSrl = orderSrl;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getBillDocType() {
        return billDocType;
    }

    public void setBillDocType(String billDocType) {
        this.billDocType = billDocType;
    }

    public String getBillDocTypeNm() {
        return billDocTypeNm;
    }

    public void setBillDocTypeNm(String billDocTypeNm) {
        this.billDocTypeNm = billDocTypeNm;
    }

    public String getLastUpdt() {
        return lastUpdt;
    }

    public void setLastUpdt(String lastUpdt) {
        this.lastUpdt = lastUpdt;
    }

    public String getCnclFlg() {
        return cnclFlg;
    }

    public void setCnclFlg(String cnclFlg) {
        this.cnclFlg = cnclFlg;
    }

    public String getPrcssdFlg() {
        return prcssdFlg;
    }

    public void setPrcssdFlg(String prcssdFlg) {
        this.prcssdFlg = prcssdFlg;
    }

    public String getPrcssdUId() {
        return prcssdUId;
    }

    public void setPrcssdUId(String prcssdUId) {
        this.prcssdUId = prcssdUId;
    }

    public String getPrcssdDate() {
        return prcssdDate;
    }

    public void setPrcssdDate(String prcssdDate) {
        this.prcssdDate = prcssdDate;
    }

    public String getAdDate() {
        return adDate;
    }

    public void setAdDate(String adDate) {
        this.adDate = adDate;
    }

    public String getOrdrTrcFlg() {
        return ordrTrcFlg;
    }

    public void setOrdrTrcFlg(String ordrTrcFlg) {
        this.ordrTrcFlg = ordrTrcFlg;
    }

    public String getPssdTm() {
        return pssdTm;
    }

    public void setPssdTm(String pssdTm) {
        this.pssdTm = pssdTm;
    }

    public String getOrdrSts() {
        return ordrSts;
    }

    public void setOrdrSts(String ordrSts) {
        this.ordrSts = ordrSts;
    }

    public String getBillSrl() {
        return billSrl;
    }

    public void setBillSrl(String billSrl) {
        this.billSrl = billSrl;
    }

    public String getBillSrvcType() {
        return billSrvcType;
    }

    public void setBillSrvcType(String billSrvcType) {
        this.billSrvcType = billSrvcType;
    }

    public String getBrnNo() {
        return brnNo;
    }

    public void setBrnNo(String brnNo) {
        this.brnNo = brnNo;
    }

    public String getHallNo() {
        return hallNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }

    public String getPrcssTm() {
        return prcssTm;
    }

    public void setPrcssTm(String prcssTm) {
        this.prcssTm = prcssTm;
    }

    public String getPrntDate() {
        return prntDate;
    }

    public void setPrntDate(String prntDate) {
        this.prntDate = prntDate;
    }

    public String getStgNo() {
        return stgNo;
    }

    public void setStgNo(String stgNo) {
        this.stgNo = stgNo;
    }

    public String getStgOrdr() {
        return stgOrdr;
    }

    public void setStgOrdr(String stgOrdr) {
        this.stgOrdr = stgOrdr;
    }

    public String getTblNo() {
        return tblNo;
    }

    public void setTblNo(String tblNo) {
        this.tblNo = tblNo;
    }

    public String getTrmnlNm() {
        return trmnlNm;
    }

    public void setTrmnlNm(String trmnlNm) {
        this.trmnlNm = trmnlNm;
    }

    public String getTrmnlNo() {
        return trmnlNo;
    }

    public void setTrmnlNo(String trmnlNo) {
        this.trmnlNo = trmnlNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMasterEntity)) return false;
        OrderMasterEntity that = (OrderMasterEntity) o;
        return orderSrl.equals(that.orderSrl) &&
                Objects.equals(billNo, that.billNo) &&
                Objects.equals(billDate, that.billDate) &&
                Objects.equals(billTime, that.billTime) &&
                Objects.equals(orderNote, that.orderNote) &&
                Objects.equals(billDocType, that.billDocType) &&
                Objects.equals(billDocTypeNm, that.billDocTypeNm) &&
                Objects.equals(lastUpdt, that.lastUpdt) &&
                Objects.equals(cnclFlg, that.cnclFlg) &&
                Objects.equals(prcssdFlg, that.prcssdFlg) &&
                Objects.equals(prcssdUId, that.prcssdUId) &&
                Objects.equals(prcssdDate, that.prcssdDate) &&
                Objects.equals(adDate, that.adDate) &&
                Objects.equals(ordrTrcFlg, that.ordrTrcFlg) &&
                Objects.equals(pssdTm, that.pssdTm) &&
                Objects.equals(ordrSts, that.ordrSts) &&
                Objects.equals(billSrl, that.billSrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderSrl, billNo, billDate, billTime, orderNote, billDocType, billDocTypeNm, lastUpdt, cnclFlg, prcssdFlg, prcssdUId, prcssdDate, adDate, ordrTrcFlg, pssdTm, ordrSts, billSrl);
    }
}
