package engineering.bean.buyProduct;

import java.util.ArrayList;

public class ProductCatalogBean {

    private ArrayList<ProductBean> catalog ;

    public ProductCatalogBean(ArrayList<ProductBean> catalog) {
        setCatalog(catalog);
    }


    public ArrayList<ProductBean> getCatalog() {
        return catalog;
    }

    public void setCatalog(ArrayList<ProductBean> catalog) {
        this.catalog = catalog;
    }

    public ProductBean getProductByIndex(Integer index) {
        if (index < catalog.size()) {
            return catalog.get(index) ;
        }
        return null ;
    }
}
