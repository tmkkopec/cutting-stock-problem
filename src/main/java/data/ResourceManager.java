package data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceManager {

    public List<Picture> getPicturesFromResources() throws IOException, URISyntaxException {
        return getPicturesFromFolder(ResourceManager.class.getClassLoader().getResource("images").getPath());
    }

    public List<Picture> getPicturesFromFolder(String folderPath) throws IOException {
        List<File> list = Arrays.asList(new File(folderPath).listFiles());

        return list.stream().map(Picture::new).collect(Collectors.toList());
    }
}
