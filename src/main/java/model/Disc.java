package model;

import java.io.Serializable;

public class Disc implements Serializable {

    private int diskID;
    private String russianTitle;
    private String originalTitle;
    private String director;
    private String genre;
    private int duration;
    private String languages;
    private String country;
    private String description;
    private double rating;
    private String actors;
    private short releaseYear;
    private String comment;

    public Disc ()
    {
        
    }
    
    public Disc(String russianTitle, String originalTitle, String director, String genre, int duration,
                String languages, String country, String description, double rating, String actors,
                short releaseYear, String comment, int clientID, String imgUrl) {
        this.russianTitle = russianTitle;
        this.originalTitle = originalTitle;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        this.languages = languages;
        this.country = country;
        this.description = description;
        this.rating = rating;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.comment = comment;
        this.clientID = clientID;
        this.imgUrl = imgUrl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private int clientID;
    private String imgUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getDiskID() {
        return diskID;
    }

    public void setDiskID(int diskID) {
        this.diskID = diskID;
    }

    public String getRussianTitle() {
        return russianTitle;
    }

    public void setRussianTitle(String russianTitle) {
        this.russianTitle = russianTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disc disc = (Disc) o;

        if (diskID != disc.diskID) return false;
        if (duration != disc.duration) return false;
        if (Double.compare(disc.rating, rating) != 0) return false;
        if (releaseYear != disc.releaseYear) return false;
        if (clientID != disc.clientID) return false;
        if (russianTitle != null ? !russianTitle.equals(disc.russianTitle) : disc.russianTitle != null) return false;
        if (originalTitle != null ? !originalTitle.equals(disc.originalTitle) : disc.originalTitle != null)
            return false;
        if (director != null ? !director.equals(disc.director) : disc.director != null) return false;
        if (genre != null ? !genre.equals(disc.genre) : disc.genre != null) return false;
        if (languages != null ? !languages.equals(disc.languages) : disc.languages != null) return false;
        if (country != null ? !country.equals(disc.country) : disc.country != null) return false;
        if (description != null ? !description.equals(disc.description) : disc.description != null) return false;
        if (actors != null ? !actors.equals(disc.actors) : disc.actors != null) return false;
        if (comment != null ? !comment.equals(disc.comment) : disc.comment != null) return false;
        return imgUrl != null ? imgUrl.equals(disc.imgUrl) : disc.imgUrl == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = diskID;
        result = 31 * result + (russianTitle != null ? russianTitle.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (int) releaseYear;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + clientID;
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        return result;
    }
}
