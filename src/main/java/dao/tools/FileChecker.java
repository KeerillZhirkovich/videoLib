package dao.tools;

import java.io.*;

public class FileChecker {

    public static boolean fileIsEmpty(String url) throws IOException {
        File file = new File(url);
        boolean result = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(
        new FileInputStream(file), "UTF-8"));
        result = br.readLine() == null;
        return result;
    }
}
