package Middleware;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMiddleware {
    private final String passwordPatternStr = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,12}$";
    private final String digitPatternStr = "^\\d+$";


    public static final String doubleNegativeStr = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";

    public static final String doublePositiveStr = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
    private final Pattern negativeDoublePattern = Pattern.compile(doubleNegativeStr);

    private final Pattern doublePositivePattern = Pattern.compile(doublePositiveStr);
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
        if(matcher.find()){
            System.out.println("hehe");
            return false;
        }else {
            return username.length() >= 12;
        }
    }
    public boolean isPositive(String num) {
        if(num.isEmpty()){
            return false;
        }
        Matcher matcher = digitPattern.matcher(num);
        Matcher matcher1 = doublePositivePattern.matcher(num);
        if(!matcher.matches() && !matcher1.matches()){
            return false;
        }

        return Double.parseDouble(num) > 0;
    }
    public boolean isValidNumber(String num){
        Matcher matcher = negativeDoublePattern.matcher(num);
        Matcher matcher2 = doublePositivePattern.matcher(num);
        Matcher matcher1 = digitPattern.matcher(num);
        return matcher.matches() || matcher1.matches() || matcher2.matches();
    }


    public boolean isValidIString(int length, String inputI4){
        return inputI4.length() >= length;
    }

}
