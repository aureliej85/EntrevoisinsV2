package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.rule.ActivityTestRule;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class FavoriteListTest {


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule(ListNeighbourActivity.class);


    @Test
    public void FavoriteTabShouldContainsOnlyFavoritesNeighbours(){
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(3, click()));
        onView(allOf(withId(R.id.detailNeighbour),isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
        pressBack();
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(swipeLeft());
        onView(allOf(withText("Vincent"),isDisplayed())).perform(click());
    }


}



