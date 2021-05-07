package pl.sikora.pretiusApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class HourGetter {

    public static int getHours(Path path) {

        int hourToReturn = 0;

        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime fileTime = attr.creationTime();
            DateFormat df = new SimpleDateFormat("HH");
            String hour = df.format(fileTime.toMillis());
            hourToReturn = Integer.valueOf(hour);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hourToReturn;
    }


}
