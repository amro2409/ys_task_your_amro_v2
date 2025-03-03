package com.task.appv2.data.models;

import com.google.gson.annotations.SerializedName;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;

import java.util.List;

public class OrderData {
    @SerializedName("OrderMasterList")
    private List<OrderMasterEntity> orderMasterList;

    public List<OrderMasterEntity> getOrderMasterList() {
        return orderMasterList;
    }

    public void setOrderMasterList(List<OrderMasterEntity> orderMasterList) {
        this.orderMasterList = orderMasterList;
    }
}
