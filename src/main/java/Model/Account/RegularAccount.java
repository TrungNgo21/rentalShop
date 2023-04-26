package Model.Account;

public class RegularAccount extends Account{
    public RegularAccount(String accountId, String accountType, int points, int numReturnedItems, boolean isAllowed2DaysItems, int rentalThreshold, boolean isCurrentlyBorrowed) {
        super(accountId, accountType, points, numReturnedItems, isAllowed2DaysItems, rentalThreshold, isCurrentlyBorrowed);
    }

    @Override
    void addPoint(int addedPoints) {
        throw new Error("You are not qualified to add point");
    }

    @Override
    boolean isFreeToBorrowOne() {
        throw new Error("You are not qualified to borrow anything free");
    }

    @Override
    boolean isAllowedToPromoted() {
        return getNumReturnedItems() > 5;
    }
}
