package Model.Account;

public class AdminAccount extends Account{
    @Override
    public void addPoint(int addedPoints) {

    }

    @Override
    public boolean isFreeToBorrowOne() {
        return false;
    }

    @Override
    public boolean isAllowedToPromoted() {
        return false;
    }
}
