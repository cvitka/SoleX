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

import hr.foi.air.solex.activities.developers.UpdateDeveloperDataActivity;
import hr.foi.air.solex.models.mdevelopers.Developer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
@LargeTest
public class UpdateDeveloperDataActivityTest {

    Developer mThisDeveloper = new Developer();

    private String name;
    private String surname;
    private String email;
    private String adress;
    private String contactNumber;
    private String years;
    private Integer id= 42;


    public void postaviPodatke() {
        mThisDeveloper.setId(id);
        //mThisDeveloper.setEmail("testd");
        mThisDeveloper.setPicture("");
        //mThisDeveloper.setName("Proba");
        //mThisDeveloper.setAddress("adresa");
        //mThisDeveloper.setDirektor("Malimoj");
    }

    @Rule
    public ActivityTestRule<UpdateDeveloperDataActivity> mUpdateTestRule =
            new ActivityTestRule<UpdateDeveloperDataActivity>(UpdateDeveloperDataActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    postaviPodatke();
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent data = new Intent(targetContext, UpdateDeveloperDataActivity.class);
                    data.putExtra("myObject", new Gson().toJson(mThisDeveloper));
                    return data;
                }
            };

    @Before
    public void intitValues() {
        name = "Ime";
        surname = "Prezime";
        email = "testd";
        adress = "nekatamo";
        years = "5";
        contactNumber = "1531335";

    }

    @Test
    public void testUpdateData() throws Exception {

        onView(withId(R.id.activity_update_developer_etNewName))
                .perform(typeText(name), closeSoftKeyboard());

        onView(withId(R.id.activity_update_developer_etNewSurname))
                .perform(typeText(surname), closeSoftKeyboard());

        onView(withId(R.id.activity_update_developer_etNewAddress))
                .perform(typeText(adress), closeSoftKeyboard());

        onView(withId(R.id.activity_update_developer_etNewEmail))
                .perform(typeText(email), closeSoftKeyboard());

        //onView(withId(R.id.activity_update_developer_etNewNumber))
               // .perform(typeText(contactNumber));

        //onView(withId(R.id.activity_update_developer_etNewYears))
              //  .perform(typeText(years));


        onView(withId(R.id.activity_update_developer_etNewName))
                .check(matches(withText(name)));

        onView(withId(R.id.activity_update_developer_etNewSurname))
                .check(matches(withText(surname)));

        onView(withId(R.id.activity_update_developer_etNewAddress))
                .check(matches(withText(adress)));

        onView(withId(R.id.activity_update_developer_etNewEmail))
                .check(matches(withText(email)));

        //onView(withId(R.id.activity_update_developer_etNewNumber))
        //        .check(matches(withText(contactNumber)));

        //onView(withId(R.id.activity_update_developer_etNewYears))
        //        .check(matches(withText(years)));

       // onView(withId(R.id.activity_update_developer_btnUpdateData))
       //         .perform(click());

    }
}
