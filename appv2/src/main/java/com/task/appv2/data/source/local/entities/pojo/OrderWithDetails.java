package com.task.appv2.data.source.local.entities.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.task.appv2.data.source.local.entities.OrderDetailEntity;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entries.OrderDetailEntries;
import com.task.appv2.data.source.local.entries.OrderMasterEntries;

import java.util.List;

public class OrderWithDetails {

    @Embedded
    public OrderMasterEntity orderMaster;

    @Relation(
            parentColumn = OrderMasterEntries.COL_ORDER_SRL,
            entityColumn = OrderDetailEntries.COL_ORDER_SRL_FK
    )
    public List<OrderDetailEntity> orderDetails;

}

