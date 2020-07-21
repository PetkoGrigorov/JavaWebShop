package model.system;

import model.User;

public class AuthUser {

    private static User authUser = null;
    public static boolean isUserLogged = isUserAuthenticated();

    public static void authenticateUser(String email, String password) {
        authUser = User.fetchUser(email, password);
    }

    public static boolean isUserAuthenticated() {
        return authUser != null;
    }

    public static String getUsername() {
        return authUser.getUsername();
    }

    public static String getUserFullName() {
        return authUser.getUserFullName();
    }

    public static void destroyUser() {
        AuthUser.authUser = null;
    }


}
