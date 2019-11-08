package com.merculia.carecomm.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.merculia.carecomm.BaseActivity.BaseFragmentActivity;
import com.merculia.carecomm.R;
import com.merculia.carecomm.Utils.Constants;
import com.merculia.carecomm.interfaces.ReturnFromCall;

public class DialCallMobileActivity extends BaseFragmentActivity implements View.OnClickListener {

    private Button btnCancel;
    private LinearLayout layoutMinimize;
    private ImageView ivMute,ivSpeaker;
    private LinearLayout layoutMute,layoutSpeaker;
    private TextView txtMute,txtSpeaker;
    private ReturnFromCall inter;
    private boolean isMute = false;
    private boolean isSpeaker = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavigationBar();
        if (isTablet(context)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_dial_call_mobile);
        setView();
        init();
        setEvents();
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        inter = (ReturnFromCall) intent.getSerializableExtra(Constants.INTERFACE);
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
        btnCancel = findViewById(R.id.btn_cancel);
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
            ivMute.setImageResource(R.mipmap.group_405);
        }else {
            ivMute.setImageResource(R.mipmap.group_401);
        }
    }

    private void setSpeakerLayout(boolean isSpeaker){
        if (isSpeaker){
            ivSpeaker.setImageResource(R.mipmap.group_417);
        }else {
            ivSpeaker.setImageResource(R.mipmap.group_391);
        }
    }
}
