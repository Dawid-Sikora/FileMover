package pl.sikora.pretiusApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ResultsGetter {

    public static int[] getCounts(String path) throws IOException {
        int[] tall = new int[3];

        String str = readLines(path);
        String[] splited = str.split("\\s+");

        int idx =0;
        for (int i = 0; i < splited.length; i++) {
            try {
                int integer = Integer.parseInt(splited[i]);
                tall[idx++] = integer;
            }catch (Exception x){
            }
        }
        return tall;
    }


    public static String readLines(String path) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
