package engineering.dao;

import engineering.dao.queries.Queries;
import engineering.exception.InvalidCouponException;
import engineering.pattern.Connector;
import model.User;
import model.buy_product.Coupon;
import model.buy_product.containers.CouponContainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO {

    private static final Integer EXAMPLE_COUPON = 0 ;

    private static final String COUPON_CODE_COL_ID = "id" ;
    private static final String COUPON_TYPE_COL_ID = "tipoCoupon" ;
    private static final String COUPON_DISCOUNT_COL_ID = "sconto" ;
    private static final String COUPON_DISCOUNT_TYPE = "sconto";

    public Coupon loadCouponByCode(Integer couponCode) throws InvalidCouponException {
        Connection connection = Connector.getConnectorInstance().getConnection();
        Coupon coupon = null ;
        try(Statement statement = connection.createStatement() ;
            ResultSet resultSet = Queries.selectCouponByCode(statement, couponCode) ;)
        {

            if (resultSet.next()) {
                coupon = createCoupon(resultSet) ;
            }
            else {
                throw  new InvalidCouponException("ERRORE: COUPON NON VALIDO") ;
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return coupon ;
    }

    public List<Coupon> loadCouponByUser(String userEmail) {
        Connection connection = Connector.getConnectorInstance().getConnection();
        List<Coupon> coupons = new ArrayList<>() ;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = Queries.selectCouponsByOwner(statement, userEmail) ;)
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
        Double couponDiscount = resultSet.getDouble(COUPON_DISCOUNT_COL_ID) ;

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

    public Coupon addNewCoupon(Double couponValue, String email) {
        Connection connection = Connector.getConnectorInstance().getConnection();

        Coupon newCoupon = null ;
        try(PreparedStatement statement = connection.prepareStatement("INSERT Coupon(tipoCoupon, sconto, donazione, customer) VALUES(?,?,?,?) ;", Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, COUPON_DISCOUNT_TYPE);
            statement.setDouble(2, couponValue);
            statement.setDouble(3, 0.0);
            statement.setString(4, email);

            statement.executeUpdate() ;
            ResultSet generatedKeys = statement.getGeneratedKeys() ;

            while (generatedKeys.next()) {
                Integer newCouponCode = generatedKeys.getInt(1) ;
                newCoupon = new Coupon(newCouponCode, couponValue) ;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return newCoupon ;
    }
}
