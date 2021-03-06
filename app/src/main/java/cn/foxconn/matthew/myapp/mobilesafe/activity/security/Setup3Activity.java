package cn.foxconn.matthew.myapp.mobilesafe.activity.security;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import cn.foxconn.matthew.myapp.R;
import cn.foxconn.matthew.myapp.mobilesafe.base.BaseSetupActivity;
import cn.foxconn.matthew.myapp.utils.ToastUtil;

public class Setup3Activity extends BaseSetupActivity {
    @BindView(R.id.et_phone)
    EditText mEtPhone;

    @Override
    public void showPreviousPage() {
        startActivity(new Intent(this, Setup2Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_previous_in, R.anim.trans_previous_out);
    }

    @Override
    public void showNextPage() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
//            Toast.makeText(this, "安全号码不为空", Toast.LENGTH_SHORT).show();
            ToastUtil.show(this, "安全号码不为空");
            return;
        } else {
            preference.edit().putString("safe_phone", phone).commit();
            startActivity(new Intent(this, Setup4Activity.class));
            finish();
            overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_setup3;
    }

    @Override
    protected void init() {
        super.init();
        String phone = preference.getString("safe_phone", "");
        mEtPhone.setText(phone);
    }

    public void selectContact(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String phone = data.getStringExtra("phone");
            phone = phone.replaceAll("-", "").replaceAll(" ", "");
            mEtPhone.setText(phone);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
