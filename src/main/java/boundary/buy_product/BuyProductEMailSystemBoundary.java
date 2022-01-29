package boundary.buy_product;

import engineering.bean.buy_product.VendorOrderBean;

import java.io.FileWriter;
import java.io.IOException;

public class BuyProductEMailSystemBoundary {

    private final static String VENDOR_NOTIFY_FILE_NAME = "vendorNotifyFile" ;

    public void notifyVendors(VendorOrderBean vendorOrderBean) {
        sendEmail(vendorOrderBean);
    }

    private void sendEmail(VendorOrderBean vendorOrderBean) {

        try(FileWriter fileWriter = new FileWriter(VENDOR_NOTIFY_FILE_NAME)) {
            fileWriter.write(String.format("""
                Carissimo %s.
                Siamo lieti di informarti che hai ricevuto un ordine da %s.
                Controlla la sezione degli ordini per vedere i dettagli.
                Cordiali saluti, il team MyBarber
                """, vendorOrderBean.getVendor(), vendorOrderBean.getOrderOwner()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
