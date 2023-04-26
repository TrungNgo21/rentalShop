package Model.Product;

public class DVD extends Product{
    public DVD(String id, String title, String rentalType, String genre, int stock, String publishedYear, int numOfCopies, double rentalFee, String loanType, String status) {
        super(id, title, "DVD", genre, stock, publishedYear, numOfCopies, rentalFee, loanType, status);
    }
}
