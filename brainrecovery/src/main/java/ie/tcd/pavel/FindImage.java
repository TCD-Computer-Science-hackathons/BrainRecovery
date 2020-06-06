package ie.tcd.pavel;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.VaadinService;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.io.File;

@Route("img")
public class FindImage extends VerticalLayout
{

    public FindImage()
    {

        try {

            File imgFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images\\square.jpg");
            byte[] imageBytes = Files.readAllBytes(imgFile.toPath());
            StreamResource resource = new StreamResource("dummyImageName.jpg", () -> new ByteArrayInputStream(imageBytes));
            Image image = new Image(resource, "dummy image");
            add(image);
        }
        catch (Exception e)
        {
            System.out.println("ERROR LOADING IMAGE: "+e);
        }
    }
}