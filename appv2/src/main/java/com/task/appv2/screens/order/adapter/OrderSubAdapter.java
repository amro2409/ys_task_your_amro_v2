package com.task.appv2.screens.order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.task.appv2.R;
import com.task.appv2.data.source.local.entities.OrderDetailEntity;
import com.task.appv2.databinding.OrderSubListItemBinding;
import com.task.appv2.utils.OrderHelper;
import com.task.appv2.utils.constants.OrderStsFlagsConstant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrderSubAdapter extends RecyclerView.Adapter<OrderSubAdapter.SubViewHolder> {
    private final List<OrderDetailEntity> subItems = new ArrayList<>();

    public void submitList(List<OrderDetailEntity> newList) {
        subItems.clear();
        subItems.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderSubListItemBinding binding = OrderSubListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new SubViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        holder.bind(subItems.get(position));
    }

    @Override
    public int getItemCount() {
        return subItems.size();
    }

    static class SubViewHolder extends RecyclerView.ViewHolder {
        private final OrderSubListItemBinding binding;

        public SubViewHolder(@NonNull OrderSubListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("ResourceAsColor")
        public void bind(@NotNull OrderDetailEntity item) {
            binding.itemQtyAndName.setText(String.format("%sX %s", item.getItemQuantity(), item.getItemName()));

            final String statusName =
                    OrderHelper.convertValueOrderStsOrCnlFlg2Name(item.getItemStatus(), item.getCancelFlag());

            updateUISubCard(statusName, binding);

        }

        void updateUISubCard(String status, OrderSubListItemBinding itemBinding) {
            final Context context = itemBinding.getRoot().getContext();
            int backgroundRes;
            int colorRes;
            switch (status) {
                case OrderStsFlagsConstant.NAME_STS_NEW:
                    backgroundRes = R.drawable.card_order_list_green;

                    colorRes = R.color.on_green_container;
                    break;
                case OrderStsFlagsConstant.NAME_STS_CHANGES:
                    backgroundRes = R.drawable.card_order_list_change;

                    colorRes = R.color.on_primary_container;
                    break;
                case OrderStsFlagsConstant.NAME_STS_DELAYED:
                    backgroundRes = R.drawable.card_order_list_red;

                    colorRes = R.color.on_holo_red_container;
                    break;
                case OrderStsFlagsConstant.NAME_FLG_CANCELED:
                    backgroundRes = R.drawable.card_order_list_gray;
                    colorRes = R.color.on_gray_container;
                    break;
                default:
                    backgroundRes = R.drawable.card_order_list_default;
                    colorRes = R.color.on_green_container;

            }
            //----------start update UI STATE AS response to change data and redraw views in screen
            itemBinding.getRoot().setBackground(AppCompatResources.getDrawable(context, backgroundRes));
            itemBinding.itemStIndicator.setBackgroundResource(colorRes);
        }

    }
}

