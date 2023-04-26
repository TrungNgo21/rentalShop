package Model.Product;

public class Game extends Product{
    public Game(String id, String title, String rentalType, String genre, int stock,String publishedYear, int numOfCopies, double rentalFee, String loanType, String status) {
        super(id, title, "GAME", genre, stock, publishedYear, numOfCopies, rentalFee, loanType, status);
    }
}
