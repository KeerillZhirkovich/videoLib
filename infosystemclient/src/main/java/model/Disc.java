package model;

import java.io.Serializable;
import java.util.Comparator;


/**
 * Класс, являющийся абстракцией сущности Disc. Содержит поля, описывающие Disc,
 * методы доступа к ним, а так же служебные методы.
 */
public class Disc implements Serializable, Comparable<Disc> {

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
  private int clientID;
  private String imgUrl;

  /**
   * Конструктор по умолчанию.
   */
  public Disc() {
    this.russianTitle = "";
    this.originalTitle = "";
    this.director = "";
    this.genre = "";
    this.duration = 0;
    this.languages = "";
    this.country = "";
    this.description = "";
    this.rating = 0;
    this.actors = "";
    this.releaseYear = 0;
    this.comment = "";
    this.clientID = 0;
    this.imgUrl = "";
  }

  /**
   * @param russianTitle
   * @param originalTitle
   * @param director
   * @param genre
   * @param duration
   * @param languages
   * @param country
   * @param description
   * @param rating
   * @param actors
   * @param releaseYear
   * @param comment
   * @param clientID
   * @param imgUrl
   */
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

  /**
   * @return
   */
  public String getDirector() {
    return director;
  }

  /**
   * @param director
   */
  public void setDirector(String director) {
    this.director = director;
  }

  /**
   * @return
   */
  public String getGenre() {
    return genre;
  }

  /**
   * @param genre
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * @return
   */
  public int getDuration() {
    return duration;
  }

  /**
   * @param duration
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * @return
   */
  public String getLanguages() {
    return languages;
  }

  /**
   * @param languages
   */
  public void setLanguages(String languages) {
    this.languages = languages;
  }

  /**
   * @return
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return
   */
  public String getComment() {
    return comment;
  }

  /**
   * @param comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return
   */
  public double getRating() {
    return rating;
  }

  /**
   * @param rating
   */
  public void setRating(double rating) {
    this.rating = rating;
  }

  /**
   * @return
   */
  public String getActors() {
    return actors;
  }

  /**
   * @param actors
   */
  public void setActors(String actors) {
    this.actors = actors;
  }

  /**
   * @return
   */
  public int getDiskID() {
    return diskID;
  }

  /**
   * @param diskID
   */
  public void setDiskID(int diskID) {
    this.diskID = diskID;
  }

  /**
   * @return
   */
  public String getRussianTitle() {
    return russianTitle;
  }

  /**
   * @param russianTitle
   */
  public void setRussianTitle(String russianTitle) {
    this.russianTitle = russianTitle;
  }

  /**
   * @return
   */
  public String getOriginalTitle() {
    return originalTitle;
  }

  /**
   * @param originalTitle
   */
  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  /**
   * @return
   */
  public short getReleaseYear() {
    return releaseYear;
  }

  /**
   * @param releaseYear
   */
  public void setReleaseYear(short releaseYear) {
    this.releaseYear = releaseYear;
  }

  /**
   * @return
   */
  public int getClientID() {
    return clientID;
  }

  /**
   * @param clientID
   */
  public void setClientID(int clientID) {
    this.clientID = clientID;
  }

  /**
   * @return
   */
  public String getImgUrl() {
    return imgUrl;
  }

  /**
   * @param imgUrl
   */
  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  /**
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Disc disc = (Disc) o;

    if (duration != disc.duration) {
      return false;
    }
    if (Double.compare(disc.rating, rating) != 0) {
      return false;
    }
    if (releaseYear != disc.releaseYear) {
      return false;
    }
    if (russianTitle != null ? !russianTitle.equals(disc.russianTitle) : disc.russianTitle != null) {
      return false;
    }
    if (originalTitle != null ? !originalTitle.equals(disc.originalTitle) : disc.originalTitle != null) {
      return false;
    }
    if (director != null ? !director.equals(disc.director) : disc.director != null) {
      return false;
    }
    if (genre != null ? !genre.equals(disc.genre) : disc.genre != null) {
      return false;
    }
    if (languages != null ? !languages.equals(disc.languages) : disc.languages != null) {
      return false;
    }
    if (country != null ? !country.equals(disc.country) : disc.country != null) {
      return false;
    }
    if (actors != null ? !actors.equals(disc.actors) : disc.actors != null) {
      return false;
    }
    return comment != null ? comment.equals(disc.comment) : disc.comment == null;
  }

  /**
   * @return
   */
  @Override
  public int hashCode() {
    int result;
    long temp;
    result = russianTitle != null ? russianTitle.hashCode() : 0;
    result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
    result = 31 * result + (director != null ? director.hashCode() : 0);
    result = 31 * result + (genre != null ? genre.hashCode() : 0);
    result = 31 * result + duration;
    result = 31 * result + (languages != null ? languages.hashCode() : 0);
    result = 31 * result + (country != null ? country.hashCode() : 0);
    temp = Double.doubleToLongBits(rating);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (actors != null ? actors.hashCode() : 0);
    result = 31 * result + (int) releaseYear;
    result = 31 * result + (comment != null ? comment.hashCode() : 0);
    return result;
  }

  /**
   * @param o
   * @return
   */
  @Override
  public int compareTo(Disc o) {
    return originalTitle.compareTo(o.getOriginalTitle());
  }

  /**
   * @return
   */
  @Override
  public String toString() {
    return russianTitle + ' ' + originalTitle + ' '
        + director + ' ' + genre + ' ' + languages + ' '
        + country + ' ' + actors + ' ' + releaseYear;
  }
}
