package hr.foi.air.solex;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import hr.foi.air.solex.activities.companies.UpdateCompanyDataActivity;
import hr.foi.air.solex.models.mcompanies.Company;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
@LargeTest
public class UpdateCompanyDataActivityTest {

    Company mThisCompany = new Company();

    private String name;
    private String email;
    private String adress;
    private String webPage;
    private String director;
    private Integer numberOfWorkers;

    public void postaviPodatke(){
        mThisCompany.setId(15);
        //mThisCompany.setEmail("test");
        mThisCompany.setPicture("");
        //mThisCompany.setName("Proba");
        //mThisCompany.setAddress("adresa");
        //mThisCompany.setDirektor("Malimoj");
    }

    @Rule
    public ActivityTestRule<UpdateCompanyDataActivity> mUpdateTestRule =
            new ActivityTestRule<UpdateCompanyDataActivity>(UpdateCompanyDataActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    postaviPodatke();
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, UpdateCompanyDataActivity.class);
                    intent.putExtra("myObject", new Gson().toJson(mThisCompany));
                    return intent;
                }
            };

    @Before
    public void intitValues(){
        name= "test";
        email = "test";
        adress = "neka tamo";
        webPage = "www.page.com";
        director = "Matej";
        numberOfWorkers = 5;
    }

    @Test
    public void testUpdateData() throws Exception{

        onView(withId(R.id.activity_update_company_etNewName))
                .perform(typeText(name), closeSoftKeyboard());

        onView(withId(R.id.activity_update_company_etNewEmail))
                .perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.activity_update_company_etNewAddress))
                .perform(typeText(adress), closeSoftKeyboard());

        onView(withId(R.id.activity_update_company_etWebPage))
                .perform(typeText(webPage), closeSoftKeyboard());


        onView(withId(R.id.activity_update_company_etNewName))
                .check(matches(withText(name)));

        onView(withId(R.id.activity_update_company_etNewAddress))
                .check(matches(withText(adress)));

        onView(withId(R.id.activity_update_company_etNewEmail))
                .check(matches(withText(email)));

        onView(withId(R.id.activity_update_company_etWebPage))
                .check(matches(withText(webPage)));

        onView(withId(R.id.activity_update_company_etNewName))
                .check(matches(withText(name)));

        /*onView(withId(R.id.activity_update_company_btnUpdateData))
                .perform(click());*/


    }
}

