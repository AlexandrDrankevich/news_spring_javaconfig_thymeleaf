package by.htp.ex.util.validation;

import by.htp.ex.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DataValidation {

    private final boolean result;
    private final Map<String, Boolean> validResults;

    private DataValidation(Builder builder) {
        this.validResults = builder.validResults;
        result = !validResults.containsValue(false);
    }

    public boolean isResult() {
        return result;
    }

    public Map<String, Boolean> getValidResults() {
        return validResults;
    }

    public static class Builder {
        private Map<String, Boolean> validResults = new HashMap<>();
        private static final String loginCheckPattern = "\\w+([\\.-]?\\w+)*@[a-z]+.[a-z]{2,3}";
        private static final String passwordCheckPattern = "[A-Z a-z 0-9]+";
        private static final String nameSurnameCheckPattern = "[A-Z a-z]+";
        private static final String birthdayCheckPattern = "((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";

        public Builder checkLogin(String login) {
            validResults.put("login", Pattern.matches(loginCheckPattern, login));
            return (this);
        }

        public Builder checkPassword(String password) {
            validResults.put("password", Pattern.matches(passwordCheckPattern, password));
            return (this);
        }

        public Builder checkName(String name) {
            validResults.put("name", Pattern.matches(nameSurnameCheckPattern, name));
            return (this);
        }

        public Builder checkSurname(String surname) {
            validResults.put("surname", Pattern.matches(nameSurnameCheckPattern, surname));
            return (this);
        }

        public Builder checkBirthday(String birthday) {
            validResults.put("birthday", Pattern.matches(birthdayCheckPattern, birthday));
            return (this);
        }

        public Builder checkRegData(UserInfo user) {
            String birthday = user.getBirthday().toString();
            String login = user.getLogin();
            String name = user.getName();
            String surname = user.getSurname();
            checkLogin(login).checkName(name).checkSurname(surname).checkBirthday(birthday);
            return (this);
        }

        public DataValidation generateResult() {
            return new DataValidation(this);
        }
    }
}
