package com.merculia.carecomm.RestApis.Profile;

public class ChangePasswordRequestModel {
    public UserModel user;

    public static class UserModel {
        public String password;
        public String newPassword;
    }
}
