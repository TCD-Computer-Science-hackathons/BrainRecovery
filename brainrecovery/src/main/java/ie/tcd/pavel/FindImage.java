package ie.tcd.pavel;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.VaadinService;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Route("img")
public class FindImage extends HorizontalLayout
{

    private ArrayList<Image> images = new ArrayList<>();
    private HorizontalLayout topRow = new HorizontalLayout();
    private HorizontalLayout bottomRow = new HorizontalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();

    private String currentImage;
    private static final int NUM_IMAGES = 4;

    public FindImage()
    {
        removeAll();
        H1 title = new H1("Match the image");
        Button button = new Button("Reload");
        button.addClickListener(event -> {
            reloadImages();
        });
        loadImages();
        rightLayout.add(title, topRow, bottomRow, button);
        rightLayout.setAlignItems(Alignment.CENTER);
        leftLayout.setAlignItems(Alignment.CENTER);
        add(leftLayout, rightLayout);

    }

    private void loadImages() {
        images.clear();
        try {
            Random random = new Random();
            for(int i = 0; i < NUM_IMAGES; i++) {
                int rand = random.nextInt(11);
                String pathname;
                File imgFile;
                byte[] imageBytes;
                StreamResource resource;
                Image image;
                if(rand < 10) {
                    pathname = System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images\\cat_0" + rand + ".jpg";
                    image = loadImage(pathname, "0" + rand);
                }
                else {
                    pathname = System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images\\cat_" + rand + ".jpg";
                    image = loadImage(pathname, "" + rand);
                }

                // If the user clicks an image that does not match it resets at the moment
                image.addClickListener(event -> {
                    if(image.getAlt().isPresent()) {
                        if(!image.getAlt().get().equals(currentImage)) {
                            reloadImages();
                        }
                    }
                });

                int rand2 = random.nextInt(NUM_IMAGES);
                if(rand2 == 0) {
                    Image imageCopy = loadImage(pathname, "0" + rand);
                    images.add(imageCopy);
                }
                images.add(image);
            }
            for(int image = 0; image < NUM_IMAGES; image++) {
                if(image < (NUM_IMAGES / 2)) {
                    topRow.add(images.get(image));
                } else {
                    bottomRow.add(images.get(image));
                }
            }

            int rand3 = random.nextInt(NUM_IMAGES);
            Image image = images.get(rand3);
            String alt = "cat_00";
            if(image.getAlt().isPresent()) {
                alt = image.getAlt().get();
            }
            currentImage = alt;
            String pathname = System.getProperty("user.dir")+"\\src\\main\\java\\ie\\tcd\\pavel\\images\\" + alt + ".jpg";
            leftLayout.add(loadImage(pathname, "0" + rand3));
            leftLayout.setAlignItems(Alignment.CENTER);
            leftLayout.setWidth("20%");
        }
        catch (Exception e)
        {
            System.out.println("ERROR LOADING IMAGE: "+e);
        }
    }

    private void reloadImages() {
        leftLayout.removeAll();
        topRow.removeAll();
        bottomRow.removeAll();
        loadImages();
    }

    private Image loadImage(String pathname, String id) {
        try {
            File imgFile = new File(pathname);
            byte[] imageBytes = Files.readAllBytes(imgFile.toPath());
            StreamResource resource = new StreamResource("cat_" + id + ".jpg", () -> new ByteArrayInputStream(imageBytes));
            Image image = new Image(resource, "cat_" + id);
            return image;
        } catch (Exception e) {
            System.out.println("ERROR LOADING IMAGE: " + e);
        }
        return null;
    }
}