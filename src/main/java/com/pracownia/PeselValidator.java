package com.pracownia;


public class PeselValidator {

    static final int PESEL_NUMBER_LENGTH = 11;

    public static boolean IsValidPESEL(String PESEL){


        if(PESEL == null)
            return false;

        if(!checkPESELLength(PESEL))
            return false;

        if(!isNumeric(PESEL))
            return false;

        return checkControlDigitValidity(PESEL);
    }

    public static boolean checkPESELLength(String PESEL){
        return PESEL.length() == PESEL_NUMBER_LENGTH;
    }

    public static boolean isNumeric(String str){
        for(Character c : str.toCharArray()){
            if(!Character.isDigit(c) || !(c >= '0' && c <= '9'))
                return false;
        }
        return true;
    }

    public static boolean checkControlDigitValidity(String PESEL){
        int weights[] = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;
        int currentDigit;
        int i = 0;
        for(Character c : PESEL.toCharArray()){
            try {
                currentDigit = Integer.parseInt(c.toString());
            }catch(NumberFormatException nfe){
                return false;
            }

            sum += currentDigit * weights[i];
            i++;
        }

        return sum % 10 == 0;


    }
}
