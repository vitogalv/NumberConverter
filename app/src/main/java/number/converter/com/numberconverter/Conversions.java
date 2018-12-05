package number.converter.com.numberconverter;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all of the methods necessary to convert from one radix to the other radices
 */
public class Conversions {

    /**
     * To convert from decimal form
     * @param decimal the string that represents the value as a decimal
     * @return an array containing the other three number system forms of the provided value
     */
    public static String[] fromDecimal(String decimal){
        Pattern pat = Pattern.compile("[A-Za-z./!@#$%^&*()+=;:'\"?><,|~]");
        Matcher match = pat.matcher(decimal);
        if(match.find()){
            throw new IllegalArgumentException();
        }
        boolean neg = false;
        if(decimal.charAt(0) == '-'){
            decimal = decimal.replaceFirst("-", "");
            neg = true;
        }
        String[] retVal = new String[3];
        retVal[0] = decToRad(decimal, 8);
        retVal[1] = decToRad(decimal, 16);
        retVal[2] = decToRad(decimal, 2);
        if(neg){
            retVal[0] = "-" + retVal[0];
            retVal[1] = "-" + retVal[1];
            retVal[2] = "-" + retVal[2];
        }
        return retVal;
    }

    /**
     * To convert from octal form
     * @param octal the string that represents the value as an octal number
     * @return an array containing the other three number system forms of the provided value
     */
    public static String[] fromOctal(String octal){
        Pattern pat = Pattern.compile("[A-Za-z8-9./!@#$%^&*()+=;:'\"?><,|~]");
        Matcher match = pat.matcher(octal);
        if(match.find()){
            throw new IllegalArgumentException();
        }
        boolean neg = false;
        if(octal.charAt(0) == '-'){
            octal = octal.replaceFirst("-", "");
            neg = true;
        }
        String[] retVal = new String[3];
        retVal[0] = toDec(octal, 8);
        retVal[2] = octToBin(octal);
        retVal[1] = binToHex(retVal[2]);
        if(neg){
            retVal[0] = "-" + retVal[0];
            retVal[2] = "-" + retVal[2];
            retVal[1] = "-" + retVal[1];
        }
        return retVal;
    }

    /**
     * To convert from hexadecimal form
     * @param hex the string that represents the value as a hexadecimal number
     * @return an array containing the other three number system forms of the provided value
     */
    public static String[] fromHex(String hex){
        Pattern pat = Pattern.compile("[G-Zg-z./!@#$%^&*()+=;:'\"?><,|~]");
        Matcher match = pat.matcher(hex);
        if(match.find()){
            throw new IllegalArgumentException();
        }
        boolean neg = false;
        if(hex.charAt(0) == '-'){
            hex = hex.replaceFirst("-", "");
            neg = true;
        }
        String[] retVal = new String[3];
        retVal[0] = toDec(hex, 16);
        retVal[2] = hexToBin(hex);
        retVal[1] = binToOct(retVal[2]);
        if(neg){
            retVal[0] = "-" + retVal[0];
            retVal[2] = "-" + retVal[2];
            retVal[1] = "-" + retVal[1];
        }
        return retVal;
    }

    /**
     * To convert from binary form
     * @param binary the string that represents the value as a binary number
     * @return an array containing the other three number system forms of the provided value
     */
    public static String[] fromBinary(String binary){
        Pattern pat = Pattern.compile("[A-Za-z2-9./!@#$%^&*()+=;:'\"?><,|~]");
        Matcher match = pat.matcher(binary);
        if(match.find()){
            throw new IllegalArgumentException();
        }
        boolean neg = false;
        if(binary.charAt(0) == '-'){
            binary = binary.replaceFirst("-", "");
            neg = true;
        }
        String[] retVal = new String[3];
        retVal[0] = toDec(binary, 2);
        retVal[1] = binToOct(binary);
        retVal[2] = binToHex(binary);
        if(neg){
            retVal[0] = "-" + retVal[0];
            retVal[1] = "-" + retVal[1];
            retVal[2] = "-" + retVal[2];
        }
        return retVal;
    }

    /**
     * Method to  convert from decimal to all other number systems
     * @param decimal the decimal value to be converted
     * @param radix the radix of the number system that is being converted to
     * @return the value with the supplied radix
     */
    private static String decToRad(String decimal, int radix){
        String retVal = "";
        long quotient = Long.parseLong(decimal);
        if(quotient == 0){
            return "0";
        }
        Stack<Integer> remainders = new Stack<>();
        while(quotient != 0){
            int remainder = (int)(quotient%radix);
            quotient = quotient/radix;
            remainders.push(remainder);
        }
        while(!remainders.empty()){
            if(radix == 16){
                retVal += toHexChar(remainders.pop());
            }else{
                retVal += remainders.pop();
            }
        }
        return deleteLeading0(retVal);
    }

    /**
     * Convert from any other radix to radix 10
     * @param value the value that is being converted to decimal
     * @param radix the radix of {@param value}
     * @return the value as a decimal
     */
    private static String toDec(String value, int radix){
        String retVal = "";
        int position = 0;
        String allUpper;
        if(radix == 16) {
            allUpper = value.toUpperCase();
        }else{
            allUpper = value;
        }
        long sum = 0;
        for(int i = allUpper.length()-1; i >= 0; i--){
            int intVal;
            if(radix == 16){
                intVal = fromHexChar(allUpper.charAt(i));
            }else{
                intVal = Character.getNumericValue(allUpper.charAt(i));
            }
            sum += intVal * Math.pow(radix, position);
            position++;
        }
        retVal += sum;
        return deleteLeading0(retVal);
    }

    /**
     * Method to convert from octal to binary
     * @param octal the octal value to be converted
     * @return the value as a binary number
     */
    private static String octToBin(String octal){
        String retVal = "";
        for(int i = 0; i < octal.length(); i++){
            int octInt = Character.getNumericValue(octal.charAt(i));
            retVal += getOctTriple(octInt);
        }
        return deleteLeading0(retVal);
    }

    /**
     * Method to convert from hexadecimal to binary
     * @param hex the hexadecimal number of the value being converted
     * @return the value as a binary number
     */
    private static String hexToBin(String hex){
        String retVal = "";
        String hexUpper = hex.toUpperCase();
        for(int i = 0; i < hexUpper.length(); i++){
            retVal += getHexQuad(hexUpper.charAt(i));
        }
        return deleteLeading0(retVal);
    }

    /**
     * Method to convert from binary to octal
     * @param bin the value as a binary number
     * @return the value as an octal number
     */
    private static String binToOct(String bin){
        String binCopy = bin;
        while(binCopy.length()%3 != 0){
            binCopy = "0" + binCopy;
        }
        String retVal = "";
        int subEnd = 3;
        while(subEnd <= binCopy.length()){
            String triple = binCopy.substring(subEnd-3, subEnd);
            char octChar = tripleToOctal(triple);
            retVal += octChar;
            subEnd += 3;
        }
        return deleteLeading0(retVal);
    }

    /**
     * Method to convert from binary to hexadecimal
     * @param bin the value as a binary number
     * @return the value as a hexadecimal number
     */
    private static String binToHex(String bin){
        String binCopy = bin;
        while(binCopy.length()%4 != 0){
            binCopy = "0" + binCopy;
        }
        String retVal = "";
        int subEnd = 4;
        while(subEnd <= binCopy.length()){
            String quad = binCopy.substring(subEnd-4, subEnd);
            char hexChar = quadToHex(quad);
            retVal += hexChar;
            subEnd += 4;
        }
        return deleteLeading0(retVal);
    }

    /**
     * @param val the original string which may or may not contain leading 0's
     * @return a new string with the leading 0's removed
     */
    private static String deleteLeading0(String val){
        StringBuilder builder = new StringBuilder(val);
        while(builder.length() > 0){
            if(builder.charAt(0) == '0'){
                builder.deleteCharAt(0);
            }else{
                return builder.toString();
            }
        }
        return "0";
    }

    /**
     * Method to obtain decimal value of a hexadecimal character
     * @param hex the hex character 0-9 or A-F
     * @return the decimal value of the hex character
     */
    private static int fromHexChar(char hex){
        switch(hex){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
        }
        return -1;
    }

    /**
     * Method to go from decimal value 0-15 inclusive to a hexadecimal character
     * @param val a value in the range of 0-15 inclusive
     * @return the value as a hexadecimal character
     */
    private static char toHexChar(int val){
        switch(val){
            case 0: return '0';
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
        }
        return '-';
    }

    /**
     * Method to get get binary triplet for octal to binary conversion
     * @param octal the octal value
     * @return the binary triplet corresponding to the octal value
     */
    private static String getOctTriple(int octal){
        switch(octal){
            case 0: return "000";
            case 1: return "001";
            case 2: return "010";
            case 3: return "011";
            case 4: return "100";
            case 5: return "101";
            case 6: return "110";
            case 7: return "111";
        }
        return "-";
    }

    /**
     * Method to get binary string of size 4 for use in hex to binary conversion
     * @param hex the hexadecimal character
     * @return the binary string representing the supplied hex value
     */
    private static String getHexQuad(char hex){
        switch(hex){
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1001";
            case 'A': return "1010";
            case 'B': return "1011";
            case 'C': return "1100";
            case 'D': return "1101";
            case 'E': return "1110";
            case 'F': return "1111";
        }
        return "-";
    }

    /**
     * Method to get the hexadecimal character equivalent to a binary string of size 4
     * @param quad the binary string of size 4
     * @return the corresponding the hexadecimal character
     */
    private static char quadToHex(String quad){
        switch(quad){
            case "0000": return '0';
            case "0001": return '1';
            case "0010": return '2';
            case "0011": return '3';
            case "0100": return '4';
            case "0101": return '5';
            case "0110": return '6';
            case "0111": return '7';
            case "1000": return '8';
            case "1001": return '9';
            case "1010": return 'A';
            case "1011": return 'B';
            case "1100": return 'C';
            case "1101": return 'D';
            case "1110": return 'E';
            case "1111": return 'F';
        }
        return '-';
    }

    /**
     * Method to get the octal character equivalent to a binary string of size 3
     * @param triple the 3 bit binary string
     * @return the octal character corresponding to the triplet
     */
    private static char tripleToOctal(String triple){
        switch(triple){
            case "000": return '0';
            case "001": return '1';
            case "010": return '2';
            case "011": return '3';
            case "100": return '4';
            case "101": return '5';
            case "110": return '6';
            case "111": return '7';
        }
        return '-';
    }
}

