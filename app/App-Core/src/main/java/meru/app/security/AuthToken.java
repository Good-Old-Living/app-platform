package meru.app.security;

public class AuthToken {

    private String mAKey;
    private String mUser;
    private String mUserId;
    private String mHome;

    public AuthToken(String aKey, String userName, String userId, String home) {
        mAKey = aKey;
        mUser = userName;
        mUserId = userId;
        mHome = home;
    }

    public String getAKey() {
        return mAKey;
    }

    public String getUser() {
        return mUser;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getHome() {
        return mHome;
    }

    public String toString() {
        return mAKey;
    }

}
