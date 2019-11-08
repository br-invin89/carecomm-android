package com.merculia.carecomm.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.merculia.carecomm.Model.ChatModel;
import com.merculia.carecomm.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_PICTURE = 2;

    private ArrayList<ChatModel> item_list;
    private Context mContext;
    public ChatAdapter(ArrayList<ChatModel> item_list, Context context) {
        this.item_list = item_list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View view = layoutInflater.inflate(R.layout.row_iten_send, parent, false);
            return new SelfViewHolder(view);
        }
        if (viewType == TYPE_ITEM) {
            View view = layoutInflater.inflate(R.layout.row_item_recieved, parent, false);
            return new OtherViewHolder(view);
        }
        if (viewType == TYPE_PICTURE){
            View view = layoutInflater.inflate(R.layout.image_item, parent, false);
            return new ImageRecycleGridView(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ChatModel chatModel =  item_list.get(i);
        if (viewHolder.getItemViewType() == TYPE_HEADER){
            ((SelfViewHolder)viewHolder).mine.setText(chatModel.getMessgae());
        }else if (viewHolder.getItemViewType() == TYPE_ITEM) {
            ((OtherViewHolder)viewHolder).other.setText(chatModel.getMessgae());
        }else {
            ((ImageRecycleGridView)viewHolder).recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            ((ImageRecycleGridView)viewHolder).recyclerView.setVerticalScrollBarEnabled(false);

            ImageAdapter adapter = new ImageAdapter(item_list.get(i).getArrayListImages(),mContext);
            ((ImageRecycleGridView)viewHolder).recyclerView.setAdapter(adapter);

        }
    }
    @Override
    public int getItemViewType(int position) {
        ChatModel chatModel =  item_list.get(position);
        int messageType = chatModel.getMessageCode();
        if (messageType == 0) {
            return TYPE_HEADER;
        } else if (messageType == 1){
            return TYPE_ITEM;
        }else {
            return TYPE_PICTURE;
        }
    }
    @Override
    public int getItemCount() {
        return item_list.size();
    }

    public class SelfViewHolder extends RecyclerView.ViewHolder {
        TextView mine;
        public SelfViewHolder(@NonNull View itemView) {
            super(itemView);
            mine = itemView.findViewById(R.id.self_message);
        }
    }
    public class OtherViewHolder extends RecyclerView.ViewHolder {
        TextView other;
        public OtherViewHolder(@NonNull View itemView) {
            super(itemView);
            other = itemView.findViewById(R.id.other_text);
        }
    }
    public class ImageRecycleGridView extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public ImageRecycleGridView(@NonNull View itemView) {
            super(itemView);


            recyclerView = itemView.findViewById(R.id.image_recycle_view);




        }
    }
}
