package com.task.appv2.data.data_source.local.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.task.appv2.data.data_source.local.entries.OrderDetailEntries;
import com.task.appv2.data.data_source.local.entries.OrderMasterEntries;

@Entity(tableName ="OrderDetailEntity",
        foreignKeys = @ForeignKey(entity = OrderMasterEntity.class,
                parentColumns = OrderMasterEntries.COL_ORDER_SRL,
                childColumns = OrderDetailEntries.COL_ORDER_SRL_FK,
                onDelete = ForeignKey.CASCADE))
public class OrderDetailEntity {

    private String orderSrlFk;

    @NonNull
    @PrimaryKey
    @SerializedName("ITM_SRL")
    private String itemSerial;

    @SerializedName("I_CODE")
    private String itemCode;

    @SerializedName("ITM_NM")
    private String itemName;

    @SerializedName("ITM_UNT")
    private String itemUnit;

    @SerializedName("I_QTY")
    private String itemQuantity;

    @SerializedName("ITM_DSC")
    private String itemDescription;

    @SerializedName("PRCSSD_FLG")
    private String processedFlag;

    @SerializedName("PRNT_DATE")
    private String printDate;

    @SerializedName("CNCL_FLG")
    private String cancelFlag;

    @SerializedName("ITM_STS")
    private String itemStatus;


    public String getOrderSrlFk() {
        return orderSrlFk;
    }

    public void setOrderSrlFk(String orderSrlFk) {
        this.orderSrlFk = orderSrlFk;
    }

    @NonNull
    public String getItemSerial() {
        return itemSerial;
    }

    public void setItemSerial(@NonNull String itemSerial) {
        this.itemSerial = itemSerial;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getProcessedFlag() {
        return processedFlag;
    }

    public void setProcessedFlag(String processedFlag) {
        this.processedFlag = processedFlag;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }


}
