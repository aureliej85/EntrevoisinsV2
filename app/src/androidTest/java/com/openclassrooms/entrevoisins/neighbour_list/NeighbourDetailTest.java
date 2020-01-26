package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class NeighbourDetailTest {


    private NeighbourApiService mApiService;
    private int mPosition = 2;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mApiService = DI.getNewInstanceApiService();
    }


    @Test
    public void fromNeighboursListToDetailNeighbour() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).perform(actionOnItemAtPosition(mPosition, click()));
        onView(ViewMatchers.withId(R.id.bigUsernameText)).check(matches(withText(mApiService.getNeighbours().get(mPosition).getName())));
        onView(ViewMatchers.withId(R.id.adressText)).check(matches(withText(mApiService.getNeighbours().get(mPosition).getAdress())));
        onView(ViewMatchers.withId(R.id.telText)).check(matches(withText(mApiService.getNeighbours().get(mPosition).getTel())));
        onView(ViewMatchers.withId(R.id.urlText)).check(matches(withText(mApiService.getNeighbours().get(mPosition).getUrl())));
        onView(ViewMatchers.withId(R.id.descText)).check(matches(withText(mApiService.getNeighbours().get(mPosition).getDescription())));
    }
}
