package boundary.buy_product;

import engineering.bean.buy_product.VendorOrderBean;

public class BuyProductEMailSystemBoundary {

    public void notifyVendors(VendorOrderBean vendorOrderBean) {
        sendEmail(vendorOrderBean);
    }

    private Boolean sendEmail(VendorOrderBean vendorOrderBean) {
        System.out.printf("Carissimo %s.\nSiamo lieti di informarti che hai ricevuto un ordine da %s.\nControlla la sezione degli ordini per vedere i dettagli\n", vendorOrderBean.getVendor(), vendorOrderBean.getOrderOwner());
        return true ;
    }
}
