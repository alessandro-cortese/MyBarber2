package test;

import engineering.exception.InvalidCouponException;
import engineering.exception.NegativePriceException;
import engineering.pattern.decorator.Priceable;
import javafx.util.Pair;
import model.buy_product.Cart;
import model.buy_product.Product;
import model.buy_product.coupon.Coupon;
import model.buy_product.coupon.CouponApplier;
import model.buy_product.coupon.PercentageCoupon;
import model.buy_product.coupon.SubtractionCoupon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.buy_product.coupon.Coupon.PERCENTAGE_TYPE;
import static model.buy_product.coupon.Coupon.SUBTRACTION_TYPE;
import static org.junit.jupiter.api.Assertions.*;

public class BuyProductTest {
    //Responsabile Testing : Simone Nicosanti ; Matricola 0266910

    private static final List<Pair<Integer,Double>> PRODUCT_QUANTITY_PRICE = List.of(new Pair<>(2, 25.05), new Pair<>(4, 12.32), new Pair<>(1,24.33), new Pair<>(3,72.167)) ;

    private static final List<Pair<Double, Integer>> COUPON_MAP_DISCOUNT_TYPE = List.of(
            new Pair<>(45.0, PERCENTAGE_TYPE), new Pair<>(23.0, PERCENTAGE_TYPE), new Pair<>(23.0, SUBTRACTION_TYPE), new Pair<>(12.0, SUBTRACTION_TYPE))  ;

    private static final Double TEST_DELTA = 0.001 ;

    @Test
    public void cartTest() {
        Cart testCart = createTestCart() ;

        Double cartPrice = testCart.getPrice() ;

        Double testPrice = 0.0 ;
        for (Pair<Integer,Double> rowPair : PRODUCT_QUANTITY_PRICE) {
            testPrice = testPrice + rowPair.getKey() * rowPair.getValue() ;
        }

        assertEquals(testPrice, cartPrice, TEST_DELTA) ;
    }

    @Test
    public void couponApplierTest() throws NegativePriceException, InvalidCouponException {
        Priceable testPriceable = createTestCart() ;
        CouponApplier couponApplier = new CouponApplier(testPriceable) ;

        int i = 0 ;
        for (Pair<Double,Integer> couponPair : COUPON_MAP_DISCOUNT_TYPE) {
            Coupon coupon = createTestCoupon(i, couponPair) ;
            couponApplier.applyCoupon(coupon);
            i++ ;
        }

        Double applierPrice = couponApplier.getFinalPrice() ;

        Double testPrice = testPriceable.getPrice() ;
        for (Pair<Double,Integer> couponPair : COUPON_MAP_DISCOUNT_TYPE) {
            Integer type = couponPair.getValue() ;
            if (type.equals(SUBTRACTION_TYPE)) testPrice = testPrice - couponPair.getKey() ;
            else testPrice = testPrice * (1 - couponPair.getKey() / 100.0) ;
        }

        //We attend that values are not equals: by system rule, we apply first subtraction coupon then percentage; here are applied in reverse
        assertNotEquals(testPrice, applierPrice, TEST_DELTA);
    }

    @Test
    public void couponApplierExceptionTest() {
        Priceable testPriceable = createTestCart() ;
        CouponApplier couponApplier = new CouponApplier(testPriceable) ;

        Coupon coupon1 = new SubtractionCoupon(0, 10000.0) ;
        assertThrows(NegativePriceException.class, () -> couponApplier.applyCoupon(coupon1)) ;

        Coupon coupon2 = new SubtractionCoupon(1, 15.0) ;
        assertDoesNotThrow(() -> couponApplier.applyCoupon(coupon2));

        Coupon coupon3 = new SubtractionCoupon(1, 23.0) ;
        assertThrows(InvalidCouponException.class, () -> couponApplier.applyCoupon(coupon3)) ;
    }


    private Cart createTestCart() {
        Cart testCart = new Cart() ;

        int i = 0 ;
        for (Pair<Integer, Double> productRowCouple : PRODUCT_QUANTITY_PRICE) {
            Product product = createTestProduct(i, productRowCouple.getValue()) ;
            int quantity = productRowCouple.getKey() ;
            for (int j = 0 ; j < quantity ; j++) testCart.insertProduct(product);
            i++ ;
        }
        return testCart ;
    }


    private Product createTestProduct(Integer isbn, Double price) {
        Product product = new Product() ;
        product.setIsbn(isbn);
        product.setPrice(price);

        return product ;
    }


    private Coupon createTestCoupon(Integer couponCode, Pair<Double, Integer> couponPair) {
        Integer couponType = couponPair.getValue() ;
        Coupon coupon ;
        if (couponType.equals(SUBTRACTION_TYPE)) coupon = new SubtractionCoupon(couponCode, couponPair.getKey()) ;
        else coupon = new PercentageCoupon(couponCode, couponPair.getKey()) ;

        return coupon ;
    }


}
