package com.merculia.carecomm.MobileFragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.merculia.carecomm.Activities.DialCallMobileActivity;
import com.merculia.carecomm.Activities.DialVideoCallMobileActivity;
import com.merculia.carecomm.Adapters.ChatMainItemAdapter;
import com.merculia.carecomm.Adapters.SendFileItemAdapter;
import com.merculia.carecomm.BaseActivity.BaseFragment;
import com.merculia.carecomm.Model.ChatModel;
import com.merculia.carecomm.Model.MainChatModel;
import com.merculia.carecomm.Model.SendPhotoModel;
import com.merculia.carecomm.R;

import java.net.URISyntaxException;
import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ChatRoomMobileFrgment extends BaseFragment implements View.OnClickListener {

    private EditText etMessage;
    private ImageView ivClose,ivVideo,ivCall,ivSendPics,ivCloseChatScreen;
    private RecyclerView rvChat,rvFiles;
    private ImageView ivSendMsg;
    private LinearLayout sendFileLayout, chatLayout;
    private TextView screenTitle;
    private LinearLayoutManager layoutManager;
    private  ArrayList<ChatModel> messageList;
    private ChatMainItemAdapter menuItemAdapter;
    private Button btnShare;
    private Socket socket;
    private String Nickname ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            txtTitleString = getArguments().getString(Constants.txtTitleString);
//            txtValueString = getArguments().getString(Constants.txtValueString);
//            inputType = getArguments().getInt(Constants.inputType);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_room_mobile, container, false);

        setView(view);
        inits();
        setEvents();
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    protected void setView(View view) {
    ivClose = view.findViewById(R.id.iv_close);
    ivVideo = view.findViewById(R.id.iv_video);
    ivCall = view.findViewById(R.id.iv_call);
    rvChat = view.findViewById(R.id.rv_chat);
    etMessage = view.findViewById(R.id.edtInput);
    ivSendMsg = view.findViewById(R.id.btnSendMessage);
    chatLayout = view.findViewById(R.id.layout_chat_room);
    sendFileLayout = view.findViewById(R.id.send_pics);
    screenTitle = view.findViewById(R.id.screen_title);
        ivSendPics = view.findViewById(R.id.iv_send_pics);
        ivCloseChatScreen = view.findViewById(R.id.iv_close_chat_screen);
        btnShare = view.findViewById(R.id.btn_share);
        rvFiles =view.findViewById(R.id.rv_files);


    }


    @Override
    protected void inits() {
        setAdapter();
        screenTitle.setText("Share Album");
        setScreen(false);
    }

    private void setScreen(boolean isOpenGallary){

        if (isOpenGallary){
            chatLayout.setVisibility(View.GONE);
            sendFileLayout.setVisibility(View.VISIBLE);
        }else {
            chatLayout.setVisibility(View.VISIBLE);
            sendFileLayout.setVisibility(View.GONE);
        }
    }
    private void setAdapter(){
        layoutManager = new LinearLayoutManager(getMainActivity(), LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        rvChat.setLayoutManager(layoutManager);
        ArrayList<MainChatModel> list = new ArrayList<>();
        list.add(new MainChatModel("March 1 2019",setDummyChat()));
        list.add(new MainChatModel("March 1 2019",setDummyChat()));
        list.add(new MainChatModel("March 1 2019",setDummyChat()));
        list.add(new MainChatModel("March 1 2019",setDummyChat()));
        list.add(new MainChatModel("Yesterday",setDummyChat()));
        list.add(new MainChatModel("Today",setDummyChat()));

        menuItemAdapter = new ChatMainItemAdapter(context,list);

        rvChat.setAdapter(menuItemAdapter);
    }
    private void setAdapterPictures(){
        layoutManager = new GridLayoutManager(getMainActivity(),3);
        rvFiles.setLayoutManager(layoutManager);
        ArrayList<SendPhotoModel> stringArrayList = new ArrayList<>();
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_1,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_2,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_3,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_4,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_5,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_6,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_1,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_2,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_3,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_4,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_5,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_6,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_1,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_2,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_3,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_4,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_5,false));
        stringArrayList.add(new SendPhotoModel(R.drawable.main_photo_6,false));
        SendFileItemAdapter menuItemAdapter = new SendFileItemAdapter(getMainActivity(),context,stringArrayList);

        rvFiles.setAdapter(menuItemAdapter);
    }

    private ArrayList<ChatModel> setDummyChat(){
        messageList = new ArrayList();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(R.drawable.main_photo_1);
        integerArrayList.add(R.drawable.main_photo_2);
        integerArrayList.add(R.drawable.main_photo_3);
        integerArrayList.add(R.drawable.main_photo_4);
        messageList.add(new ChatModel("this is my message ...How are you I am waiting for you what are you doing " , 0,null));
        messageList.add(new ChatModel("this is your message ... " , 1,null));
        messageList.add(new ChatModel("this is my message ... " , 0,null));
        messageList.add(new ChatModel("" , 2,integerArrayList));
        messageList.add(new ChatModel("this is your message ... " , 1,null));

        return  messageList;
    }
    @Override
    protected void setEvents() {
        ivClose.setOnClickListener(this);
        ivVideo.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivSendMsg.setOnClickListener(this);
        ivSendPics.setOnClickListener(this);
        ivCloseChatScreen.setOnClickListener(this);
        btnShare.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getMainActivity().hideMenu();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
    private void sendMessage(){
        if (!TextUtils.isEmpty(etMessage.getText().toString())) {
            messageList.add(new ChatModel(etMessage.getText().toString(), 0,null));
            if (!TextUtils.isEmpty(etMessage.getText().toString())) {
                try {


//if you are using a phone device you should connect to same local network as your laptop and disable your pubic firewall as well

                    socket = IO.socket("http://192.168.88.26:3354");

                    //create connection

                    socket.connect();

// emit the event join along side with the nickname
                    Nickname= etMessage.getText().toString();
                    socket.emit("join","I am test man for join");

                    socket.emit("messagedetection","Nick name","Message Value");
                    socket.emit("messagedetection","Nick name","Message Value");
                    socket.emit("disconnect","I am test  man for disconnect");

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                }
            menuItemAdapter.notifyDataSetChanged();
            layoutManager.scrollToPosition(messageList.size()-1);
            etMessage.setText("");
        }

    }
    @Override
    public void onClick(View view) {
        if (view== ivClose){
           setScreen(false);

        }
        if (view== ivVideo){
            getMainActivity().openActivity(context, DialVideoCallMobileActivity.class);
        }
        if (view== ivCall){
            getMainActivity().openActivity(context, DialCallMobileActivity.class);
        }
        if (view == ivSendMsg){
            sendMessage();
        }
        if (view == ivCloseChatScreen){
            onBack();
        }
        if (view == ivSendPics){
            setAdapterPictures();
            setScreen(true);

        }
        if (view == btnShare){
            setScreen(false);

        }


    }
}
