package Middleware;

import Model.User.User;

public class CartMiddleWare {
    public boolean isBelongedToCurrentUser(String cartId, User currentUser){
        if (cartId.equals(currentUser.getCart().getCartId())){
            return true;
        }else {
            return false;
        }
    }
}
