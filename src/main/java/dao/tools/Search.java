package dao.tools;

import model.Disc;

import static dao.tools.WorkWithStrings.ifContainsSplit;

public class Search {

    public static boolean similarDisc(Disc disc1, Disc disc2) {

        boolean result = false, check = false;
        Disc discTemp = new Disc(disc1.getRussianTitle().trim(), disc1.getOriginalTitle().trim(),
                disc1.getDirector().trim(), disc1.getGenre().trim(), disc1.getDuration(), disc1.getLanguages().trim(),
                disc1.getCountry().trim(), disc1.getDescription().trim(), disc1.getRating(), disc1.getActors().trim(),
                disc1.getReleaseYear(), disc1.getComment().trim(), disc1.getClientID(), disc1.getImgUrl());

        if(!discTemp.getOriginalTitle().isEmpty()) {
            result = ifContainsSplit(discTemp.getOriginalTitle().toLowerCase(), disc2.getOriginalTitle().toLowerCase());
            check = true;
        }

        if(!discTemp.getRussianTitle().isEmpty()) {
            result = ifContainsSplit(discTemp.getRussianTitle().toLowerCase(), disc2.getRussianTitle().toLowerCase());
            check = true;
        }

        if(!discTemp.getDirector().isEmpty()) {
            result = ifContainsSplit(discTemp.getDirector().toLowerCase(), disc2.getDirector().toLowerCase());
            check = true;
        }

        if(!discTemp.getGenre().isEmpty()) {
            result = ifContainsSplit(discTemp.getGenre().toLowerCase(), disc2.getGenre().toLowerCase());
            check = true;
        }

        if(!discTemp.getActors().isEmpty()) {
            result = ifContainsSplit(discTemp.getActors().toLowerCase(), disc2.getActors().toLowerCase());
            check = true;
        }

        if(!discTemp.getLanguages().isEmpty()) {
            result = ifContainsSplit(discTemp.getLanguages().toLowerCase(), disc2.getLanguages().toLowerCase());
            check = true;
        }

        if(!discTemp.getCountry().isEmpty()) {
            result = ifContainsSplit(discTemp.getGenre().toLowerCase(), disc2.getGenre().toLowerCase());
            check = true;
        }

        if(discTemp.getReleaseYear() != 0) {
            result = discTemp.getReleaseYear() == disc2.getReleaseYear();
            check = true;
        }

        if(discTemp.getDuration() != 0) {
            result = discTemp.getDuration() >= disc2.getDuration();
            check = true;
        }

        if(discTemp.getRating() != 0) {
            result = discTemp.getRating() <= disc2.getRating();
            check = true;
        }

        if(!check){
            result = true;
        }
        return result;
    }
}
