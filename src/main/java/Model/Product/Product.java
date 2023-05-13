package Model.Product;

import java.util.Arrays;

public abstract class Product {
    private final String[] loanTypes = {"2-DAY, 1-WEEK"};
    private final String[] rentalTypes = {"RECORD", "DVD", "GAME"};
    private final String[] rentalStatus = {"BORROWED", "AVAILABLE"};
    private final String[] genres = {"ACTION", "HORROR", "DRAMA", "COMEDY"};
    private String id;
    private String title;
    private String rentalType;
    private String genre;
    private String publishedYear;
    private int numOfCopies;
    private double rentalFee;
    private String loanType;
    private String status;

    private int stock;
    private String imageLocation;

    public Product(String id, String title, String rentalType, String genre, String publishedYear, int numOfCopies, double rentalFee, String loanType, String status, String imageLocation) {
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.numOfCopies = numOfCopies;
        this.rentalFee = rentalFee;
        this.loanType = loanType;
        this.status = status;
        this.imageLocation = imageLocation;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRentalType() {
        return rentalType;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getStatus() {
        return status;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public int getStock() {
        return stock;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {this.title = title;}

    public void setRentalFee(double rentalFee) {this.rentalFee = rentalFee;}

    public void setImageLocation(String imageLocation) {this.imageLocation = imageLocation;}

    @Override
    public String toString() {
        return "Product{" +
                "loanTypes=" + Arrays.toString(loanTypes) +
                ", rentalTypes=" + Arrays.toString(rentalTypes) +
                ", rentalStatus=" + Arrays.toString(rentalStatus) +
                ", genres=" + Arrays.toString(genres) +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rentalType='" + rentalType + '\'' +
                ", genre='" + genre + '\'' +
                ", publishedYear='" + publishedYear + '\'' +
                ", numOfCopies=" + numOfCopies +
                ", rentalFee=" + rentalFee +
                ", loanType='" + loanType + '\'' +
                ", status='" + status + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }
}
