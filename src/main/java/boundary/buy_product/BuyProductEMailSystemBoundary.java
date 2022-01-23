package boundary.buy_product;

import engineering.bean.buy_product.VendorOrderBean;

public class BuyProductEMailSystemBoundary {

    public void notifyVendors(VendorOrderBean vendorOrderBean) {
        sendEmail(vendorOrderBean);
    }

    private Boolean sendEmail(VendorOrderBean vendorOrderBean) {
        System.out.printf(String.format("""
                Carissimo %s.
                Siamo lieti di informarti che hai ricevuto un ordine da %s.
                Controlla la sezione degli ordini per vedere i dettagli.
                Cordiali saluti, il team MyBarber
                """, vendorOrderBean.getVendor(), vendorOrderBean.getOrderOwner()));
        return true ;
    }
}
