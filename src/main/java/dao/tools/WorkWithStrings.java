package dao.tools;

public class WorkWithStrings {

    public static boolean ifContainsSplit(String data, String str) {

        str = str.replace(" ", ",");
        String[] arr = str.split(",|.");
        boolean b = false;

        for (String s : arr) {
            if(!s.isEmpty()){
                b = data.contains(s);
            }
        }

        return b;
    }
}
