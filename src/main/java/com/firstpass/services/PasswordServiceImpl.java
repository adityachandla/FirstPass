package com.firstpass.services;

import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService{

    @Override
    public String getPassword(String userSecret, String appName) {
        if(userSecret.length() == 0 || appName.length() == 0) {
            return "Invalid input";
        }
        StringBuilder finalPassword = new StringBuilder(20);
        finalPassword.append(addAndCapitalize(userSecret, appName));
        ensureMinimumSize(finalPassword, 4);
        StringBuilder numbers = getNumbers(finalPassword);
        finalPassword.append("&");
        finalPassword.append(numbers);
        finalPassword.append("$");
        return finalPassword.toString();
    }

    @Override
    public String getPassword(String appName) {
        if(appName.length() == 0) {
            return "Invalid input";
        }
        return getPassword("default" , appName);
    }

    public void ensureMinimumSize(StringBuilder sb, int minSize) {
        while(sb.length() < minSize) {
            sb.append(sb.toString());
        }
    }

    public StringBuilder getNumbers(StringBuilder finalPassword) {
        StringBuilder temp = new StringBuilder();
        for(int idx = 0; idx < finalPassword.length(); ++idx) {
            int val = powerFunc(3, finalPassword.charAt(idx) - 'A', 7);
            temp.append(val);
        }
        return temp;
    }

    public String addAndCapitalize(String userSecret, String appName) {
        StringBuilder sb = new StringBuilder();
        int userNameIdx = 0, appNameIdx = 0;
        while(userNameIdx < userSecret.length() && appNameIdx < appName.length()) {
            sb.append(addChars(userSecret.charAt(userNameIdx), appName.charAt(appNameIdx)));
            appNameIdx++; userNameIdx++;
        }
        sb.setCharAt(0, capitalize(sb.charAt(0)));
        return sb.toString();
    }

    public char addChars(char one, char two) {
        return (char)(((((one+two)%26)+26)%26) + 'a');
    }

    public char capitalize(char toCapitalize) {
        return (char) (toCapitalize - 'a' + 'A');
    }

    public int powerFunc(int base, int exponent, int mod) {
        if(exponent == 0)
            return 1;
        if(exponent == 1)
            return base;
        int halfExponent = powerFunc(base, exponent/2, mod);
        return exponent%2 == 0 ? (halfExponent*halfExponent)%mod : (halfExponent*halfExponent*base)%mod;
    }
}
