package hr.foi.air.solex;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.foi.air.solex.activities.companies.CompanyProjectsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CompanyProjectsActivityTest {

    @Rule
    public ActivityTestRule<CompanyProjectsActivity> companyProjectsTest =
            new ActivityTestRule<CompanyProjectsActivity>(CompanyProjectsActivity.class);

    @Test
    public void dataTest() throws Exception{

        onView(withId(R.id.btnAddNewProject))
                .perform(click());
    }

}
