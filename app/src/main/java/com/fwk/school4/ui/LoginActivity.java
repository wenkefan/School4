package com.fwk.school4.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.fwk.school4.R;
import com.fwk.school4.constant.SpLogin;
import com.fwk.school4.model.LoginBean;
import com.fwk.school4.network.HTTPURL;
import com.fwk.school4.network.OKHttp;
import com.fwk.school4.listener.OnSucceedListener;
import com.fwk.school4.utils.DataVerifyUtils;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements OnSucceedListener {

    private static final int LOGION = 1;
    @InjectView(R.id.et_user_name)
    EditText etUserName;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    private String username;//登录名称
    private String password;//登录密码
    private LoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolcar4_activity_login);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        if (SpLogin.getAlreadyLogin()) {
            //直接跳转
            callIntent();
        }
        super.onResume();
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        showDialog();
        username = etUserName.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if (DataVerifyUtils.VerifyData(username, password)) {
            String url = String.format(HTTPURL.API_LOGIN, username, password);
            require(LOGION, url, LoginBean.class);
        }else {
            closeDialog();
        }
    }

    private void require(int flag, String url, Class cla) {
        OKHttp okhttp = new OKHttp();
        okhttp.setListener(this);
        LogUtils.d("登录URL：" + url);
        okhttp.request(flag, url, cla);
    }


    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null){
            closeDialog();
            if (flag == LOGION){
                loginBean = (LoginBean) cla;
                if (loginBean.getRerurnValue() != null){
                    SpLogin.setAlreadyLogin(true);
                    SpLogin.save(username, password, loginBean.getRerurnValue().getKgId(), (String) loginBean.getRerurnValue().getKgName(), loginBean.getRerurnValue().getName(), loginBean.getRerurnValue().getUserId(), loginBean.getRerurnValue().getWorkerExtensionId());
                    handler.sendEmptyMessage(LOGION);
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(loginBean.getMessage());
                        }
                    });
                }
            }
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(message);
                }
            });
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == LOGION){
                callIntent();
            }
        }
    };

    @Override
    public void Error() {

    }

    private void callIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private ProgressDialog progressDialog;

    private void showDialog(){
        if (progressDialog == null){

            progressDialog = new ProgressDialog(this);

        }

        progressDialog.setMessage("正在加载中...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    private void closeDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
