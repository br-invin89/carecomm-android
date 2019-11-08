package com.merculia.carecomm.RestApis.Auth;

public class CheckConfirmCodeResponseModel {
    public UserModel user;

    public static class UserModel {
        public String username;
        public String email;
        public String token;
    }
}
