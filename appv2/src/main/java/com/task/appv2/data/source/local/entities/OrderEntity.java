package com.task.appv2.data.data_source.local.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderEntity {
    @SerializedName("OrderMasterList")
    private List<OrderMasterEntity> orderMasterList;

    public List<OrderMasterEntity> getOrderMasterList() {
        return orderMasterList;
    }

    public void setOrderMasterList(List<OrderMasterEntity> orderMasterList) {
        this.orderMasterList = orderMasterList;
    }
}
