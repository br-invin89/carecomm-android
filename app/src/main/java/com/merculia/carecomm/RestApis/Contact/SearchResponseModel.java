package com.merculia.carecomm.RestApis.Contact;

public class SearchResponseModel {
    public User user;

    public static class User {
        public String _id;
        public String name;
        public String username;
        public String email;
        public String photo;
    }
}
