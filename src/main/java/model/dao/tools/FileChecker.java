package model.dao.tools;

import java.io.*;

public class FileChecker {

    public static boolean fileIsEmpty(String url) {
        File file = new File(url);
        boolean result = true;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            result = br.readLine() == null;
            return result;
        } catch (Exception e) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }

        return result;
    }
}
