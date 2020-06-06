package ie.tcd.pavel.pages;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Title;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.ArrayList;

@Route("")
public class MainPage extends VerticalLayout {


    private ScoreWidget score;
    private ArithmeticPage arithmeticPage;
    private FindImagePage findImagePage;
    private GrammarPage grammarPage;
    private NameImagePage nameImagePage;
    private VerticalLayout startPage = new VerticalLayout();

    public MainPage() {

        this.setSizeFull();
        setAlignItems(Alignment.CENTER);
        score = new ScoreWidget();

        resetScore(11);

        findImagePage = new FindImagePage(score);
        arithmeticPage = new ArithmeticPage(score);
        grammarPage = new GrammarPage(score);
        nameImagePage = new NameImagePage(score);

        H1 title = new H1("Brain Recovery");

        Button arithmetics = new Button("Arithmetics");
        Button findImage = new Button("Find Image");
        Button grammar = new Button("Grammar");
        Button nameImage = new Button("Name Image");
        Button random = new Button("Random");


        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(arithmetics);
        buttons.add(findImage);
        buttons.add(grammar);
        buttons.add(nameImage);

        startPage.setJustifyContentMode(JustifyContentMode.CENTER);
        startPage.setAlignItems(Alignment.CENTER);
        startPage.add(title);
        startPage.setWidth("30em");
        for (Button button : buttons) {
            button.setWidth("25em");
            button.setHeight("10em");
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            button.addClickListener(event->{GenerateGame(10,event.getSource().getText());});
            startPage.add(button);
        }
        add(startPage);
    }

    public void GenerateGame(int numberOfGames, String type)
    {
        this.removeAll();
        resetScore(numberOfGames);
        switch (type)
        {
            case "Find Image":add(findImagePage); break;
            case "Arithmetics":add(arithmeticPage);break;
            case "Grammar":add(grammarPage);break;
            case "Name Image":add(nameImagePage);break;
            case "Random":break;
            default:break;
        }
    }

    public void goToStarterScreen()
    {
        removeAll();
        add(startPage);
    }

    public void resetScore(int goal)
    {
        score.goal = goal;
        score.correct = score.inCorrect = 0;
        score.generateScore();
    }




}
