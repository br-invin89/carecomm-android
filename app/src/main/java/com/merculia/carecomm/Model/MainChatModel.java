package com.merculia.carecomm.Model;

import java.util.ArrayList;

public class MainChatModel {
    private String date;
    private ArrayList<ChatModel> arrayList;

    public MainChatModel(String date, ArrayList<ChatModel> arrayList) {
        this.date = date;
        this.arrayList = arrayList;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<ChatModel> getArrayList() {
        return arrayList;
    }
}
