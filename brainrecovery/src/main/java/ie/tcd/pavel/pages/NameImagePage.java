package ie.tcd.pavel.pages;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.ButtonOptions;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Route("nameimage")
public class NameImagePage extends VerticalLayout
{
    private final static Random rnd = new Random();
    HashMap<String, ArrayList<String>> captions = new HashMap<String, ArrayList<String>>();
    ArrayList<Image> images = new ArrayList<Image>();
    boolean isCorrect;
    String correctOption;
    private H2 title = new H2("Find the correct answer");

    public NameImagePage()
    {
        this.setWidthFull();
        this.setAlignItems(Alignment.CENTER);
        loadCaptions();
        loadAllImages();
        buildLayout();
    }

    private void buildLayout()
    {
        Image randomImage = images.get(rnd.nextInt(images.size()));

        String imageFileName = randomImage.getSrc().substring(randomImage.getSrc().
                lastIndexOf('/')+1);

        String nameObject = correctOption = imageFileName.substring(0,imageFileName.lastIndexOf('.'));

        ArrayList<String> currentCaptions = captions.get(nameObject);

        ArrayList<Button> buttons = new ArrayList<>();

        for(String x : currentCaptions)
        {
            Button button = new Button(x);
            button.setWidth("15em");
            button.addClickListener(event->{isCorrect = correctOption.equals(event.getSource().getText());
                buildLayout();});
            buttons.add(button);
        }

        Collections.shuffle(buttons);

        HorizontalLayout top = new HorizontalLayout();
        top.setJustifyContentMode(JustifyContentMode.CENTER);
        top.setWidth("30em");

        top.add(buttons.get(0),buttons.get(1));

        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setJustifyContentMode(JustifyContentMode.CENTER);
        bottom.setWidth("30em");

        bottom.add(buttons.get(2),buttons.get(3));

        this.removeAll();

        this.add(title,randomImage,top,bottom);

    }

    private void loadCaptions()
    {
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\captions";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(int i = 0; i < listOfFiles.length; i++)
        {
            ArrayList<String> tempCaptions = new ArrayList<String>();
            try
            {
                tempCaptions = (ArrayList<String>) Files.readAllLines(Paths.get(path + "\\" + listOfFiles[i].
                        getName()));
                captions.put(listOfFiles[i].getName().substring(0,listOfFiles[i].getName().lastIndexOf('.')),
                        tempCaptions);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

        for(String k : captions.keySet())
        {
            System.out.print(k+ ":     ");
            for(String n : captions.get(k))
            {
                System.out.print(n+", ");
            }
            System.out.println();
        }
    }

    private void loadAllImages()
    {
        File folder = new File( System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images");
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < listOfFiles.length; i++)
        {
            images.add(loadImage(listOfFiles[i].getName(),"25em","25em"));
        }

    }

    private Image loadImage(String name, String width, String height)
    {
        try
        {
            File imgFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images\\"
                    +name);
            byte[] imageBytes = Files.readAllBytes(imgFile.toPath());
            StreamResource resource = new StreamResource(name, () -> new ByteArrayInputStream(imageBytes));

            Image image = new Image(resource, name);
            image.setWidth(width);
            image.setHeight(height);

            image.getStyle().set("border","1px solid #000000");

            return image;

        }
        catch (Exception e)
        {
            System.out.println("ERROR LOADING IMAGE: " + e);
        }
        return null;
    }

}


