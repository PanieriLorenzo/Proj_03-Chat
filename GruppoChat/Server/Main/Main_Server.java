package Main;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main_Server extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../View/Finestra.fxml"));
			Scene scene = new Scene(root,930,522);
			//scene.getStylesheets().add(getClass().getResource("application.css").toString());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(Main_Server.class.getResourceAsStream( "../Img/logo.png" )));
			primaryStage.setTitle("Easy Chat");
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			Platform.setImplicitExit(false);

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent event) {
			    	Alert closeConfirmation = new Alert(
			                Alert.AlertType.CONFIRMATION,
			                "Vuoi veramente uscire?"
			        );
			        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
			                ButtonType.OK
			        );
			        exitButton.setText("Esci");
			        closeConfirmation.setHeaderText("Conferma uscita");
			        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			        closeConfirmation.initOwner(primaryStage);
			        //closeConfirmation.setX(primaryStage.getX());
			        //closeConfirmation.setY(primaryStage.getY() + primaryStage.getHeight());

			        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			        if (!ButtonType.OK.equals(closeResponse.get())) {
			            event.consume();
			        }else {
			        	System.exit(0);
			        }
			    }
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
