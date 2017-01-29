package hr.foi.air.solex;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.foi.air.solex.activities.companies.CompanyProfileActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CompanyProfileActivityTest {

    @Rule
    public ActivityTestRule<CompanyProfileActivity> profileTestRule =
            new ActivityTestRule<CompanyProfileActivity>(CompanyProfileActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, CompanyProfileActivity.class);
                    intent.putExtra("companyId",1);
                    return intent;
                }
            };
    @Test
    public void testCompanyProfile() throws Exception{
        onView(withId(R.id.activity_company_profile_btnToggleProjectsLayout))
                .perform(click());

        onView(withId(R.id.activity_company_profile_btnToggleGenInfoLayout))
                .perform(click());

        onView(withId(R.id.activity_company_profile_btnToggleMainTechLayout))
                .perform(click());

        onView(withId(R.id.btnStartUpdateCompanyData))
                .perform(click());
    }

}
