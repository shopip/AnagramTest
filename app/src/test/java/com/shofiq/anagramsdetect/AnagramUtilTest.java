package com.shofiq.anagramsdetect;

import com.shofiq.anagramsdetect.Util.AnagramUtil;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramUtilTest {

    // checking anagram with valid input
    @Test
    public void isValidAnagram () {

        assertTrue(AnagramUtil.checkAnagram("bleat","table"));
        assertTrue(AnagramUtil.checkAnagram("fired","fried"));

    }

    //checking anagram with valid input with different case
    @Test
    public void isValidAnagramCaseCheck () {

        assertTrue(AnagramUtil.checkAnagram("bleat","Table"));

    }

    //checking valid input with space inbetween
    @Test
    public void isValidAnagramSpaceCheck () {

        assertTrue(AnagramUtil.checkAnagram("ble at","Ta  ble"));

    }

    //checking with invalid input with same length
    @Test
    public void inalidAnagram () {

        assertFalse(AnagramUtil.checkAnagram("eat","tar"));

    }

    //checking with invalid input with different length
    @Test
    public void inalidAnagram_DifferentLength () {

        assertFalse(AnagramUtil.checkAnagram("Fried","Fire"));

    }

    //checking with invalid input with empty value
    @Test
    public void inalidAnagramEmptyCheck () {

        //first input empty
        assertFalse(AnagramUtil.checkAnagram("","Fire"));
        //second input empty
        assertFalse(AnagramUtil.checkAnagram("Fired",""));
        //both input empty
        assertFalse(AnagramUtil.checkAnagram("",""));


    }


}
