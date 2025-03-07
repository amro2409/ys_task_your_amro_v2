package com.task.appv2.widgets.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.task.appv2.R;
import com.task.appv2.widgets.dialog.sweet_alert.ProgressHelper;
import com.task.appv2.widgets.dialog.sweet_alert.SweetAlertDialog;
import com.task.appv2.widgets.dialog.sweet_alert.progress.ProgressWheel0;


public class CustomDialog {

    public interface OnDialogListener {
        void onAccept();

        default void onCancel() {
        }

        default void onFinish() {
        }
    }

    static class ParamsEntry {
        //define variables
        private String title;
        private String msg;
        public int res_id_title, res_id_msg;

        public ParamsEntry() {
            res_id_title = R.string.title_update_dialog_note;
            res_id_msg = R.string.msg_update_dialog_note;
        }

        //methods: getter and setter
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        //check if found title and content include in any action do require for show in alert dialog
        // else default show
        public void checkReceivesMessage(Context context, String... s) {
            if (s != null)
                if (s.length > 0) {
                    setTitle(s.length > 1 ? s[0] : context.getString(res_id_title));
                    setMsg(s.length == 2 ? s[1] : context.getString(res_id_msg));
                } else {
                    setTitle(context.getString(res_id_title));
                    setMsg(context.getString(res_id_msg));
                }
        }
    }

    interface Alert {
        void showAlertRender(Context context, String... message);

        default void hidAlert() {
        }
    }

    //this : product that implement to base
    static class ErrorAlert extends ParamsEntry implements Alert {
        private final OnDialogListener alertDialog;

        public ErrorAlert(OnDialogListener alertDialog) {
            //super();
            this.alertDialog = alertDialog;
            res_id_title = R.string.title_update_dialog_error;
            res_id_msg = R.string.msg_update_dialog_error;
        }

        @Override
        public void showAlertRender(Context context, String... msg) {
            checkReceivesMessage(context, msg);
            /*          new SweetAlertDialog(context*//*,SweetAlertDialog.ERROR_TYPE*//*).setTitleText(getTitle())
                    .setContentText(String.format("<font color='#F27474'>%s</font>", getMsg())).show();*/

            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context).setTitleText(getTitle())
                    .setContentText(String.format("<font color='#F27474'>%s</font>", getMsg()))
                    .setConfirmButton(context.getString(R.string.ok), sweetAlertDialog1 -> {
                        alertDialog.onAccept();
                        sweetAlertDialog1.cancel();
                    });
            sweetAlertDialog.setOnKeyListener((dialog, keyCode, event) -> {
                dialog.cancel();
                return false;
            });
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.show();
            System.out.println("ErrorAlert");
        }
    }

    static class NotifyAlert extends ParamsEntry implements Alert {
        private final OnDialogListener alertDialog;

        public NotifyAlert(OnDialogListener alertDialog) {
            super();
            this.alertDialog = alertDialog;
        }

        @Override
        public void showAlertRender(Context context, String... msg) {
            checkReceivesMessage(context, msg);

            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context)
                    .setTitleText(getTitle()).setContentText(getMsg())
                    .setConfirmButton(context.getString(R.string.ok), sweetAlertDialog1 -> {
                        alertDialog.onAccept();
                        sweetAlertDialog1.cancel();
                    });
            sweetAlertDialog.setOnKeyListener((dialog, keyCode, event) -> {
                dialog.cancel();
                return false;
            });
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.show();
            System.out.println("NotifyAlert");
        }
    }

    static class NoteWithCancelAlert extends ParamsEntry implements Alert {
        private final OnDialogListener alertDialog;

        public NoteWithCancelAlert(OnDialogListener alertDialog) {
            super();
            this.alertDialog = alertDialog;
        }

        @Override
        public void showAlertRender(Context context, String... msg) {
            checkReceivesMessage(context, msg);
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context/*,
                       SweetAlertDialog.WARNING_TYPE*/).setTitleText(getTitle()).setContentText(getMsg())
                    .setConfirmText(context.getString(R.string.ok)).setConfirmClickListener(sweetAlertDialog12 -> {
                        alertDialog.onAccept();
                        sweetAlertDialog12.cancel();
                    }).setCancelButton(context.getString(R.string.cancel), sweetAlertDialog1 -> {
                        alertDialog.onCancel();
                        sweetAlertDialog1.cancel();
                    });
            sweetAlertDialog.setOnKeyListener((dialog, keyCode, event) -> {
                dialog.cancel();
                return false;
            });
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.show();
        }
    }

    static class SureAlert extends ParamsEntry implements Alert {
        private final OnDialogListener alertDialog;

        public SureAlert(OnDialogListener alertDialog) {
            super();
            this.alertDialog = alertDialog;
        }

        @Override
        public void showAlertRender(Context context, String... msg) {
            checkReceivesMessage(context, msg);
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context,
                    SweetAlertDialog.WARNING_TYPE).setTitleText(getTitle()).setContentText(getMsg());

            sweetAlertDialog.setConfirmText("<font color='#FFFFFF'>" + context.getString(R.string.sure) + "</font>")
                    .setConfirmClickListener(sDialog -> {
                        //change dialog style upon confirm 1{
                        alertDialog.onAccept();
                        sDialog.setTitleText(" Successfully. !")
                                .setContentText(context.getString(R.string.ok_success))
                                .setConfirmText(context.getString(R.string.ok))
                                .setConfirmClickListener(sweetAlertDialog12 -> {
                                    sDialog.cancel();
                                    sweetAlertDialog12.dismissWithAnimation();
                                    alertDialog.onFinish();
                                })
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    });
            sweetAlertDialog.setCancelText(context.getString(R.string.cancel))
                    .setCancelClickListener(sDialog -> {
                        //sDialog.dismissWithAnimation();
                        sDialog.setTitleText(context.getString(android.R.string.cancel))
                                .setContentText(context.getString(R.string.cancel_err))
                                .setConfirmText(context.getString(R.string.ok))
                                .setConfirmClickListener(sweetAlertDialog1 -> {
                                    sweetAlertDialog1.dismissWithAnimation();
                                    sDialog.dismissWithAnimation();
                                    alertDialog.onCancel();
                                }).changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    });
            sweetAlertDialog.setOnKeyListener((dialog, keyCode, event) -> {
                dialog.cancel();
                return false;
            });
        }
    }

    static class SuccessAlert extends ParamsEntry implements Alert {

        public SuccessAlert() {
            super();
        }

        @Override
        public void showAlertRender(Context context, String... msg) {
            checkReceivesMessage(context, msg);
            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(context.getString(R.string.ok_success))
                    .setContentText("<font color='#60bf04'>" + getMsg() + "</font>")
                    .setConfirmText(context.getString(R.string.ok)).setConfirmClickListener(null)
                    .show();
        }
    }

    static class ProgressAlert extends ParamsEntry implements Alert {
        private SweetAlertDialog sweetAlertDialog;

        public ProgressAlert() {
            res_id_title = R.string.title_progress_notify;
            res_id_msg = R.string.label_text_progress_notify;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void showAlertRender(Context context, String... msg) {
            checkReceivesMessage(context, msg);
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            ProgressHelper progressHelper= sweetAlertDialog.getProgressHelper();
            progressHelper .setBarColor(Color.parseColor("#FF9800"));
           // progressHelper.setSpinSpeed(0.15F);
           // progressHelper.setInstantProgress(0.4F);
            progressHelper.setRimColor(Color.LTGRAY);
            progressHelper.setRimWidth(12);
            progressHelper.setProgressWheel(new ProgressWheel0(context));
            sweetAlertDialog//.setTitleText(getTitle())
                    .setContentText("<font color='#1A70B9'>" + getMsg() + "</font>");

            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.show();
        }

        @Override
        public void hidAlert() {
            sweetAlertDialog.cancel();
        }
    }


 /*   class DialogBaseProvider {
        Class<?> sAClass;

        public DialogBaseProvider(Class<?> sAClass) {
            this.sAClass = sAClass;
        }

    }*/

    //=================================
    //Factory
    abstract static class DialogFactory {
        /* protected final OnDialogListener alertDialog;
         public DialogBase(OnDialogListener alertDialog) {
             this.alertDialog = alertDialog;
         }*/
        Alert alert;

        /***
         * method factory Is implemented in sub class
         * @return new instance object any subclass from Alert
         */
        abstract Alert createAlert();

        protected void showDialog(Context context, String... msg) {
            alert = this.createAlert();
            alert.showAlertRender(context, msg);
        }

        public void hidden() {
            alert.hidAlert();
        }
    }

// sub classes

    public static class ErrorAlertDialogFactory extends DialogFactory {
        private final OnDialogListener alertDialog;

        public ErrorAlertDialogFactory(OnDialogListener alertDialog) {
            this.alertDialog = alertDialog;
        }

        @Override
        Alert createAlert() {
            return new ErrorAlert(alertDialog);
        }
    }

    public static class NoteAlertDialogFactory extends DialogFactory {
        private final OnDialogListener alertDialog;

        public NoteAlertDialogFactory(OnDialogListener alertDialog) {
            this.alertDialog = alertDialog;
        }

        @Override
        Alert createAlert() {
            return new NotifyAlert(alertDialog);
        }
    }

    public static class NoteWithCancelAlertDialogFactory extends DialogFactory {
        private final OnDialogListener alertDialog;

        public NoteWithCancelAlertDialogFactory(OnDialogListener alertDialog) {
            this.alertDialog = alertDialog;
        }

        @Override
        Alert createAlert() {
            return new NoteWithCancelAlert(alertDialog);
        }
    }

    public static class SureAlertDialogFactory extends DialogFactory {
        private final OnDialogListener dialogListener;
        private static SureAlertDialogFactory sInstance;

        public static SureAlertDialogFactory getsInstance(OnDialogListener alertDialog) {
            if (sInstance == null) sInstance = new SureAlertDialogFactory(alertDialog);
            return sInstance;
        }

        public SureAlertDialogFactory(OnDialogListener alertDialog) {
            this.dialogListener = alertDialog;
        }

        @Override
        Alert createAlert() {
            return new SureAlert(dialogListener);
        }
    }

    public static class SuccessAlertDialogFactory extends DialogFactory {

        @Override
        Alert createAlert() {
            return new SuccessAlert();
        }

    }

    public static class ProgressAlertDialogFactory extends DialogFactory {
        private static ProgressAlertDialogFactory instance;

        public static ProgressAlertDialogFactory getInstance() {
            synchronized (ProgressAlertDialogFactory.class) {
                if (instance == null) {
                    instance = new ProgressAlertDialogFactory();
                }
            }
            return instance;
        }

        @Override
        Alert createAlert() {
            return new ProgressAlert();
        }
    }
    //==================================

    public static void setDialogCallback(Context context, @NonNull DialogFactory dialog, String... msg) {
        dialog.showDialog(context, msg);
        Log.d("customdialog", "setDialogCallback() called with: context = [" + context + "], dialog = [" + dialog + "], msg = [" + msg + "]");
        //final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    }

}
