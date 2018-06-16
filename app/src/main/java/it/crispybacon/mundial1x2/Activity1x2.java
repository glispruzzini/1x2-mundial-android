package it.crispybacon.mundial1x2;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import it.crispybacon.mundial1x2.ui.loading.LoadingDialog;

/**
 * Created by Jameido on 15/06/2018.
 */
public abstract class Activity1x2 extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    public void showLoadingDialog() {
        if (null == mLoadingDialog) {
            mLoadingDialog = LoadingDialog.newInstance(this);
        }
        mLoadingDialog.show();
    }

    public void hideLoadingDialog() {
        if (null != mLoadingDialog) {
            mLoadingDialog.dismiss();
        }
    }

    public void showSnackBar(final View rootView, final String message) {
        Snackbar vSnackbar = Snackbar.make(rootView, message, Toast.LENGTH_SHORT);
        vSnackbar.getView().getBackground().setAlpha(204);
        TextView tv = vSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setAllCaps(false);
        tv.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        vSnackbar.show();
    }
}
