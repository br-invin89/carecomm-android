package com.merculia.carecomm.Model;

public class MakeConservationModel {
    private String title;
    private int profilePic;
    private boolean isContact;

    public MakeConservationModel(String title, int profilePic,boolean isContact) {
        this.title = title;
        this.isContact = isContact;
        this.profilePic = profilePic;
    }

    public boolean isContact() {
        return isContact;
    }

    public String getTitle() {
        return title;
    }

    public int getProfilePic() {
        return profilePic;
    }
}
