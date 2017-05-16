package data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceManager {

    public List<Picture> getPicturesFromResources() throws IOException, URISyntaxException {
        return getPicturesFromFolder(getPathToImages());
    }

    public static String getPathToImages(){
        return ResourceManager.class.getClassLoader().getResource("images").getPath();
    }

    public static String getPathToProperties(){
        return ResourceManager.class.getClassLoader().getResource("application.properties").getPath();
    }

    public List<Picture> getPicturesFromFolder(String folderPath) throws IOException {
        List<File> list = new LinkedList<>(Arrays.asList(new File(folderPath).listFiles()));

        for(File f: list){
            if(f.getPath().contains("Thumbs.db")) {
                list.remove(f);
            }
        }

        return list.stream().map(Picture::new).collect(Collectors.toList());
    }
}
