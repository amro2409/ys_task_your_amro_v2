package com.task.appv2.utils;

import com.task.appv2.utils.constants.OrderStsFlagsConstant;

public class OrderHelper {

    public static String convertValueOrderStsOrCnlFlg2Name(String orderStatus, String cancelFlg) {
        String status = " Unknown st ";

         if (cancelFlg == null) {
            return status;
        }

        if (cancelFlg.equals("1")) {
            status = OrderStsFlagsConstant.NAME_FLG_CANCELED;
            return status;
        }

        switch (orderStatus) {
            case "1":
                status = OrderStsFlagsConstant.NAME_STS_NEW;
                break;
            case "2":
                status = OrderStsFlagsConstant.NAME_STS_CHANGES;
                break;
            case "3":
                status = OrderStsFlagsConstant.NAME_STS_DELAYED;
                break;
            default:
                throw new IllegalArgumentException("Unknown arg value status: '" + orderStatus + "'");
        }
        return status;
    }
}
