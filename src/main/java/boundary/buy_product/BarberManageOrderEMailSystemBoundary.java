package boundary.buy_product;

import engineering.bean.buy_product.CartRowBean;
import engineering.bean.buy_product.VendorOrderBean;

import java.util.List;

public class BarberManageOrderEMailSystemBoundary {

    public void sendEmail(VendorOrderBean vendorOrderBean, List<CartRowBean> cartRowBeanList) {
        System.out.println(String.format("""
                Caro %s!!
                La informiamo che l'ordine numero %d fatto presso il rivenditore %s è stato confermato!
                Di seguito i prodotti dell'ordine confermati!
                Cordiali saluti, il team MyBarber!
                """, vendorOrderBean.getOrderOwner(), vendorOrderBean.getOrderCode(), vendorOrderBean.getVendor()));
        for (CartRowBean cartRowBean : cartRowBeanList) {
            System.out.println(String.format("Prodotto: %s\nQuantità: %s\n", cartRowBean.getName(), cartRowBean.getQuantity()));
        }
    }
}
