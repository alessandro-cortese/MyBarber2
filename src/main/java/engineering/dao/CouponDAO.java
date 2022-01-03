package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.exception.InvalidCouponException;
import engineering.pattern.Connector;
import model.User;
import model.buyProduct.Coupon;
import model.buyProduct.containers.CouponContainer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CouponDAO {

    private final static String EXAMPLE_COUPON = "coupon_-20" ;

    public Coupon loadCouponByCode(String couponCode, User user) throws InvalidCouponException {
        /*Connection connection = Connector.getConnectorInstance().getConnection();
        try(Statement statement = connection.createStatement() ;
            ResultSet resultSet = Queries.loadCouponByCode(statement, couponCode, user.getEmail()) ;)
        {
            if (!resultSet.isFirst()) {
                throw new InvalidCouponException() ;
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }*/

        if (couponCode.compareTo(EXAMPLE_COUPON) == 0) {
            return new Coupon(couponCode, "") ;
        }
        else {
            throw new InvalidCouponException("Coupon non Trovato") ;
        }
    }

    public void invalidateCoupon(Coupon coupon) {
        Connection connection = Connector.getConnectorInstance().getConnection();
        try(Statement statement = connection.createStatement()) {

            Queries.deleteCoupon(statement, coupon.getCouponCode(), coupon.getOwnerUser()) ;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void invalidateAllCoupon(CouponContainer couponContainer) {
        for (int i = 0 ; i < couponContainer.getSize() ; i++) {
            if (couponContainer.getCouponByIndex(i) != null) {
                invalidateCoupon(couponContainer.getCouponByIndex(i));
            }
        }
    }
}
