package pl.sikora.pretiusApp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;


public class PretiusApp {

    static int  allFilesMoved = 0, filesMovedToDEV = 0, filesMovedToTEST = 0;
    static String pathToResultTxt = "HOME\\count.txt";
    static String pathToHOME = "HOME";
    static String pathToDEV = "DEV";
    static String pathToTEST = "TEST";

    public static void main(String[] args) throws IOException {

        File resultTxt = new File(pathToResultTxt);
        checkDirectories();

        if (resultTxt.exists()) {
            int[] counts = ResultsGetter.getCounts(pathToResultTxt);
            filesMovedToDEV = counts[1];
            filesMovedToTEST = counts[2];
        }

        while (true) {
            List<Path> listOfPaths = FilesReader.getFilesPath("HOME");
            if (listOfPaths.size()>1){
                analizeAndMoveFiles(listOfPaths);
                saveCounts(pathToResultTxt);
            }
        }
    }


    public static void checkDirectories(){
        File home = new File(pathToHOME);
        File dev = new File(pathToDEV);
        File test = new File(pathToTEST);

        if(!home.exists()){
            home.mkdir();
        }

        if(!dev.exists()){
            dev.mkdir();
        }
        if(!test.exists()){
            test.mkdir();
        }
    }

    public static void analizeAndMoveFiles(List<Path> listOfPaths) {
        if (listOfPaths.size() != 0) {
            listOfPaths.stream().forEach(p -> {
                String extension = ExtensionGetter.getExtensionByString(p.toString()).get();
                int hour = HourGetter.getHours(p);
                moveFile(p, extension, hour);
            });
        }
    }

    public static void moveFile(Path path, String extension, int hour) {

        File file = new File(path.toString());

        if ("xml".equals(extension)) {
            file.renameTo(new File("DEV\\" + file.getName()));
            System.out.println("Files are moved");
            filesMovedToDEV++;
        } else {
            if ("jar".equals(extension)) {

                if (hour % 2 == 0) {
                    file.renameTo(new File("DEV\\" + file.getName()));
                    System.out.println("Files are moved");
                    filesMovedToDEV++;
                } else {
                    file.renameTo(new File("TEST\\" + file.getName()));
                    System.out.println("Files are moved");
                    filesMovedToTEST++;
                }
            }
        }
    }

    public static void saveCounts(String path) throws FileNotFoundException {
        File resultTxt = new File(path);
        if (resultTxt.exists()) {
            resultTxt.delete();
        }

        allFilesMoved = filesMovedToDEV+filesMovedToTEST;
        PrintWriter out = new PrintWriter(path);
        out.println("AllFilesMoved: "+allFilesMoved);
        out.println("FilesMovedToDev: "+filesMovedToDEV);
        out.println("FilesMovedToTest: "+filesMovedToTEST);
        out.close();
    }

}
