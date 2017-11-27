package dao.tools;

import java.util.ArrayList;

public class Search {

    public static int relevance(ArrayList<String> words, String data) {

        int result = 0;

        for (String word : words) {
            if (data.contains(word)) {
                result++;
            }
        }

        return result;
    }
}
