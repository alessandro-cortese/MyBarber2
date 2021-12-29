package engineering.dao.queries;

import engineering.dao.SaloonDAO;
import model.Saloon;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class provaDAO { //Classe "Conteiner per provare se le DAO funzionano"
    public static void main(String args[]){
      //  Saloon saloonIstance = new Saloon("fossa","Velletri","fontana,32","0660", Time.valueOf(LocalTime.NOON),2);
        try {
            System.out.println("looking for saloon named TagliaX" );
            new SaloonDAO();
            List<Saloon> list = SaloonDAO.retreiveBySaloonName("TagliaX");

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
