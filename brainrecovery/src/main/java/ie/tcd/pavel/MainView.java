package ie.tcd.pavel;

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

@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    public MainView() {

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

        //VerticalLayout vl = new VerticalLayout();

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        //addClassName("centered-content");
        add(title);
        setAlignItems(Alignment.CENTER);
        for(Button button : buttons) {
            button.setWidth("25em");
            button.setHeight("10em");
            add(button);
        }
    }

}
