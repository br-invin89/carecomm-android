package com.merculia.carecomm.MobileFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merculia.carecomm.Adapters.MakeConversationItemAdapter;
import com.merculia.carecomm.BaseActivity.BaseFragment;
import com.merculia.carecomm.Model.MakeConservationModel;
import com.merculia.carecomm.R;
import com.merculia.carecomm.Utils.SpacesItemDecoration;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MakeConservationFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivBack,ivClose;
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
        View view = inflater.inflate(R.layout.fragment_make_conservation, container, false);

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


    }


    @Override
    protected void inits() {
        ivClose.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        screenTitle.setText("Make a Conversation");
        setAdapter();
    }

    @Override
    protected void setEvents() {
        ivBack.setOnClickListener(this);


    }

    private void setAdapter(){
        layoutManager = new GridLayoutManager(getMainActivity(),2);
        rvMakeCon.addItemDecoration(new SpacesItemDecoration(35));
        rvMakeCon.setLayoutManager(layoutManager);
        ArrayList<MakeConservationModel> list = new ArrayList<>();
        list.add(new MakeConservationModel("Daughter",R.drawable.main_photo_9,false));
        list.add(new MakeConservationModel("Mom",R.drawable.main_photo_8,false));
        list.add(new MakeConservationModel("Aunty",R.drawable.main_photo_6,false));
        list.add(new MakeConservationModel("Mother Law",R.drawable.main_photo_7,false));
        list.add(new MakeConservationModel("Daughter",R.drawable.main_photo_5,false));
        list.add(new MakeConservationModel("Wife",R.drawable.main_photo_1,false));

        MakeConversationItemAdapter menuItemAdapter = new MakeConversationItemAdapter(context,list,getMainActivity());

        rvMakeCon.setAdapter(menuItemAdapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onClick(View view) {
      if (ivBack == view){
          onBack();
      }
    }
}
