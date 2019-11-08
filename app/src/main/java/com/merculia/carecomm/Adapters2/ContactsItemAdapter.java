package com.merculia.carecomm.Adapters2;

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
import com.merculia.carecomm.RestApis.Contact.ContactModel;
import com.merculia.carecomm.R;
import com.merculia.carecomm.RestApis.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsItemAdapter extends RecyclerView.Adapter<ContactsItemAdapter.ItemView> {

    private List<ContactModel> dataSet;
    private Context mContext;
    private BaseFragmentActivity mainActivity;
    public OnItemClickLinter onItemClickLinter;

    public ContactsItemAdapter(Context context, List<ContactModel> dataSet, BaseFragmentActivity mainActivity) {
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
    public void onBindViewHolder(@NonNull ContactsItemAdapter.ItemView holder, int position) {
        ContactModel data = dataSet.get(position);

        if (data.userRef.photo.contentEquals(""))
            Picasso.get().load("https://static.productionready.io/images/smiley-cyrus.jpg").into(holder.imageProfile);
        else
            Picasso.get().load(data.userRef.photo).into(holder.imageProfile);
        holder.tvTitle.setText(data.userRef.name);
        holder.layoutChat.setVisibility(View.GONE);
        if (data.isContacted) {
            holder.layoutContact.setVisibility(View.VISIBLE);
            holder.layoutPending.setVisibility(View.GONE);
        } else {
            holder.layoutContact.setVisibility(View.GONE);
            holder.layoutPending.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ItemView  extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageProfile,ivChat,ivCallContact,ivVideoContact,ivChatContact;
        LinearLayout layoutChat,layoutContact, layoutPending;
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
            layoutPending = itemView.findViewById(R.id.layout_pending);

            itemView.setOnClickListener(this);
            ivCallContact.setOnClickListener(this);
            ivChatContact.setOnClickListener(this);
            ivVideoContact.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ContactModel data = dataSet.get(getAdapterPosition());
            Data.selectedContact = data;

            if (view == itemView){
                mainActivity.ReplaceFragmentWithBackstack(new ContactDetailMobileFrgment(),false,true);
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
