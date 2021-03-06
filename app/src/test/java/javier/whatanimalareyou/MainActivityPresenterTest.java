package javier.whatanimalareyou;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javier.whatanimalareyou.ui.MainActivity.MainActivityPresenter;
import javier.whatanimalareyou.ui.MainActivity.MainActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 10/5/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    private MainActivityPresenter mPresenter;

    @Mock
    private MainActivityView mView;

    @Before
    public void SetUp() {

        mPresenter = new MainActivityPresenter(mView);
    }

    @Test
    public void updateTextViewTypeface() throws Exception {

        // Arrange
        TextView tv = new TextView(null);
        Typeface font = Typeface.DEFAULT;

        // Act
        mPresenter.updateViewTypeface(tv, font);

        // Assert
        Mockito.verify(mView).updateViewTypeface(tv, font);
    }

    @Test
    public void setSpinnerAdapter() throws Exception {

        // Arrange
        int spinnerLayout = 1234;
        String fontPath = "fonts/lol.ttf";
        List<String> spinnerChoiceItems = new ArrayList<>();

        // Act
        mPresenter.setSpinnerAdapterView(null, spinnerLayout, fontPath, spinnerChoiceItems);

        // Assert
        Mockito.verify(mView).setSpinnerAdapterView(null, spinnerLayout, fontPath, spinnerChoiceItems);
    }

    @Test
    public void updateStatementText() throws Exception {

        // Arrange
        String text = "some text";

        // Act
        mPresenter.updateStatementText(text);

        // Assert
        Mockito.verify(mView).updateStatementTextViewText(text);
    }

    @Test
    public void updateStatementCounterText() throws Exception {

        // Arrange
        int current = 1;
        int max = 3;

        // Act
        mPresenter.updateStatementCountText(current, max);

        // Assert
        Mockito.verify(mView).updateStatementCountTextViewText(current, max);
    }

    @Test
    public void updateTextColor() throws Exception {

        // Arrange
        View view = new View(null);
        int color = 1234;

        // Act
        mPresenter.updateTextColor(view, color);

        // Assert
        Mockito.verify(mView).updateTextColor(view, color);
    }

    @Test
    public void setViewEnabled() throws Exception {

        // Arrange
        View view = new View(null);
        boolean enabled = true;

        // Act
        mPresenter.setViewEnabled(view, enabled);


        // Assert
        Mockito.verify(mView).setViewEnabled(view, enabled);
    }

    @Test
    public void updateSpinnerSelectedItem() throws Exception {

        // Arrange
        int choicePosition = 2;

        // Act
        mPresenter.updateSpinnerSelectedItem(choicePosition);

        // Assert
        Mockito.verify(mView).updateSpinnerSelectedItem(choicePosition);
    }

    @Test
    public void launchResultsActivity() throws Exception {

        // Arrange
        String animalName = "Tiger";
        int imageId = R.drawable.tiger;
        String caption = "RAWR";

        // Act
        mPresenter.launchResultsActivity(animalName, imageId, caption);

        // Assert
        Mockito.verify(mView).launchResultsActivity(animalName, imageId, caption);
    }
}