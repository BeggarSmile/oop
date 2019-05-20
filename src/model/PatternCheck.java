package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheck {
    private static Pattern pattern = Pattern.compile("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}");

    public static void check(String registrationNumber) throws IlleagalRegistrationNumberFormat {
        Matcher matcher = pattern.matcher(registrationNumber);
        if (!matcher.matches())
            throw new IlleagalRegistrationNumberFormat();
    }
}
