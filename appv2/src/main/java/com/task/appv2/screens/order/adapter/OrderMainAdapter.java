package com.task.appv2.screens.order.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.task.appv2.R;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.pojo.OrderWithDetails;
import com.task.appv2.databinding.OrderMainListCardBinding;
import com.task.appv2.screens.order.OrderViewModel;
import com.task.appv2.utils.OrderHelper;
import com.task.appv2.utils.constants.OrderStsFlagsConstant;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OrderMainAdapter extends PagedListAdapter<OrderWithDetails,  RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ORDER = 0;
    private static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_NO_RESULT = 2;

    public OrderMainAdapter() {
        super(new DIFF_CALLBACK_ORDER());
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("OrderMainAdapter", "getItemViewType ");
        if (super.getItemCount() == 0) {
            return VIEW_TYPE_NO_RESULT;
        } else {
            OrderMasterEntity item = Objects.requireNonNull(getItem(position)).orderMaster;
            return (item == null) ? VIEW_TYPE_LOADING : VIEW_TYPE_ORDER;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("OrderMainAdapter", "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_ORDER) {
            OrderMainListCardBinding binding = OrderMainListCardBinding.inflate(inflater, parent, false);
            return new OrderViewHolder(binding);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.layout_item_loading, parent, false);
            return new LoadingViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.layout_item_no_result, parent, false);
            return new NoResultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OrderViewHolder) {
            OrderWithDetails orderWithDetails=   Objects.requireNonNull(getItem(position));
            OrderMasterEntity order = orderWithDetails.orderMaster;
            if (order != null) {
                order.setOrdrDtl(orderWithDetails.orderDetails);
                ((OrderViewHolder) holder).bind(order);
            }
        }
    }

    @Override
    public int getItemCount() {
        return  super.getItemCount();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        private final OrderMainListCardBinding binding;
        private final OrderSubAdapter subAdapter;

        public OrderViewHolder(@NonNull OrderMainListCardBinding mainListBinding) {
            super(mainListBinding.getRoot());
            binding = mainListBinding;
            subAdapter= new OrderSubAdapter();

            binding.itemSubList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
            binding.itemSubList.setAdapter(subAdapter);
        }

        public void bind(@NotNull OrderMasterEntity order) {
            final String statusName =
                    OrderHelper.convertValueOrderStsOrCnlFlg2Name(order.getOrdrSts(), order.getCnclFlg());
            binding.itemOrderStatusTv.setText(statusName);

            binding.itemPssdTimeTv.setText(String.format("%s", order.getPssdTm()));

            binding.itemOrderNoTv.setText(String.format("# Order No:%s", order.getBillNo()));

            binding.itemOrderTypeTv.setText(order.getBillDocTypeNm());
            binding.itemBillTimeTv.setText(order.getBillTime());

            //--
            updateUICard(statusName, binding);
            //--parse sub list data
            subAdapter.submitList(order.getOrdrDtl());
        }

        void updateUICard(String status, OrderMainListCardBinding cardBinding) {
            final Context context = cardBinding.getRoot().getContext();
            int backgroundRes;
            int iconResStatus;
            int colorRes;
            switch (status) {
                case OrderStsFlagsConstant.NAME_STS_NEW:
                    backgroundRes = R.drawable.card_order_list_green;
                    iconResStatus = R.drawable.ic_new;
                    colorRes = R.color.on_green_container;
                    break;
                case OrderStsFlagsConstant.NAME_STS_CHANGES:
                    backgroundRes = R.drawable.card_order_list_change;
                    iconResStatus = R.drawable.ic_changed;
                    colorRes = R.color.on_primary_container;
                    break;
                case OrderStsFlagsConstant.NAME_STS_DELAYED:
                    backgroundRes = R.drawable.card_order_list_red;
                    iconResStatus = R.drawable.ic_delayed;
                    colorRes = R.color.on_holo_red_container;
                    break;
                case OrderStsFlagsConstant.NAME_FLG_CANCELED:
                    backgroundRes = R.drawable.card_order_list_gray;
                    iconResStatus = R.drawable.ic_cancelled;
                    colorRes = R.color.on_gray_container;
                    break;
                default:
                    backgroundRes = R.drawable.card_order_list_default;
                    iconResStatus = R.drawable.ic_default;
                    colorRes = R.color.on_green_container;

            }
            //----------start update UI STATE AS response to change data and redraw in screen
            cardBinding.getRoot().setBackground(AppCompatResources.getDrawable(context, backgroundRes));
            cardBinding.itemOrderStatusIv.setImageDrawable(AppCompatResources.getDrawable(context, iconResStatus));

            int resolvedColor = ContextCompat.getColor(context, colorRes);

            cardBinding.itemOrderStatusTv.setTextColor(resolvedColor);
            cardBinding.itemOrderNoTv.setTextColor(resolvedColor);
        }

    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(View view) {
            super(view);
        }
    }

    public static class NoResultViewHolder extends RecyclerView.ViewHolder {
        public NoResultViewHolder(View view) {
            super(view);
        }
    }

    //============================================================================//
    public static class DIFF_CALLBACK_ORDER extends DiffUtil.ItemCallback<OrderWithDetails> {
        @Override
        public boolean areItemsTheSame(@NonNull OrderWithDetails oldItem, @NonNull OrderWithDetails newItem) {
            return oldItem.orderMaster.getOrderSrl().equals(newItem.orderMaster.getOrderSrl());
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderWithDetails oldItem, @NonNull OrderWithDetails newItem) {
            return oldItem.orderMaster.equals(newItem.orderMaster);
        }
    }

}
