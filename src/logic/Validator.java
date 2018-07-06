package logic;

import entity.User;

import java.util.regex.Pattern;

public class Validator {
    private static final String LOGIN_REGEX = "1";
    private static final String PASSWORD_REGEX = "2";
    private static final String EMAIL_REGEX = "3";
    private static final String NAME_REGEX = "4";

    public boolean isUserValid(User user){
        return user.getLogin().matches(LOGIN_REGEX)
                && user.getPassword().matches(PASSWORD_REGEX)
                && user.getEmail().matches(EMAIL_REGEX)
                && user.getFirstName().matches(NAME_REGEX)
                && user.getLastName().matches(NAME_REGEX);
    }
}
