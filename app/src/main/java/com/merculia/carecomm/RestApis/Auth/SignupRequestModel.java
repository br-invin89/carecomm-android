package com.merculia.carecomm.RestApis.Auth;

public class SignupRequestModel {
    public UserModel user;

    public static class UserModel {
        public String name;
        public String username;
        public String email;
        public String password;
    }

}
