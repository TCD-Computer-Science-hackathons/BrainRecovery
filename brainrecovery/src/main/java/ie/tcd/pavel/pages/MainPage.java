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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route("")
public class MainPage extends VerticalLayout {

    public MainPage() {


        this.setSizeFull();

        H1 title = new H1("Brain Recovery");

        Button five = new Button("5 Minutes");
        Button ten = new Button("10 Minutes");
        Button thirty = new Button("30 Minutes");
        Button hour = new Button("60 Minutes");
        five.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        ten.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        thirty.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        hour.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(five);
        buttons.add(ten);
        buttons.add(thirty);
        buttons.add(hour);

        add(title);
        setAlignItems(Alignment.CENTER);
        for(Button button : buttons) {
            button.setWidth("25em");
            button.setHeight("10em");
            add(button);
        }
    }

}
