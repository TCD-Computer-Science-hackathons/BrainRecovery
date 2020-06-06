package ie.tcd.pavel.pages;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import ie.tcd.pavel.arithmetics.ArithmeticExercise;
import ie.tcd.pavel.arithmetics.ArithmeticTaskGenerator;


import java.util.Random;

public class ArithmeticPage extends VerticalLayout {

    private ArithmeticExercise exercise;
    private H1 exerciseString;
    private Label incorrect;
    private Random random;
    public ScoreWidget score;


    public ArithmeticPage(ScoreWidget score) {
        this.score = score;
        setAlignItems(Alignment.CENTER);
        random = new Random();
        loadPage();
    }

    private void loadPage() {
        removeAll();
        add(new H2("Find the correct answer"));
        exercise = ArithmeticTaskGenerator.generateExerciseByType(random.nextInt(4));
        exerciseString = new H1(exercise.toString());
        add(exerciseString);

        TextField answer = new TextField();
        answer.setPattern("-?[0-9]*");
        answer.setPreventInvalidInput(true);
        answer.setMaxLength(10);
        answer.getStyle().set("width", "10em");
        add(answer);

        incorrect = new Label("");
        add(incorrect);

        Button confirmButton = new Button("Confirm");
        confirmButton.addClickShortcut(Key.ENTER);
        confirmButton.addClickListener(event -> {
            if(answer.getValue() != null&&!answer.getValue().equals("")) {
                if (Integer.parseInt(answer.getValue()) == exercise.getAnswer()) {
                    score.correct++;
                }
                else
                {
                    score.inCorrect++;
                }
                score.generateScore();
                loadPage();
            }
        });
        add(confirmButton);
        add(score);
    }
}
