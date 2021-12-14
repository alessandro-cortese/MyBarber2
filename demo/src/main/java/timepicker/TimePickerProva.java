package timepicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TimePickerProva extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("TimePicker/prova_time_picker.fxml")) ;
        Scene scene = new Scene(root.load());

        stage.setResizable(false);

        stage.setTitle("TimePickerProva");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}