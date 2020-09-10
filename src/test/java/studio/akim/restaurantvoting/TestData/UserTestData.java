package studio.akim.restaurantvoting.TestData;

import studio.akim.restaurantvoting.model.AbstractBaseEntity;
import studio.akim.restaurantvoting.model.User;

public class UserTestData {
    public static final int ADMIN_ID = AbstractBaseEntity.START_SEQ;
    public static final int USER_ID = AbstractBaseEntity.START_SEQ + 1;
    public static final int USER_ID1 = AbstractBaseEntity.START_SEQ + 2;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin");
    public static final User USER = new User(USER_ID, "User", "password");
    public static final User USER1 = new User(USER_ID1, "User1", "password");
}
