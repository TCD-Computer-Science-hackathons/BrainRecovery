package ie.tcd.pavel.pages;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;

import java.util.Optional;

public class ScoreWidget extends VerticalLayout
{
    public int correct;
    public int inCorrect;
    public int goal;
    ProgressBar progressBar;
    H2 info;
    Button goBack;

    public ScoreWidget()
    {
        progressBar = new ProgressBar();
        info = new H2("");
        goBack = new Button("Exit");
        this.goBack.addClickListener(event->{
            exit();
        });
        this.goal = 10;
        this.correct = 0;
        this.inCorrect = 0;
        this.setWidth("30em");
        this.setAlignItems(Alignment.CENTER);
        generateScore();
    }

    public void exit()
    {
        Optional<Component> mainPageOptional = this.getParent();
        if(mainPageOptional.isPresent())
        {
            VerticalLayout gamePage = (VerticalLayout) mainPageOptional.get();
            if(gamePage.getParent().isPresent())
            {
                MainPage mainPage = (MainPage)gamePage.getParent().get();
                mainPage.goToStarterScreen();
            }

        }
    }


    public void generateScore()
    {
        if(correct==goal)
        {
            exit();
        }
        else {
            removeAll();
            progressBar.setValue((1.0 * correct) / goal);
            info.setText(String.format("Correct: %d, Incorrect: %d, Goal: %d", correct, inCorrect, goal));
            add(info, progressBar,goBack);
        }
    }
}
