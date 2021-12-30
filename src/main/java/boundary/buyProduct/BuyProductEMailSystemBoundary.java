package boundary.buyProduct;

import engineering.bean.buyProduct.VendorOrderBean;

public class BuyProductEMailSystemBoundary {

    public void notifyVendors(VendorOrderBean vendorOrderBean) {
        sendEmail(vendorOrderBean);
    }

    private Boolean sendEmail(VendorOrderBean vendorOrderBean) {
        System.out.println(vendorOrderBean.getVendor()) ;
        return true ;
    }
}
