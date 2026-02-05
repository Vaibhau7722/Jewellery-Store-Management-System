package com.example.JewelleryStoreManagementSystem;

import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;

public class Utilities {

    public static boolean contains1LetterAndNumber(String inputString) {
        if(Character.isLetter(inputString.charAt(0))){
            if(Character.isDigit(inputString.charAt(1))){
                if(Character.isDigit(inputString.charAt(2))){
                    return true;
                }
            }
            if(Character.isDigit(inputString.charAt(1))){
                return true;
            }
        }
        return false;
    }

    public static boolean contains2LettersAnd2Numbers(String inputString) {
        int letter = 0;
        int number = 0;
        if (!inputString.isEmpty()) {
            for (int i = 0; i <= 3; i++) {
                if (Character.isLetter(inputString.charAt(i))) {
                    letter++;
                }
                if (Character.isDigit(inputString.charAt(i))) {
                    number++;
                }
            }
            if(letter == 2 && number == 2) {
                return true;
            }
        }
        return false;
    }

    public static int randomNumberGenerator(int min, int max)
    {
        double r = Math.random();
        return (int)(r * (max - min)) + min;
    }

    public static char randomLetterGenerator(){
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }

    public static boolean isUniqueCaseID(String inputID){
        for(int i = 0; i < CONTENT.getCaseList().getListSize(); i++){
            if(inputID.equals("") || CONTENT.getCaseList().get(i).getContent().getCaseID().contains(inputID)){
                return false;
            }
        }
        return true;
    }

    public static String uniqueCaseIDEnforce(String inputString){
        if (isUniqueCaseID(inputString) && !inputString.equals("")){
            return inputString;
        }else {
            String uniqueCaseID = inputString;
            int i = 1;
            while(!Utilities.isUniqueCaseID(uniqueCaseID) || uniqueCaseID.equals("")){
                uniqueCaseID = "Case"+i;
                i++;
            }
            return uniqueCaseID;
        }
    }

    public static boolean isUniqueTrayID(String inputID){
        for(int i = 0; i < CONTENT.getTrayList().getListSize(); i++){
            if(CONTENT.getTrayList().get(i).getContent().getTrayID().contains(inputID)){
                return false;
            }
        }
        return true;
    }

    public static String uniqueTrayIDEnforce(String inputString) { //ensure a tray has a unique id by creating one with random number/letter generator in a premade format.
        String newID = inputString;
        while (!isUniqueTrayID(newID) || (!contains1LetterAndNumber(newID) || (newID.length() < 2 || newID.length() > 3))) {
            char letter = Utilities.randomLetterGenerator();
            int num = Utilities.randomNumberGenerator(0, 99);
            newID = letter+Integer.toString(num);
        }
        return newID.toUpperCase();
    }


    public static boolean isUniqueSerialNum(String inputID){
        for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++){
            if(CONTENT.getJewelleryItemList().get(i).getContent().getItemSerialNum().contains(inputID)){
                return false;
            }
        }
        return true;
    }

    public static String uniqueSerialNumEnforce(String inputString){
        if(isUniqueSerialNum(inputString) && contains2LettersAnd2Numbers(inputString) && !(inputString.equals(""))){
            return inputString;
        } else {
            String newSerialNum = inputString;
            while(!isUniqueSerialNum(newSerialNum) || newSerialNum.equals("") || (newSerialNum.length() > 4)){
                char letter1 = randomLetterGenerator();
                char letter2 = randomLetterGenerator();
                int num1 = randomNumberGenerator(0, 9);
                int num2 = randomNumberGenerator(0, 9);
                String number1 = Integer.toString(num1);
                String number2 = Integer.toString(num2);
                int serialNumberOrder = randomNumberGenerator(0,5);
                if(serialNumberOrder == 0){
                    newSerialNum = letter1+number1+letter2+number2;
                } else if(serialNumberOrder == 1) {
                    newSerialNum = number1+letter1+number2+letter2;
                } else if (serialNumberOrder == 2) {
                    newSerialNum = number1+number2+letter1+letter2;
                } else if(serialNumberOrder == 3){
                    newSerialNum = letter1+letter2+number1+number2;
                } else if(serialNumberOrder == 4){
                    newSerialNum = number1+letter1+letter2+number2;
                } else if(serialNumberOrder == 5){
                    newSerialNum = letter1+number1+number2+letter2;
                }
            }
            return newSerialNum.toUpperCase();
        }
    }

    public static boolean isUniqueNum(String inputID){
        for(int i = 0; i < CONTENT.getMaterialList().getListSize(); i++){
            if(Objects.equals(CONTENT.getMaterialList().get(i).getContent().getUniqueID(), inputID)){
                return false;
            }
        }
        return true;
    }

    public static String uniqueMaterialIDEnforce(String inputID){
        if(isUniqueNum(inputID) && !inputID.equals("")){
            return inputID;
        }else {
            String uniqueNum = inputID;
            int i = 0;
            while(!isUniqueNum(uniqueNum) || uniqueNum.equals("")){
                uniqueNum = Integer.toString(i);
                i++;
            }
            return uniqueNum;
        }
    }

    public static int parseInt(String stringID) {
        return Integer.parseInt(stringID);
    }


    public static boolean isValidURL(String url) {
        //This utility method uses the regex library.
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        return m.matches();
    }
}