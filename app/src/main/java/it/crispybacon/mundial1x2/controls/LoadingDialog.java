package it.crispybacon.mundial1x2.controls;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;

/**
 * Created by Jameido on 15/06/2018.
 */

public class LoadingDialog extends Dialog {

    public static final String TAG = "LoadingDialog";
    private final int SHOW_RESULT_DURATION = 1000;//ms

    public LoadingDialog(@NonNull Context context) {
        super(context, false, null);
    }

    public static LoadingDialog newInstance(Context aContext) {
        LoadingDialog vLoadingDialog = new LoadingDialog(aContext);
        vLoadingDialog.setContentView(R.layout.dialog_loading);

        return vLoadingDialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        if(window!=null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setBackgroundDrawable(null);
        }
    }
}
