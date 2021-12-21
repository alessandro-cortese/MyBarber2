package first_view.otherbarberscheduler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;

import java.io.IOException;


public class ScheduleTimeBarberController2 {

    /*
        Si prendono le righe che sono state incluse e a cui è stato dato id, vedere il codice FXML per
        il meccanismo dell'include; di base si include un file Fxml in un altro specificando il file sorgente da cui
        prendere ciò che deve essere incluso.
        La classe a cui le variabili seguenti sono dichiarate è Parent perché viene incluso tutto il layout contenuto
        nel file FXML specificato come source
     */
    @FXML private Parent mondayScheduleRow ;
    @FXML private Parent tuesdayScheduleRow ;
    @FXML private Parent wednesdayScheduleRow ;
    @FXML private Parent thursdayScheduleRow ;
    @FXML private Parent fridayScheduleRow ;
    @FXML private Parent saturdayScheduleRow ;

    /*
        è possibile prendere il controller del file incluso semplicemente assegnandogli come nome di variabile il nome
        NOME_PARENT_DICHIARATO_SOPRA+Controller. Questo permette di accedere al controller della singola riga di
        scheduling e prendere le informazioni delle CheckBox e delle TextFiled che sono contenuto in quella riga: questa
        cosa si può fare attraverso i metodi del controller getOpenStatus() e getMorningTime() e getAfternoonTime()
     */
    @FXML private SchedulerRowController mondayScheduleRowController ;
    @FXML private SchedulerRowController tuesdayScheduleRowController ;
    @FXML private SchedulerRowController wednesdayScheduleRowController ;
    @FXML private SchedulerRowController thursdayScheduleRowController ;
    @FXML private SchedulerRowController fridayScheduleRowController ;
    @FXML private SchedulerRowController saturdayScheduleRowController ;

    private SchedulerRowController[] schedulerRowControllerArray = null;

    private void initializeSchedulerRowArray() {
        //Serve per inizializzare l'array di Controllers delle righe per potervi ciclare
        schedulerRowControllerArray = new SchedulerRowController[]{mondayScheduleRowController,tuesdayScheduleRowController,
                wednesdayScheduleRowController, thursdayScheduleRowController, fridayScheduleRowController, saturdayScheduleRowController};
    }


    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        if (schedulerRowControllerArray == null) initializeSchedulerRowArray();

        for (SchedulerRowController schedulerRowControllerController : schedulerRowControllerArray) {

            System.err.println(schedulerRowControllerController.getOpenStatus().getKey());
            System.err.println(schedulerRowControllerController.getOpenStatus().getValue());
        }
    }




}
