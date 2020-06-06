package ie.tcd.pavel;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Route("grammar")
public class GrammarPage extends VerticalLayout {

    private ArrayList<String> sentences;
    private HashMap<String, String[]> sentencesToAnswers;

    private Random random;

    private Label wrong;

    public GrammarPage() {

        random = new Random();
        // TEMPORARY
        sentences.add("Jenny _____ tired");
        sentences.add("_____ lots of animals in the zoo");
        String[] missingWordOptions = new String[] {"be", "%is", "has", "have"};
        sentencesToAnswers.put(sentences.get(0), missingWordOptions);
        missingWordOptions = new String[] {"There", "There is", "%There are", "There aren't"};
        sentencesToAnswers.put(sentences.get(1), missingWordOptions);

        setAlignItems(Alignment.CENTER);
        loadPage();
    }

    private void loadPage() {
        removeAll();

        int randomInt = random.nextInt(sentences.size());

        H1 sentenceH1 = new H1(sentences.get(randomInt));
        add(sentenceH1);
        wrong = new Label("");

        String[] options = sentencesToAnswers.get(sentences.get(randomInt));
        String correctAnswer = "";
        for(int index = 0; index < options.length; index++) {
            if(options[index].toCharArray()[0] == '%') {
                correctAnswer = options[index];
            }
        }
        for(String s : options) {
            Button option = new Button(s);
            option.getStyle().set("width", "10em");
            option.addClickListener(event -> {
                if(option.getText().equals(correctAnswer)) {
                    // ANSWER IS CORRECT
                    loadPage();
                } else {
                    wrong.setText("Wrong Answer");
                }
            });
            add(option);
        }

        add(wrong);
    }

}
