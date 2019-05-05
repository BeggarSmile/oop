package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheck {

    private Pattern pattern = Pattern.compile("[A-Z][0-9][0-9][0-9][A-Z][A-Z][0-9][0-9]");
    private Matcher matcher;
    private boolean check;

    public PatternCheck(String registrationNumber) {
        this.matcher = pattern.matcher(registrationNumber);
        check = this.matcher.find();
    }

    public boolean check() {
        return check;
    }
}
