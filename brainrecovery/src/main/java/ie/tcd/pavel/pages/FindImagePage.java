package ie.tcd.pavel.pages;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.io.File;
import java.util.*;


@Route("img")
public class FindImagePage extends VerticalLayout
{

    private ArrayList<Image> images = new ArrayList<Image>();
    private ArrayList<Integer> occupiedIndices = new ArrayList<Integer>();
    private final static Random rnd = new Random();
    private final static int NUMBER_OF_TILES = 4;
    private int correctOption;
    private String correctOptionName;
    private boolean isCorrect;
    private Button next = new Button("Next task");
    private H2 title = new H2("Find a matching image");

    public FindImagePage()
    {
        this.setAlignItems(Alignment.CENTER);
        this.setWidthFull();
        loadAllImages();
        next.addClickListener(event -> {buildLayout();});
        next.setWidth("15em");
        buildLayout();

    }

    private void buildLayout()
    {
        HorizontalLayout topRow = new HorizontalLayout();
        HorizontalLayout bottomRow = new HorizontalLayout();
        HorizontalLayout optionRow = new HorizontalLayout();

        topRow.setWidth("30em");
        bottomRow.setWidth("30em");
        optionRow.setWidth("30em");

        topRow.setJustifyContentMode(JustifyContentMode.CENTER);
        bottomRow.setJustifyContentMode(JustifyContentMode.CENTER);
        optionRow.setJustifyContentMode(JustifyContentMode.CENTER);

        occupiedIndices.clear();

        correctOption = rnd.nextInt(images.size());

        occupiedIndices.add(correctOption);

        for(int i = 0; i < NUMBER_OF_TILES - 1; i++)
        {
            occupiedIndices.add(getANewRandomIndex());
        }

        Collections.shuffle(occupiedIndices);

        int index = images.get(correctOption).getSrc().lastIndexOf('/');
        correctOptionName =  images.get(correctOption).getSrc().substring(index+1);

        topRow.add(images.get(occupiedIndices.get(0)),images.get(occupiedIndices.get(1)));
        bottomRow.add(images.get(occupiedIndices.get(2)),images.get(occupiedIndices.get(3)));
        Image option = loadImage(correctOptionName,"13em","13em");

        option.setEnabled(false);
        optionRow.add(option);

        this.removeAll();
        this.add(title,topRow,bottomRow,optionRow,next);

    }

    private int getANewRandomIndex()
    {
        boolean foundNew = false;
        int random = - 1;

        if(occupiedIndices.size()==images.size())
        {
            return random;
        }

        while(!foundNew) {
            random = rnd.nextInt(images.size());
            if(!occupiedIndices.contains(random))
            {
                foundNew = true;
            }
        }

        return random;

    }

    private void loadAllImages()
    {
        File folder = new File( System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images");
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < listOfFiles.length; i++)
        {
            images.add(loadImage(listOfFiles[i].getName(),"13em","13em"));
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

            image.addClickListener(event->{isCorrect = event.getSource().equals(images.get(correctOption));
               buildLayout();
            });

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