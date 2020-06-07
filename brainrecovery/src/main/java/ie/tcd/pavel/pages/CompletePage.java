package ie.tcd.pavel.pages;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;

public class CompletePage extends VerticalLayout {

    public Button continueButton;

    public CompletePage(int correct, int incorrect) {

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setWidth("30em");

        H1 title = new H1("Task Completed!");
        continueButton = new Button("Continue");

        Text info = new Text("");
        info.setText(String.format("Correct: %d, Incorrect: %d", correct, incorrect));

        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(1);
        add(title, info, progressBar, continueButton);
    }
}
