package model.dao.tools;

import model.Client;
import model.Disc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.dao.tools.WorkWithStrings.splitData;


/**
 * Служебный класс, содержащий методы поиска данных в коллекции.
 */
public class Search {

    /**
     * Метод, преобразующий в массив переданное поле с данными.
     */
    public static ArrayList<String> fieldToArray(String field) {

        ArrayList<String> array = new ArrayList<>();

        return splitData(field);
    }

    /**
     * Метод, возвращающий коллекцию разбитых на слова полей Disc.
     */
    public static ArrayList<ArrayList<String>> dataToArray(Disc disc) {

        ArrayList<ArrayList<String>> array = new ArrayList<>();

        array.add(splitData(disc.getRussianTitle()));
        array.add(splitData(disc.getOriginalTitle()));
        array.add(splitData(disc.getDirector()));
        array.add(splitData(disc.getActors()));
        array.add(splitData(disc.getGenre()));
        array.add(splitData(Integer.toString(disc.getReleaseYear())));
        array.add(splitData(disc.getLanguages()));
        array.add(splitData(disc.getCountry()));
        array.add(splitData(disc.getDescription()));

        return array;
    }

    /**
     * Метод, возвращающий коллекцию разбитых на слова полей Client.
     */
    public static ArrayList<ArrayList<String>> dataToArray(Client client) {

        ArrayList<ArrayList<String>> array = new ArrayList<>();

        array.add(splitData(client.getName()));
        array.add(splitData(client.getSurname()));
        array.add(splitData(client.getPhone()));

        return array;
    }


    /**
     * Метод, возвращающий релевантность данных запросу.
     */
    public static int relevance(ArrayList<ArrayList<String>> arrays, ArrayList<String> keywords) {

        int result = 0;
        boolean b;
        Pattern pattern;
        Matcher matcher;

        for (String keyword : keywords) {
            b = false;
            keyword = keyword.replaceAll("\\?", ".");
            pattern = Pattern.compile(keyword);
            for (ArrayList<String> array : arrays) {
                for (String word : array) {
                    matcher = pattern.matcher(word);
                    if (matcher.find()) {
                        result++;
                        b = true;
                        break;
                    }
                }
                if (b) break;
            }
        }

        return result;
    }
}
