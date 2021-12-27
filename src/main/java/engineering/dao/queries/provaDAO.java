package engineering.dao.queries;

import engineering.dao.SaloonDAO;
import model.Saloon;

import java.sql.SQLException;
import java.util.List;

public class provaDAO { //Classe "Conteiner per provare se le DAO funzionano"
    public static void main(String args[]){
        Saloon saloonIstance = new Saloon("fossa","Velletri","fontana,32","0660",15,2);
        try {
            System.out.println("looking for saloon named fossa" );
            new SaloonDAO();
            List<Saloon> list = SaloonDAO.retreiveBySaloonName("fossa");

            int i=0;
            for (Saloon saloon : list){
                i++;
                System.out.println("Result"+ i + ": "+saloon.toString());
            }
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        }
    }

}
