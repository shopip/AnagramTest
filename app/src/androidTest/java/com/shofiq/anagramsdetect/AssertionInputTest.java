package com.shofiq.anagramsdetect;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AssertionInputTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void assertionInputTest() throws InterruptedException {

        //Initially for empty value submit button should be disabled
        onView(withId(R.id.btnSubmit)).check(matches(not(isEnabled())));

        //Checking with positive value to test anagram, result should be true, for that first text is set here
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.etFirstText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tiFirstText),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Fired"), closeSoftKeyboard());

        //Submit button should be disabled as second string is not populated yet
        onView(withId(R.id.btnSubmit)).check(matches(not(isEnabled())));

        //Checking with positive value to test anagram, result should be true, for that second text is set here
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.etSecondText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tiSecondText),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Fried"), closeSoftKeyboard());

        //Submit button should be enabled as both string is populated
        onView(withId(R.id.btnSubmit)).check(matches(isEnabled()));

        ViewInteraction button = onView(
                allOf(withId(R.id.btnSubmit),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        //checking if submit button is visible
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSubmit), withText(R.string.check_anagram),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());


        //checking for positive result , result should be positive
        ViewInteraction textView = onView(
                allOf(withText(R.string.anagram_pass),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));


        textView.check(matches(withText(R.string.anagram_pass)));


        //checking for negative result , changing first string accordingly
        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.etFirstText), withText("Fired"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tiFirstText),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Fires"));

        //As the text changes, result string of previous input should go blank
        onView(withId(R.id.resultString)).check(matches(withText("")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnSubmit), withText(R.string.check_anagram),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        //checking for negative result
        ViewInteraction textView2 = onView(
                allOf(withText(R.string.anagram_fail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        //checking for negative result
        textView2.check(matches(withText(R.string.anagram_fail)));


        //checking for submit button enable toggleness, changing first string, submit button should be disabled after that
        onView(withId(R.id.etFirstText)).perform(replaceText(""));
        //checking for submit button enable toggleness, as first string is empty, submit button should be disabled
        onView(withId(R.id.btnSubmit)).check(matches(not(isEnabled())));

        //populating first string again
        onView(withId(R.id.etFirstText)).perform(replaceText("Fired"));

        //checking for submit button enable toggleness, as first string is populated, submit button should be enabled
        onView(withId(R.id.btnSubmit)).check(matches(isEnabled()));

        //checking for submit button enable toggleness, changing second string, submit button should be disabled after that
        onView(withId(R.id.etSecondText)).perform(replaceText(""));

        //checking for submit button enable toggleness, as second string is empty, submit button should be disabled
        onView(withId(R.id.btnSubmit)).check(matches(not(isEnabled())));


    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
