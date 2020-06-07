package ie.tcd.pavel.pages;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.IOUtils;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.io.File;
import java.util.*;


public class FindImagePage extends VerticalLayout
{

    private String[] fileNames = new String[]{"ball","burger","car","cat","circle","computer","orange","plane",
            "policeman","square","train","triangle"};
    private ArrayList<Image> images = new ArrayList<Image>();
    private ArrayList<Integer> occupiedIndices = new ArrayList<Integer>();
    private final static Random rnd = new Random();
    private final static int NUMBER_OF_TILES = 4;
    private int correctOption;
    private String correctOptionName;
    private boolean isCorrect;
    private H2 title = new H2("Find a matching image");
    public ScoreWidget score;

    public FindImagePage(ScoreWidget score)
    {
        this.score = score;
        this.setAlignItems(Alignment.CENTER);
        this.setWidthFull();
        loadAllImages();
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
        correctOptionName = correctOptionName.substring(0,correctOptionName.lastIndexOf('.'));

        topRow.add(images.get(occupiedIndices.get(0)),images.get(occupiedIndices.get(1)));
        bottomRow.add(images.get(occupiedIndices.get(2)),images.get(occupiedIndices.get(3)));
        Image option = loadImage(correctOptionName,"13em","13em");

        option.setEnabled(false);
        optionRow.add(option);

        this.removeAll();
        this.add(title,topRow,bottomRow,optionRow,score);

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

        for(int i = 0; i < fileNames.length; i++)
        {
            images.add(loadImage(fileNames[i],"13em","13em"));
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

            image.addClickListener(event->{isCorrect = event.getSource().equals(images.get(correctOption));

                if(isCorrect)
                {
                    score.correct++;
                }
                else
                {
                    score.inCorrect++;
                }
                score.generateScore();
                buildLayout();
            });

            return image;

        } catch (Exception e) {
            System.out.println("ERROR LOADING IMAGE: " + e);
        }
        return null;
    }
}