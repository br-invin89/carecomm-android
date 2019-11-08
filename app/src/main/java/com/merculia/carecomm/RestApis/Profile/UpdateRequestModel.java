package com.merculia.carecomm.RestApis.Profile;

public class UpdateRequestModel {
    public UserModel user;

    public static class UserModel {
        public String name;
        public String email;
    }
}
