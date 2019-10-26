package com.shofiq.anagramsdetect.Util;

import java.util.Arrays;

public class AnagramUtil {


    /* Check Anagram returns the result of if two strings are anagram or not
   first param = String
   second param = String
   returns result bool
    */
    public static boolean checkAnagram (String firstString, String secondString) {

        // trimming all spaces
        String s1 = firstString.replaceAll(" ", "");
        String s2 = secondString.replaceAll(" ", "");
        // if both are not of same length , it cant be anagram
        if (s1.length() != s2.length() || s1.length() == 0 || s2.length() == 0) {
            return false ;
        }
        else
        {
            // converting string to lowercase and to char array so that we can sort alphabetically and if we match two character array,well get the result if two are anagram or not
            char[] ArrayS1 = s1.toLowerCase().toCharArray();
            char[] ArrayS2 = s2.toLowerCase().toCharArray();
            Arrays.sort(ArrayS1);
            Arrays.sort(ArrayS2);
            return Arrays.equals(ArrayS1, ArrayS2) ? true : false ;
        }


    }


}
