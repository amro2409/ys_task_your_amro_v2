package com.task.appv2.screens.order.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.task.appv2.databinding.FragmentSummaryOrderBinding;

public class SummaryOrderFragment extends Fragment {
   FragmentSummaryOrderBinding summaryOrderBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        summaryOrderBinding = FragmentSummaryOrderBinding.inflate(inflater,container,false);
        return summaryOrderBinding.getRoot();
    }


}