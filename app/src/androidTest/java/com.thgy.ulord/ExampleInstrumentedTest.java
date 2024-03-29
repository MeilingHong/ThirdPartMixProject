package com.thgy.ulord;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        // 需要先启动对应的Activity，然后进行操作
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, MainActivity.class);
        mActivityRule.getActivity().startActivity(intent);

        //针对指定的View进行点击操作
        onView(withId(R.id.alipay)).perform(click());
        //

        assertEquals("com.meiling.thirdpart.test", appContext.getPackageName());
    }
}
