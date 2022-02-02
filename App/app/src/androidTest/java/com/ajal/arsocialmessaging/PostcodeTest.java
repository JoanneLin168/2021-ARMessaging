package com.ajal.arsocialmessaging;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.location.Location;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.ajal.arsocialmessaging.util.PostcodeHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 * Note: the Android device needs to be unlocked for it to work
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @see <a href="https://developer.android.com/training/testing/espresso/basics">Espresso Basics</a>
 */
@RunWith(AndroidJUnit4.class)
public class PostcodeTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void init() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getSupportFragmentManager().beginTransaction();
        });
    }

    @Test
    public void test_GetCorrectPostCode() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String postcode = PostcodeHelper.getPostCode(appContext, 51.4559275, -2.6031669);
        assertEquals("BS8 1UB", postcode);
    }

    @Test
    public void test_DisplayCorrectPostcode() {
        onView(withId(R.id.navigation_settings)).perform(click());
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Location location = PostcodeHelper.getLocation(appContext);
        String postcode = PostcodeHelper.getPostCode(appContext, location.getLatitude(), location.getLongitude());
        onView(withId(R.id.text_currentPostcode)).check(matches(withText(containsString(postcode))));
    }

}