package pl.sikora.pretiusApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilesReader {

    public  static List<Path> getFilesPath(String paht){

        List<Path> list = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get("HOME"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(p -> list.add(p));

        }catch (IOException e){
            System.out.println("Directory not exists");
        }

        return list;
    }
}
