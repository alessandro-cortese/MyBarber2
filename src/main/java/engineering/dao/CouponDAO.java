package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.exception.InvalidCouponException;
import engineering.pattern.Connector;
import model.User;
import model.buy_product.Coupon;
import model.buy_product.containers.CouponContainer;

import java.sql.*;
import java.util.ArrayList;

public class CouponDAO {

    private final static Integer EXAMPLE_COUPON = 0 ;

    private final static String COUPON_CODE_COL_ID = "id" ;
    private final static String COUPON_TYPE_COL_ID = "tipoCoupon" ;
    private final static String COUPON_DISCOUNT_ID = "sconto" ;


    public Coupon loadCouponByCode(Integer couponCode, User user) throws InvalidCouponException {
        /*Connection connection = Connector.getConnectorInstance().getConnection();
        try(Statement statement = connection.createStatement() ;
            ResultSet resultSet = Queries.loadCouponByCode(statement, couponCode, user.getEmail()) ;)
        {
            resultSet.next() ;
            if (!resultSet.isFirst()) {
                throw new InvalidCouponException() ;
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }*/

        if (couponCode == EXAMPLE_COUPON) {
            return new Coupon(0, 20.0) ;
        }
        else {
            throw new InvalidCouponException("Coupon non Trovato") ;
        }
    }

    public ArrayList<Coupon> loadCouponByUser(String userEmail) {
        Connection connection = Connector.getConnectorInstance().getConnection();
        ArrayList<Coupon> coupons = new ArrayList<>() ;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = Queries.selectCouponsByOwner(statement, userEmail) ;
                )
        {
            while (resultSet.next()) {
                Coupon coupon = createCoupon(resultSet) ;
                coupons.add(coupon) ;
            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return  coupons ;
    }

    private Coupon createCoupon(ResultSet resultSet) throws SQLException {
        Integer couponCode = resultSet.getInt(COUPON_CODE_COL_ID) ;
        Double couponDiscount = resultSet.getDouble(COUPON_DISCOUNT_ID) ;

        return new Coupon(couponCode, couponDiscount) ;
    }

    public void invalidateCoupon(Coupon coupon) {
        Connection connection = Connector.getConnectorInstance().getConnection();
        try(Statement statement = connection.createStatement()) {

            Queries.deleteCoupon(statement, coupon.getCouponCode()) ;
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

    public Integer addNewCoupon(Double couponValue, String email) {
        Connection connection = Connector.getConnectorInstance().getConnection();

        Integer newCouponCode = -1 ;
        try(PreparedStatement statement = connection.prepareStatement("INSERT Coupon(id,tipoCoupon, sconto, donazione, customer) VALUES(0,?,?,?,?) ;", Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, "sconto");
            statement.setDouble(2, couponValue);
            statement.setDouble(3, 0.0);
            statement.setString(4, email);

            statement.executeUpdate() ;
            ResultSet generatedKeys = statement.getGeneratedKeys() ;

            while (generatedKeys.next()) {
                newCouponCode = generatedKeys.getInt(0) ;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return newCouponCode ;
    }
}
