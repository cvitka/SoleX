package hr.foi.air.solex;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.foi.air.solex.activities.common.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    private String email;
    private String password;

    @Rule
    public ActivityTestRule<LoginActivity> mInputLoginTextActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void initValues(){
        email="test";
        password="test1";
    }
    @Test
    public void inputText() throws Exception{
        onView(withId(R.id.activity_login_etInputEmail))
                .perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.activity_login_etInputPassword))
                .perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.activity_login_etInputEmail))
                .check(matches(withText(email)));

        onView(withId(R.id.activity_login_etInputPassword))
                .check(matches(withText(password)));

        onView(withId(R.id.activity_login_btnLogin))
                .perform(click());

    }

}
