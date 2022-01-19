package engineering.dao;

import model.buy_product.Cart;

import java.io.*;

public class CartFileSaver {

    private final String cartFileName ;

    public CartFileSaver(String userName) {
        cartFileName = userName + "_CART" ;
    }

    public Cart loadCartFromFile() {
        Cart cart = new Cart() ;
        try(
                FileInputStream fileInputStream = new FileInputStream(cartFileName) ;
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream) ;
                ) {
            cart = (Cart) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
        return cart ;
    }

    public void saveCartInFile(Cart cart) {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(new File(cartFileName)) ;
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream) ;
        ) {
            //System.out.println("SALVATO");
            objectOutputStream.writeObject(cart);
        } catch (IOException ignored) {
        }
    }

    public void deleteCart() {
        File cartFile = new File(cartFileName) ;
        cartFile.delete() ;
    }
}
