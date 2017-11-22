package dao.tools;

import java.io.*;

public class FileChecker {

    public static boolean fileIsEmpty(String url) {

        File file = new File(url);
        boolean result = true;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
            result = br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
