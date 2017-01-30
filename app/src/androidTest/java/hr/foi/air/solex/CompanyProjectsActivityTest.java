package hr.foi.air.solex;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.foi.air.solex.activities.companies.CompanyProfileActivity;
import hr.foi.air.solex.activities.companies.CompanyProjectsActivity;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.utils.UserType;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CompanyProjectsActivityTest {
    @Before
    public void setUserSingleton(){
        User.getInstance().setEmail("poduzece");
        User.getInstance().setId(1);
        User.getInstance().setUserType(UserType.COMPANY);
    }

    @Rule
    public ActivityTestRule<CompanyProjectsActivity> companyProjectsTest =
            new ActivityTestRule<CompanyProjectsActivity>(CompanyProjectsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    setUserSingleton();
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, CompanyProjectsActivity.class);
                    intent.putExtra("companyId", 1);
                    return intent;
                }
            };


    @Test
    public void dataTest() throws Exception{

        onView(withId(R.id.btnAddNewProject))
                .perform(click());
    }

}
