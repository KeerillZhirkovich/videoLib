package dao.tools;

public class WorkWithStrings {

    public static String[] splitData (String data) {

        data = data.replace(" ", ",");
        String[] arr = data.split(",|.");
        return arr;
    }
}
