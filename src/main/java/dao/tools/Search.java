package dao.tools;

public class Search {

    public static int relevance(String[] words, String data) {

        int result = 0;

        for (String word : words) {
            if (data.contains(word)) {
                result++;
            }
        }

        return result;
    }
}
