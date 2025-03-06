package com.task.appv2.screens.order.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.task.appv2.databinding.FragmentHistoryOrderBinding;

public class HistoryOrderFragment extends Fragment {
    FragmentHistoryOrderBinding historyOrderBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        historyOrderBinding = FragmentHistoryOrderBinding.inflate(inflater,container,false);
        return historyOrderBinding.getRoot();
    }

}
