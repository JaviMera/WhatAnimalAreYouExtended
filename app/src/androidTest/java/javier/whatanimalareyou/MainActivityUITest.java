package javier.whatanimalareyou;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.widget.ImageView;

import javier.whatanimalareyou.model.animals.AnimalBase;
import javier.whatanimalareyou.model.animals.AnimalList;
import javier.whatanimalareyou.model.animals.concretes.Dolphin;
import javier.whatanimalareyou.model.animals.concretes.Elephant;
import javier.whatanimalareyou.model.animals.concretes.Monkey;
import javier.whatanimalareyou.model.animals.concretes.RedPanda;
import javier.whatanimalareyou.model.animals.concretes.Tiger;
import javier.whatanimalareyou.ui.MainActivity.MainActivity;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Javier on 10/4/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void statementTextViewDisplaysFirstStatementText() throws Exception {

        // Arrange
        String[] statements = activityRule.getActivity().getResources().getStringArray(R.array.statements_array);
        String expectedStatementText = statements[0];

        // Assert
        onView(withId(R.id.statementTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.statementTextView)).check(matches(withText(expectedStatementText)));
    }

    @Test
    public void statementCountTextViewDisplaysCorrectCount() throws Exception {

        // Arrange
        String[] statements = activityRule.getActivity().getResources().getStringArray(R.array.statements_array);
        String expectedStatementCountText = "1 of " + statements.length;

        // Assert
        onView(withId(R.id.statementCountTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.statementCountTextView)).check(matches(withText(expectedStatementCountText)));
    }

    @Test
    public void activityCreateSpinnerIsVisible() throws Exception {

        // Assert
        onView(withId(R.id.choiceSpinnerView)).check(matches(isDisplayed()));
    }

    @Test
    public void activityCreateSpinnerHasCorrectChoice() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);

        // Act
        onView(withId(R.id.choiceSpinnerView)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choices[0]))).perform(click());

        // Assert
        onView(withId(R.id.choiceSpinnerView)).check(matches(withSpinnerText(choices[0])));
    }

    @Test
    public void activityCreateNavigationButtons() throws Exception {

        // Assert
        onView(withId(R.id.previousButtonView)).check(matches(isDisplayed()));
        onView(withId(R.id.previousButtonView)).check(matches(not(isEnabled())));
        onView(withId(R.id.nextButtonView)).check(matches(isDisplayed()));
    }

    @Test
    public void activityOnCreateResultsButtonNotVisible() throws Exception {

        // Assert
        onView(withId(R.id.resultsButton)).check(matches(not(isDisplayed())));
    }

    @Test
    public void nextButtonPress() throws Exception {

        // Arrange
        String[] statements = activityRule.getActivity().getResources().getStringArray(R.array.statements_array);
        int enabledColor = ContextCompat.getColor(activityRule.getActivity(), R.color.plain_white);

        //  Act
        onView(withId(R.id.nextButtonView)).perform(click());

        // Assert
        onView(withId(R.id.statementTextView)).check(matches(withText(statements[1])));
        onView(withId(R.id.previousButtonView)).check(matches(isEnabled()));
        onView(withId(R.id.previousButtonView)).check(matches(new ButtonTextColorMatcher(enabledColor)));
    }

    @Test
    public void nextButtonDisabledAtLastStatement() throws Exception {

        // Arrange
        String[] statements = activityRule.getActivity().getResources().getStringArray(R.array.statements_array);
        int disabledColor = ContextCompat.getColor(activityRule.getActivity(), R.color.disabled_text_color);

        // Act
        for(int i = 0 ; i < statements.length - 1 ; i++)
            onView(withId(R.id.nextButtonView)).perform(click());

        // Assert
        onView(withId(R.id.nextButtonView)).check(matches(not(isEnabled())));
        onView(withId(R.id.nextButtonView)).check(matches(new ButtonTextColorMatcher(disabledColor)));
    }

    @Test
    public void previousButtonEnablesNextButton() throws Exception {

        // Arrange
        String[] statements = activityRule.getActivity().getResources().getStringArray(R.array.statements_array);
        int enabledColor = ContextCompat.getColor(activityRule.getActivity(), R.color.plain_white);

        // Act
        for(int i = 0 ; i < statements.length - 1 ; i++)
            onView(withId(R.id.nextButtonView)).perform(click());

        onView(withId(R.id.previousButtonView)).perform(click());

        // Assert
        onView(withId(R.id.nextButtonView)).check(matches(isEnabled()));
        onView(withId(R.id.nextButtonView)).check(matches(new ButtonTextColorMatcher(enabledColor)));
    }

    @Test
    public void previousButtonShowsPreviousStatement() throws Exception {

        // Arrange
        String[] statements = activityRule.getActivity().getResources().getStringArray(R.array.statements_array);

        // Act
        for(int i = 0 ; i < statements.length - 1 ; i++)
            onView(withId(R.id.nextButtonView)).perform(click());

        onView(withId(R.id.previousButtonView)).perform(click());

        // Assert
        onView(withId(R.id.statementTextView)).check(matches(withText(statements[3])));
    }

    @Test
    public void previousButtonDisabledAtFirstStatement() throws Exception {

        // Arrange
        int disabledColor = ContextCompat.getColor(activityRule.getActivity(), R.color.disabled_text_color);

        // Act
        onView(withId(R.id.nextButtonView)).perform(click());
        onView(withId(R.id.previousButtonView)).perform(click());

        // Assert
        onView(withId(R.id.previousButtonView)).check(matches(not(isEnabled())));
        onView(withId(R.id.previousButtonView)).check(matches(new ButtonTextColorMatcher(disabledColor)));
    }

    @Test
    public void nextButtonUpdatesStatementCounterTextView() throws Exception {

        // Arrange
        String expectedText = "2 of 5";

        // Act
        onView(withId(R.id.nextButtonView)).perform(click());

        // Assert
        onView(withId(R.id.statementCountTextView)).check(matches(withText(expectedText)));
    }

    @Test
    public void previousButtonUpdatesStatementCounterTextView() throws Exception {

        // Arrange
        String expectedText = "1 of 5";

        // Act
        onView(withId(R.id.nextButtonView)).perform(click());
        onView(withId(R.id.previousButtonView)).perform(click());

        // Assert
        onView(withId(R.id.statementCountTextView)).check(matches(withText(expectedText)));
    }

    @Test
    public void spinnerTextDisplaysFirstItemWhenThereIsNoSelection() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);
        String expectedChoice = choices[0];

        // Act
        onView(withId(R.id.choiceSpinnerView)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choices[2]))).perform(click());
        onView(withId(R.id.nextButtonView)).perform(click());

        // Assert
        onView(withId(R.id.choiceSpinnerView)).check(matches(withSpinnerText(expectedChoice)));
    }

    @Test
    public void spinnerTextDisplaysPreviouslySelectedItem() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);
        String expectedChoice = choices[2];

        // Act
        onView(withId(R.id.choiceSpinnerView)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choices[2]))).perform(click());
        onView(withId(R.id.nextButtonView)).perform(click());
        onView(withId(R.id.previousButtonView)).perform(click());

        // Assert
        onView(withId(R.id.choiceSpinnerView)).check(matches(withSpinnerText(expectedChoice)));
    }

    @Test
    public void spinnerTextDisplaysPreviouslySelectedItemAfterNextButtonPress() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);
        String expectedChoice = choices[3];

        // Act
        onView(withId(R.id.choiceSpinnerView)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choices[0]))).perform(click());
        onView(withId(R.id.nextButtonView)).perform(click());
        onView(withId(R.id.choiceSpinnerView)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choices[3]))).perform(click());
        onView(withId(R.id.previousButtonView)).perform(click());
        onView(withId(R.id.nextButtonView)).perform(click());

        // Assert
        onView(withId(R.id.choiceSpinnerView)).check(matches(withSpinnerText(expectedChoice)));
    }

    @Test
    public void resultButtonVisibleAfterReachingLastStatement() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);

        // Act
        for(String s : choices){
            onView(withId(R.id.nextButtonView)).perform(click());
        }

        // Assert
        onView(withId(R.id.resultsButton)).check(matches(isDisplayed()));
    }

    @Test
    public void resultButtonDisplaysCaptionDialogMessage() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);

        // Act
        for(String s : choices){
            onView(withId(R.id.nextButtonView)).perform(click());
        }

        onView(withId(R.id.resultsButton)).perform(click());

        // Assert
        onView(withText(R.string.caption_question_text)).check(matches(isDisplayed()));
    }

    @Test
    public void resultButtonPressNewActivityResult() throws Exception {

        // Arrange
        String[] choices = activityRule.getActivity().getResources().getStringArray(R.array.choices_array);
        Integer[] imageIds = {R.drawable.dolphin, R.drawable.elephant, R.drawable.monkey, R.drawable.redpanda, R.drawable.tiger};
        String expectedCaption = "bark";

        // Act
        for(String s : choices){
            onView(withId(R.id.nextButtonView)).perform(click());
        }

        onView(withId(R.id.resultsButton)).perform(click());
        onView(withId(R.id.captionEditTextView)).perform(typeText(expectedCaption));
        onView(withId(R.id.submitCaptionButton)).perform(click());

        // Assert
        onView(withId(R.id.animalResultImageView)).check(matches(new ImageViewMatcher(imageIds)));
        onView(withId(R.id.captionButtonView)).check(matches(withText(expectedCaption)));
    }

    private String createAnimalResultRegex() {

        String animalsToLookFor = "(";
        List<AnimalBase> animals = createAnimalList(1).getAnimals();

        for(int i = 0 ; i < animals.size() ; i++){

            animalsToLookFor += animals.get(i).getName();
            if( i < animals.size() - 1)
                animalsToLookFor += "|";
            else
                animalsToLookFor += ")";
        }

        return animalsToLookFor;
    }

    private AnimalList createAnimalList(int defaultId) {

        return new AnimalList(
            new Dolphin(defaultId),
            new Elephant(defaultId),
            new Monkey(defaultId),
            new RedPanda(defaultId),
            new Tiger(defaultId));
    }

    private class ButtonTextColorMatcher extends BaseMatcher {

        private int mExpectedColor;

        public ButtonTextColorMatcher(int color) {
            mExpectedColor = color;
        }

        @Override
        public boolean matches(Object item) {
            AppCompatButton button = (AppCompatButton)item;
            int actualColor = button.getCurrentTextColor();

            return mExpectedColor == actualColor;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Text color must be " + mExpectedColor);
        }
    }

    private class ImageViewMatcher extends BaseMatcher {

        private Integer[] mDrawableIds;

        public ImageViewMatcher(Integer[] drawableIds) {
            mDrawableIds = drawableIds;
        }

        @Override
        public boolean matches(Object item) {

            ImageView image = (ImageView)item;

            return image.getDrawable().isVisible();
        }

        @Override
        public void describeTo(Description description) {

            description.appendText("Image view does not contain either of the following ids " + mDrawableIds);
        }

    }
}