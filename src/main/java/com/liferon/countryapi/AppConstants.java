package com.liferon.countryapi;

public class AppConstants {

    public static class FrontEndClient {

        public static final String CLIENT_ID = "angular-client";
        public static final String CLIENT_SECRET = "HZzgmiImI1YHVGe2IhZ1rf1xeYQPRa5F";
        public static final String[] AUTH_SCOPES = {"read", "write", "admin"};
        public static final String[] AUTHORITIES = {"ROLE_ADMIN", "ROLE_SUPERADMIN", "ROLE_USER"};
        public static final String[] GRANT_TYPES = {"password", "authorization_code", "refresh_token"};
    }

    public static final int ACCESS_TOKEN_VALIDITY = 108000;
    public static final int REFRESH_TOKEN_VALIDITY = 2592000;
    public static final String SIGNING_KEY = "duAcCih7BWRLgfw7jNPYXk8NfTE6mapf";
}
