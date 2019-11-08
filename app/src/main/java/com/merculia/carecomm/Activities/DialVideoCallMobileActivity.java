package com.merculia.carecomm.Activities;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.merculia.carecomm.BaseActivity.BaseFragmentActivity;
import com.merculia.carecomm.R;
import com.merculia.carecomm.Utils.CameraPreview;
import com.merculia.carecomm.Utils.Constants;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class DialVideoCallMobileActivity extends BaseFragmentActivity implements View.OnClickListener {


    private Camera mCamera;
    private LinearLayout cameraPreview;
    private CameraPreview mPreview;
    private Button btnCancel;
    private LinearLayout layoutMinimize;
    private ImageView ivMute,ivSpeaker;
    private LinearLayout layoutMute,layoutSpeaker;
    private TextView txtMute,txtSpeaker;
    private boolean isMute = false;
    private boolean isSpeaker = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavigationBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setTheme();
        if (isTablet(context)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_dial_video_call_mobile);
        setView();
        init();
        setEvents();

    }

    @Override
    protected void init() {
        getPermissionLocationCamenra();

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        }
    }
    private void startCamera(){
        mCamera =  Camera.open();
        mCamera.setDisplayOrientation(90);

        mPreview = new CameraPreview(context, mCamera);
        cameraPreview.addView(mPreview);
    }
    @Override
    protected void setEvents() {
        btnCancel.setOnClickListener(this);
        layoutMinimize.setOnClickListener(this);
        layoutSpeaker.setOnClickListener(this);
        layoutMute.setOnClickListener(this);
    }

    @Override
    protected void setView() {
        cameraPreview =findViewById(R.id.cPreview);
        btnCancel= findViewById(R.id.btn_cancel);
        layoutMinimize = findViewById(R.id.layout_minimize);
        layoutMute = findViewById(R.id.layout_mute);
        layoutSpeaker = findViewById(R.id.layout_speaker);
        ivMute = findViewById(R.id.iv_mute);
        txtMute = findViewById(R.id.txt_mute);
        ivSpeaker = findViewById(R.id.iv_speaker);
        txtSpeaker = findViewById(R.id.txt_speaker);


    }

    @Override
    public void onClick(View view) {
        if (view == btnCancel){
            Constants.ISVOICECALL = false;
            finish();
        }

        if (view == layoutMinimize){
            Constants.ISVOICECALL = true;
            openActivity(context,MainActivity.class);

        }
        if (view == layoutMute){
            isMute = isMute ? false : true;
            setMuteLayout(isMute);
        }

        if (view == layoutSpeaker){
            isSpeaker = isSpeaker ? false : true;
            setSpeakerLayout(isSpeaker);
        }
    }

    private void setMuteLayout(boolean isMute){
        if (isMute){
            ivMute.setImageResource(R.mipmap.group_406);
        }else {
            ivMute.setImageResource(R.mipmap.group_403);
        }
    }

    private void setSpeakerLayout(boolean isSpeaker){
        if (isSpeaker){
            ivSpeaker.setImageResource(R.mipmap.group_420);
        }else {
            ivSpeaker.setImageResource(R.mipmap.speaker_white_border);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        }else {
            finish();
        }
    }
}
