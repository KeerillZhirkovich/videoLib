package model.dao.tools;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Класс, содержащий методы работы со строкой.
 */
public class WorkWithStrings {

    /**
     * Метод, разбивающий переданную строку на слова и возвращающий массив этих слов.
     */
    public static ArrayList<String> splitData(String data) {

        String dataChanged = ",,,";
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            boolean b = Character.isLetter(c) || Character.isDigit(c) || c == '*' || c == '?';
            if (b) {
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

    /**
     * Служебный метод, меняющий символы, не входящие в слова на ",".
     * Необходим для корректной работы метода splitData.
     */
    private static String replaceCharAt(String s, int pos) {

        return s.substring(0, pos) + ',' + s.substring(pos + 1);

    }
}
