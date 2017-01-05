package com.fwk.school4.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fwk.school4.weight.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Created by fanwenke on 16/12/5.
 */

public abstract class BasaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        init();
    }

    public abstract int getLayoutId();
    public abstract void init();

    private Dialog dialog;
    public void showDialog(){
        if (dialog == null){
            dialog = LoadingDialog.createLoadingDialog(this,"正在加载中...");
            dialog.show();
        }
    }

    public void closeDialog(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
