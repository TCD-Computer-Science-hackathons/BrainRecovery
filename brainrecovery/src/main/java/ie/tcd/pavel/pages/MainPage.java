package ie.tcd.pavel.pages;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Random;

@Route("")
public class MainPage extends VerticalLayout {


    private ScoreWidget score;
    private CompletePage completePage;
    private VerticalLayout startPage = new VerticalLayout();
    private static final Random rnd = new Random();

    public MainPage() {

        this.setSizeFull();
        setAlignItems(Alignment.CENTER);
        score = new ScoreWidget();

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
        buttons.add(random);

        startPage.setJustifyContentMode(JustifyContentMode.CENTER);
        startPage.setAlignItems(Alignment.CENTER);
        startPage.setWidth("30em");
        startPage.add(title);
        TextField numberField = new TextField("Number of tasks");
        numberField.setPattern("[0-9]*");
        numberField.setPreventInvalidInput(true);
        startPage.add(numberField);
        for (Button button : buttons) {
            button.setWidth("25em");
            button.setHeight("10em");
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            button.addClickListener(event-> {
                if (numberField.getValue() != null && !numberField.getValue().equals("")&&
                        Integer.parseInt(numberField.getValue()) != 0)
                {GenerateGame(Integer.parseInt(numberField.getValue()),event.getSource().getText());}});
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
            case "Find Image":add(new FindImagePage(score)); break;
            case "Arithmetics":add( new ArithmeticPage(score));break;
            case "Grammar":add(new GrammarPage(score));break;
            case "Name Image":add(new NameImagePage(score));break;
            case "Random":
                int rand = rnd.nextInt(4);
                switch (rand)
                {
                    case 0:add(new FindImagePage(score)); break;
                    case 1:add( new ArithmeticPage(score));break;
                    case 2:add(new GrammarPage(score));break;
                    case 3:add(new NameImagePage(score));break;
                    default:break;
                }
                break;
            default:break;
        }
    }

    public void goToStarterScreen()
    {
        removeAll();
        add(startPage);
    }

    public void goToCompleteScreen() {
        completePage = new CompletePage(score.correct, score.inCorrect);
        completePage.continueButton.addClickListener(buttonClickEvent -> {
           goToStarterScreen();
        });
        removeAll();
        add(completePage);
    }

    public void resetScore(int goal)
    {
        score.goal = goal;
        score.correct = score.inCorrect = 0;
        score.generateScore();
    }
}
