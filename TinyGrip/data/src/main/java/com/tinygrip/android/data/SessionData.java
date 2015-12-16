package com.tinygrip.android.data;

import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.entity.user.UserEntity;

/**
 * Class holding any previous
 */
public class SessionData {

    private RootEntity root;
    private UserEntity user;

    public RootEntity getRoot() {
        return root;
    }

    public void setRoot(RootEntity root) {
        this.root = root;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
