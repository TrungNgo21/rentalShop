package Model.Product;

public class MRecords extends Product{
    public MRecords(String id, String title, String rentalType, String genre, int stock, String publishedYear, int numOfCopies, double rentalFee, String loanType, String status) {
        super(id, title, "RECORD", genre, stock, publishedYear, numOfCopies, rentalFee, loanType, status);
    }
}
