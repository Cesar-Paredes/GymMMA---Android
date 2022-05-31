package com.example.mma;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void visibleLogoImg() {
        Espresso.onView(withId(R.id.imageView_mainGloves)).check(matches(isDisplayed()));
    }

    @Test
    public void visibleLogo() {
        Espresso.onView(withId(R.id.textView_mainLogo)).check(matches(isDisplayed()));
    }

    @Test
    public void visibleEmailInput() {
        Espresso.onView(withId(R.id.editText_mainUsername)).check(matches(isDisplayed()));
    }

    @Test
    public void visiblePasswordInput() {
        Espresso.onView(withId(R.id.editText_mainPassword)).check(matches(isDisplayed()));
    }

    @Test
    public void visibleLoginButton() {
        Espresso.onView(withId(R.id.button_mainLogin)).check(matches(isDisplayed()));
    }

    @Test
    public void visibleRegisterButton() {
        Espresso.onView(withId(R.id.button_mainRegister)).check(matches(isDisplayed()));
    }


    @After
    public void tearDown() throws Exception {

    }


}
