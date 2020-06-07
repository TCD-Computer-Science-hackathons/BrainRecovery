package ie.tcd.pavel.pages;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.ButtonOptions;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.IOUtils;


import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class NameImagePage extends VerticalLayout
{

    private String[] fileNames = new String[]{"ball","burger","car","cat","circle","computer","orange","plane",
            "policeman","square","train","triangle"};
    private final static Random rnd = new Random();
    HashMap<String, ArrayList<String>> captions = new HashMap<String, ArrayList<String>>();
    ArrayList<Image> images = new ArrayList<Image>();
    boolean isCorrect;
    String correctOption;
    private H2 title = new H2("Find the correct answer");
    public ScoreWidget score;

    public NameImagePage(ScoreWidget score)
    {
        this.score = score;
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
                if(isCorrect)
                {
                    score.correct++;
                }
                else
                {
                    score.inCorrect++;
                }
                score.generateScore();
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

        this.add(title,randomImage,top,bottom,score);

    }

    private void loadCaptions()
    {
        String path = "/captions/";


        for(int i = 0; i <fileNames.length;i++) {
            ArrayList<String> lines = new ArrayList<>();
            String line ="";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(NameImagePage.class.
                        getResourceAsStream(path+fileNames[i]+".txt")));
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to load shader from file path: " + path, e);
            }
            captions.put(fileNames[i],lines);
        }
    }

    private void loadAllImages()
    {

        for(int i = 0; i < fileNames.length; i++)
        {
            images.add(loadImage(fileNames[i],"25em","25em"));
        }

    }

    private Image loadImage(String name, String width, String height)
    {
        String path = "/images/";
        try {
            InputStream inputStream = NameImagePage.class.getResourceAsStream(path + name + ".jpg");
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            StreamResource resource = new StreamResource(name+ ".jpg", () -> new ByteArrayInputStream(imageBytes));
            Image image = new Image(resource, name+ ".jpg");
            image.setWidth(width);
            image.setHeight(height);

            image.getStyle().set("border", "1px solid #000000");

            return image;

        } catch (Exception e) {
            System.out.println("ERROR LOADING IMAGE: " + e);
        }
        return null;
    }

}


