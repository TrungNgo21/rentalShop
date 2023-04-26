package Model.Product;

public abstract class Product {
    private final String[] loanTypes = {"2-DAY, 1-WEEK"};
    private final String[] rentalTypes = {"RECORD", "DVD", "GAME"};
    private final String[] rentalStatus = {"BORROWED", "AVAILABLE"};
    private final String[] genres = {"ACTION", "HORROR", "DRAMA", "COMEDY"};
    private String id;
    private String title;
    private String rentalType;
    private String genre;
    private int stock;
    private String publishedYear;
    private int numOfCopies;
    private double rentalFee;
    private String loanType;
    private String status;

    public Product(String id, String title, String rentalType, String genre, int stock, String publishedYear, int numOfCopies, double rentalFee, String loanType, String status) {
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.genre = genre;
        this.stock = stock;
        this.publishedYear = publishedYear;
        this.numOfCopies = numOfCopies;
        this.rentalFee = rentalFee;
        this.loanType = loanType;
        this.status = status;
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

    public int getStock() {
        return stock;
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
}
