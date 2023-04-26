package Service;

import DataAccess.DataAccess;
import Middleware.UserMiddleware;

public class AdminServices extends UserServices{
    private final DataAccess db = new DataAccess();
    private final UserMiddleware checker = new UserMiddleware();

}
