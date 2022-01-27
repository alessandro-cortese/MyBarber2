package engineering.dao;

import model.buy_product.Cart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CartFileSaver {

    private final String cartFileName ;

    public CartFileSaver(String userName) {
        cartFileName = userName + "_CART" ;
    }

    public Cart loadCartFromFile() {
        Cart cart = new Cart() ;
        try(
                FileInputStream fileInputStream = new FileInputStream(cartFileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ) {
            cart = (Cart) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            //Se il file che contiene il carrello non viene trovato, si ritorna direttamente una nuova istanza di carrello
        }
        return cart ;
    }

    public void saveCartInFile(Cart cart) {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(cartFileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(cart);
        } catch (IOException ignored) {}
    }

    public void deleteCartFromFile() {
        try {
            Files.delete(Path.of(cartFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
