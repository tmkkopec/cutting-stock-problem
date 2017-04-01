package data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceManager {

    public List<Image> getImagesFromResources() throws IOException, URISyntaxException {
        return getImagesFromFolder(ResourceManager.class.getClassLoader().getResource("images").getPath());
    }

    public List<Image> getImagesFromFolder(String folderPath) throws IOException {
        List<File> list = Arrays.asList(new File(folderPath).listFiles());

        return list.stream().map(Image::new).collect(Collectors.toList());
    }
}
