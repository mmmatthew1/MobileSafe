package cn.foxconn.matthew.myapp.mobilesafe.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.foxconn.matthew.myapp.R;
import cn.foxconn.matthew.myapp.mobilesafe.base.MobileSafeBaseActivity;
import cn.foxconn.matthew.myapp.mobilesafe.widget.SettingItemView;
import cn.foxconn.matthew.myapp.utils.AdminManager;


public class SettingActivity extends MobileSafeBaseActivity {
    @BindView(R.id.item_update)
    SettingItemView updateItem;
    @BindView(R.id.item_device_admin)
    SettingItemView deviceAdminItem;
    private SharedPreferences preferences;

    @Override
    protected void init() {
        super.init();
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        //updateItem.setTitle("自动更新设置");
        boolean isAutoUpdate = preferences.getBoolean("auto_update", true);
        boolean isDeviceAdminOn = preferences.getBoolean("device_admin_on", false);
        updateItem.setChecked(isAutoUpdate);
        deviceAdminItem.setChecked(isDeviceAdminOn);
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.item_device_admin, R.id.item_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_update:
                if (updateItem.isChecked()) {
                    updateItem.setChecked(false);
                    preferences.edit().putBoolean("auto_update", false).apply();
                } else {
                    updateItem.setChecked(true);
                    preferences.edit().putBoolean("auto_update", true).apply();
                }
                break;
            case R.id.item_device_admin:
                if (deviceAdminItem.isChecked()) {
                    deviceAdminItem.setChecked(false);
                    AdminManager.getInstance().removeActive();
                    preferences.edit().putBoolean("device_admin_on", false).apply();
                } else {
                    AdminManager.getInstance().activeAdmin(this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if(AdminManager.getInstance().isAdminActived()){
                    deviceAdminItem.setChecked(true);
                    preferences.edit().putBoolean("device_admin_on", true).apply();
                }else {
                    deviceAdminItem.setChecked(false);
                    preferences.edit().putBoolean("device_admin_on", false).apply();
                }
                break;
            default:
                break;
        }
    }
}