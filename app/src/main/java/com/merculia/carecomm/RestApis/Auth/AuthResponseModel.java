package com.merculia.carecomm.RestApis.Auth;

import java.util.HashMap;

public class AuthResponseModel {
    UserModel user;

    public static class UserModel {
        public String username;
        public String email;
        public String token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
