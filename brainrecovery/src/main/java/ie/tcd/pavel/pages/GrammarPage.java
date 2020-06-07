package ie.tcd.pavel.pages;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GrammarPage extends VerticalLayout {

    private ArrayList<String> sentences;
    private ArrayList<ArrayList<String>> options;
    private Random random;
    private H1 title;
    private String correctAnswer;
    private int sentenceIndex;
    public ScoreWidget score;

    public GrammarPage(ScoreWidget score) {
        this.score = score;
        sentences = new ArrayList<>();
        options = new ArrayList<>();
        random = new Random();
        title = new H1();

        setAlignItems(Alignment.CENTER);

        // Read in the grammar questions
        ArrayList<String> tempArray = new ArrayList<>();
        try {
            tempArray = readGrammarQuestions();
        } catch(Exception e) {
            System.out.println("Failed to read grammar questions: " + e);
        }
        for(int i = 0; i < tempArray.size(); i++) {
            if(i % 2 == 0) {
                sentences.add(tempArray.get(i));
            } else {
                ArrayList<String> temp = new ArrayList<>(Arrays.asList(tempArray.get(i).split(",")));
                options.add(temp);
            }
        }

        // Generate the page using them
        sentenceIndex = 0;
        generatePage();

    }

    private ArrayList<String> readGrammarQuestions() throws IOException {

        ArrayList<String> lines = new ArrayList<>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(NameImagePage.class.
                    getResourceAsStream("/sentences/sentences.txt")));
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to load shader from file path: " , e);
        }
        return lines;
    }

    private void generatePage() {
        removeAll();

        // Make sure its not the same page every time
        int tempIndex = sentenceIndex;
        sentenceIndex = random.nextInt(sentences.size());
        if(tempIndex == sentenceIndex) {
            if(sentenceIndex < sentences.size() - 1) {
                sentenceIndex++;
            } else {
                sentenceIndex--;
            }
        }

        String sentence = sentences.get(sentenceIndex);
        ArrayList<String> o = options.get(sentenceIndex);
        Collections.shuffle(o);

        ArrayList<Button> buttons = new ArrayList<>();
        for(String s : o) {
            if(s.toCharArray()[0] == '%') {
                correctAnswer = s.substring(1);
            }
            Button button = new Button();
            button.getStyle().set("width", "20em");
            button.getStyle().set("height", "8em");
            if(s.toCharArray()[0] == '%') {
                button.setText(s.substring(1));
            } else {
                button.setText(s);
            }
            button.addClickListener(buttonClickEvent -> {
                if(button.getText().equals(correctAnswer)) {
                    score.correct++;
                    button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                } else {
                    score.inCorrect++;
                    button.addThemeVariants(ButtonVariant.LUMO_ERROR);
                }
                score.generateScore();
                generatePage();
            });
            buttons.add(button);
        }
        title.setText(sentence);

        // Add everything
        add(title);
        for(Button b : buttons) {
            add(b);
        }
        add(score);
    }
}
