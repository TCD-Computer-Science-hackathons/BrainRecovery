package ie.tcd.pavel.pages;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import ie.tcd.pavel.arithmetics.ArithmeticExercise;
import ie.tcd.pavel.arithmetics.ArithmeticTaskGenerator;

import java.util.Random;

@Route("arithmetic")
public class ArithmeticPage extends VerticalLayout {

    private ArithmeticExercise exercise;
    private H1 exerciseString;
    private Label incorrect;
    private Random random;


    public ArithmeticPage() {
        setAlignItems(Alignment.CENTER);
        random = new Random();
        loadPage();
    }

    private void loadPage() {
        removeAll();
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
            if(answer.getValue() != null) {
                if (Integer.parseInt(answer.getValue()) == exercise.getAnswer()) {
                    loadPage();
                    // Answer is correct
                }
            } else {
                incorrect.setText("Wrong Answer");
            }
        });
        add(confirmButton);
    }
}
