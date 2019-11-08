package com.merculia.carecomm.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merculia.carecomm.Activities.DialCallMobileActivity;
import com.merculia.carecomm.Activities.DialVideoCallMobileActivity;
import com.merculia.carecomm.BaseActivity.BaseFragmentActivity;
import com.merculia.carecomm.MobileFragments.ChatRoomMobileFrgment;
import com.merculia.carecomm.MobileFragments.ContactDetailMobileFrgment;
import com.merculia.carecomm.Model.MakeConservationModel;
import com.merculia.carecomm.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class MakeConversationItemAdapter extends RecyclerView.Adapter<MakeConversationItemAdapter.ItemView> {

    private List<MakeConservationModel> dataSet;
    private Context mContext;
    private BaseFragmentActivity mainActivity;
    public OnItemClickLinter onItemClickLinter;

    public MakeConversationItemAdapter(Context context, List<MakeConservationModel> dataSet, BaseFragmentActivity mainActivity) {
        this.dataSet = dataSet;
        this.mContext = context;
        this.mainActivity = mainActivity;
    }

    public interface OnItemClickLinter{
        public void onItemClick(int postion);
    }


    public void setItemClickLinter(OnItemClickLinter onItemClickLinter){
        this.onItemClickLinter = onItemClickLinter;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_select_contact, viewGroup, false);
        return new ItemView(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MakeConversationItemAdapter.ItemView holder, int position) {
        MakeConservationModel menuItemModel = dataSet.get(position);

        Glide.with(mContext).load(menuItemModel.getProfilePic()).into(holder.imageProfile);
        holder.tvTitle.setText(menuItemModel.getTitle());
        if (menuItemModel.isContact()){
            holder.layoutChat.setVisibility(View.GONE);
            holder.layoutContact.setVisibility(View.VISIBLE);
        } else {
            holder.layoutChat.setVisibility(View.VISIBLE);
            holder.layoutContact.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ItemView  extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageProfile,ivChat,ivCallContact,ivVideoContact,ivChatContact;
        LinearLayout layoutChat,layoutContact;
        TextView tvTitle,tvChat;

        private ItemView(View itemView) {
            super(itemView);
            ivCallContact = itemView.findViewById(R.id.iv_call_contact);
            ivVideoContact = itemView.findViewById(R.id.iv_video_contact);
            ivChatContact = itemView.findViewById(R.id.iv_chat_contact);

            imageProfile = itemView.findViewById(R.id.profile_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvChat = itemView.findViewById(R.id.tv_chat);
            ivChat = itemView.findViewById(R.id.iv_chat);
            layoutContact = itemView.findViewById(R.id.layout_contact);
            layoutChat = itemView.findViewById(R.id.layout_chat);

            itemView.setOnClickListener(this);
            ivCallContact.setOnClickListener(this);
            ivChatContact.setOnClickListener(this);
            ivVideoContact.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MakeConservationModel makeConservationModel = dataSet.get(getAdapterPosition());

            if (view == itemView){
                if (makeConservationModel.isContact()){
                    mainActivity.ReplaceFragmentWithBackstack(new ContactDetailMobileFrgment(),false,true);
                }else {
                    mainActivity.ReplaceFragmentWithBackstack(new ChatRoomMobileFrgment(),false,true);
                }
            }

            if (view == ivCallContact){
                mainActivity.openActivity(mContext, DialCallMobileActivity.class);
            }

            if (view==ivVideoContact){
                mainActivity.openActivity(mContext, DialVideoCallMobileActivity.class);
            }
            if (view == ivChatContact){
                mainActivity.ReplaceFragmentWithBackstack(new ChatRoomMobileFrgment(),false);
            }

        }
    }
}
