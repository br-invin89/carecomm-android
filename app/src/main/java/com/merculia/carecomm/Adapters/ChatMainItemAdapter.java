package com.merculia.carecomm.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merculia.carecomm.Model.ChatModel;
import com.merculia.carecomm.Model.EventModel;
import com.merculia.carecomm.Model.MainChatModel;
import com.merculia.carecomm.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatMainItemAdapter extends RecyclerView.Adapter<ChatMainItemAdapter.ItemView> {

    private List<MainChatModel> dataSet;
    private Context mContext;

    public OnItemClickLinter onItemClickLinter;

    public ChatMainItemAdapter(Context context, List<MainChatModel> dataSet) {
        this.dataSet = dataSet;
        this.mContext = context;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_chat_date, viewGroup, false);
        return new ItemView(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ChatMainItemAdapter.ItemView holder, int position) {
        MainChatModel mainChatModel = dataSet.get(position);


        holder.tvDate.setText(mainChatModel.getDate());
        setChatAdapoter(holder.rvChatType,mainChatModel.getArrayList());


    }
    private void setChatAdapoter(RecyclerView rvChat,ArrayList<ChatModel> arrayList){

       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL,false);
        rvChat.setLayoutManager(linearLayoutManager);
       ChatAdapter chatAdapter = new ChatAdapter(arrayList,mContext);
        rvChat.setAdapter(chatAdapter);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ItemView  extends RecyclerView.ViewHolder {

        TextView tvDate;
        RecyclerView rvChatType;

        private ItemView(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            rvChatType = itemView.findViewById(R.id.rv_chat_type);


        }
    }
}
