package com.task.appv2.screens.order.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.pojo.OrderWithDetails;
import com.task.appv2.databinding.FragmentListOrderBinding;
import com.task.appv2.screens.order.OrderViewModel;
import com.task.appv2.screens.order.adapter.OrderMainAdapter;

import java.util.Locale;

public class ListOrderFragment extends Fragment {
    private final String TAG=ListOrderFragment.class.getSimpleName() ;

    FragmentListOrderBinding listBinding;
    private OrderMainAdapter mainAdapter;
    private OrderViewModel viewModel;
    private int orderNumber=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listBinding = FragmentListOrderBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        // init adapter
        mainAdapter = new OrderMainAdapter();
        // setup list
        //listBinding.orderList.setHasFixedSize(true);
        listBinding.orderList.setLayoutManager(new LinearLayoutManager(listBinding.getRoot().getContext(),LinearLayoutManager.HORIZONTAL,false));

        listBinding.orderList.setAdapter(mainAdapter);

        viewModel.getOrders().observe(getViewLifecycleOwner(), orderMasterEntities -> {
            Log.d(TAG, "onChanged: orderMasterEntities.size: " + orderMasterEntities.size());
            mainAdapter.submitList(orderMasterEntities);
            orderNumber = orderMasterEntities.size();
            listBinding.paginationBar.ordersTitle.setText(String.format(Locale.ENGLISH,"Orders(%d)", orderNumber));
        });
        //------

        return listBinding.getRoot();
    }


}