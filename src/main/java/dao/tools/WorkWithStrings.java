package dao.tools;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class WorkWithStrings {

    public static ArrayList<String> splitData (String data) {

        String dataChanged = ",,,";
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            boolean b = (!Character.isLetter(c) && !Character.isDigit(c)) || c == ' ';
            if (Character.isLetter(c) || Character.isDigit(c)) {
                dataChanged = dataChanged.concat(valueOf(c));
            } else {
                dataChanged = dataChanged.concat(",");
            }
        }

        dataChanged = dataChanged.toLowerCase();
        String[] tempResult = dataChanged.split(",");

        for (String word : tempResult) {
            if (!word.isEmpty()) {
                result.add(word);
            }
        }

        return result;
    }

    static String replaceCharAt(String s, int pos) {

        return s.substring(0,pos) + ',' + s.substring(pos+1);

    }
}
