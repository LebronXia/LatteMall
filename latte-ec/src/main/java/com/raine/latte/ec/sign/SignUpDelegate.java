package com.raine.latte.ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.raine.latte.delegates.LatteDelegate;
import com.raine.latte.ec.R;
import com.raine.latte.ec.R2;
import com.raine.latte.log.LatteLogger;
import com.raine.latte.net.RestClient;
import com.raine.latte.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhengxiaobo on 2017/12/20.
 */

public class SignUpDelegate extends LatteDelegate{
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){
            //Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
                    .url("http://127.0.0.1:8080/RestServer/api/user_profile.php")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String reponse) {
                            LatteLogger.json("USER_PROFILE", reponse);
                        }
                    })
                    .build()
                    .post();

        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        getSupportDelegate().start(new SignInDelegate());
    }

    public boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()){
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
