package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Festival;
import view.AppController;
import view.FestivalController;

public class Launcher extends Application 
{

	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		//Load Festival Screen
		FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/appController.fxml"));
		Festival festival = new Festival();
		festival.loadFestival("festival.csv", "stage_bands.csv");
		loader.setController(AppController.getAppInstance());
		BorderPane borderPane = (BorderPane) loader.load();
		AppController.getAppInstance().setRootPane(borderPane);
		AppController.getAppInstance().setFestival(festival);
		
		loader = new FXMLLoader(Launcher.class.getResource("/view/festival.fxml"));
		FestivalController festivalController = new FestivalController();
		loader.setController(festivalController);
		Parent view;
		
		try 
		{
			view = loader.load();
			AppController.getAppInstance().getRootPane().setCenter(view);
			festivalController.setFestivalName(festival.getFestivalName());
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//Set title and scene
		Scene scene = new Scene(borderPane);
		stage.setTitle("Festival Stage Lineups");
		stage.setScene(scene);
		stage.show();
	}

}
