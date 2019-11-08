package com.merculia.carecomm.MobileFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.merculia.carecomm.Adapters2.UpcomingEventItemAdapter;
import com.merculia.carecomm.BaseActivity.BaseFragment;
import com.merculia.carecomm.Model.UpcomingEnventModel;
import com.merculia.carecomm.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ConservationFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivBack,ivClose;
    private LinearLayout ivMakeconv;
    private TextView screenTitle;
    private RecyclerView rvMakeCon;
    private RecyclerView.LayoutManager layoutManager;

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
        View view = inflater.inflate(R.layout.fragment_conservation, container, false);

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
        ivBack = view.findViewById(R.id.iv_back);
        ivClose = view.findViewById(R.id.iv_close);
        screenTitle = view.findViewById(R.id.screen_title);
        rvMakeCon = view.findViewById(R.id.rv_make_con);
        ivMakeconv = view.findViewById(R.id.iv_makeconv);

    }


    @Override
    protected void inits() {
        ivClose.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        screenTitle.setText("Comm");
        setConversationAdapter();
    }

    @Override
    protected void setEvents() {
        ivBack.setOnClickListener(this);
        ivMakeconv.setOnClickListener(this);

    }

    private void setConversationAdapter(){
        /*
        layoutManager = new LinearLayoutManager(getMainActivity());
        rvMakeCon.setLayoutManager(layoutManager);
        ArrayList<UpcomingEnventModel> list = new ArrayList<>();
        list.add(new UpcomingEnventModel("Jane Dane",R.drawable.main_photo_1,"Lorem ipsum dolor..."
                ,"8:40 AM","",true));
        list.add(new UpcomingEnventModel("Jane Dane",R.drawable.main_photo_2,"Lorem ipsum dolor..."
                ,"8:40 AM","",true));
        list.add(new UpcomingEnventModel("Jane Dane",R.drawable.main_photo_3,"Lorem ipsum dolor..."
                ,"8:40 AM","",true));
        list.add(new UpcomingEnventModel("Jane Dane",R.drawable.main_photo_4,"Lorem ipsum dolor..."
                ,"8:40 AM","",true));
        list.add(new UpcomingEnventModel("Jane Dane",R.drawable.main_photo_5,"Lorem ipsum dolor..."
                ,"8:40 AM","",true));
        UpcomingEventItemAdapter menuItemAdapter = new UpcomingEventItemAdapter(context,list,getMainActivity());

        rvMakeCon.setAdapter(menuItemAdapter);

         */

    }
    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onClick(View view) {
      if (ivBack == view){
          openHome();
      }
      if (view == ivMakeconv){
          getMainActivity().ReplaceFragmentWithBackstack(new MakeConservationFragment(),false,true);
      }
    }
}
