package com.task.appv2.data.source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.task.appv2.data.source.local.entities.OrderDetailEntity;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.pojo.OrderWithDetails;
import com.task.appv2.data.source.local.entries.OrderMasterEntries;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrderMaster(OrderMasterEntity orderMaster);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrderDetails(List<OrderDetailEntity> orderDetails);


    @Transaction
    @Query("SELECT * FROM OrderMasterEntity ")
    List<OrderWithDetails> getAllOrders();

    @Transaction
    @Query("SELECT * FROM OrderMasterEntity ")
    LiveData<List<OrderWithDetails>> getOrderWithDetailsLiveData();

    @Query("SELECT * FROM OrderMasterEntity ")
    DataSource.Factory<Integer, OrderMasterEntity>getAllOrdersPagingSource();

    @Query("DELETE FROM  OrderMasterEntity")
    void deleteAllOrderMasters();
    @Query("DELETE FROM  OrderDetailEntity")
    void deleteAllOrderDetails();

    @Query("DELETE FROM " + OrderMasterEntries.TABLE_NAME + " WHERE " + OrderMasterEntries.COL_ORDER_SRL + " = :orderSrl")
    void deleteOrderBySrl(String orderSrl);

}

