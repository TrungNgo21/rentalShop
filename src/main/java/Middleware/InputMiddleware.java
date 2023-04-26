package Middleware;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMiddleware {
    private final String passwordPatternStr = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,12}$";
    private final String digitPatternStr = "^\\d+$";
    private final String whiteSpacePatternStr = "\\s+";
    private final Pattern passPattern = Pattern.compile(passwordPatternStr);
    private final Pattern digitPattern = Pattern.compile(digitPatternStr);

    private final Pattern whiteSpacePattern = Pattern.compile(whiteSpacePatternStr);

    public boolean isValidPassword(String password){
        Matcher matcher = passPattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidPhoneNum(String phoneNum){
        Matcher matcher = digitPattern.matcher(phoneNum);
        if(matcher.matches()){
            return phoneNum.length() == 10;
        }else{
            return false;
        }
    }

    public boolean isValidUsername(String username){
        Matcher matcher = whiteSpacePattern.matcher(username);
        if(matcher.matches()){
            return false;
        }else {
            return username.length() == 12;
        }
    }

    public boolean isValidIString(int length, String inputI4){
        return inputI4.length() == length;
    }

}
