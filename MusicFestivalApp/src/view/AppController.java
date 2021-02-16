package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import main.Launcher;
import model.Festival;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable
{
	//variables for each stage
	public final static int SAD_STAGE = 1;
	public final static int BLUE_STAGE = 2;
	public final static int RED_STAGE = 3;
	public final static int HAPPY_STAGE = 4;
	public final static int BIG_STAGE = 5;
	public final static int TINY_STAGE = 6;
	public final static int FESTIVAL_VIEW = 7;
	
	//variables for AppController
	private static AppController appInstance; //Singleton
	private Festival festival;
	
	@FXML
	private BorderPane rootPane;

	//Constructor
	public AppController()
	{
		appInstance = null;
		this.festival = null;
	}
	
	/**This method returns an instance of AppController. If one already exists,
	 * the existing instance is returned.
	 * @return appInstance
	 */
	public static AppController getAppInstance()
	{
		if(appInstance == null)
		{
			appInstance = new AppController();
			appInstance.festival = null;
		}
		return appInstance;
	}
	
	public void setRootPane(BorderPane rootPane)
	{
		this.rootPane = rootPane;
	}
	
	public BorderPane getRootPane()
	{
		return rootPane;
	}
	
	public void setFestival(Festival festival)
	{
		AppController.getAppInstance().festival = festival;
	}
	
	public Festival getFestival()
	{
		return AppController.getAppInstance().festival;
	}
	
	/**This method handles the switching of views. This method also assigns Festivals and Festival stages
	 * to the views that need them for data manipulation.
	 * @param screenType
	 */
	public void switchView(int screenType)
	{
		try
		{
			FXMLLoader loader = null;
			
			switch(screenType)
			{
				case SAD_STAGE:
					loader = new FXMLLoader(Launcher.class.getResource("/view/stage.fxml"));
					StageController sadStageController = new StageController();
					loader.setController(sadStageController);
					sadStageController.setFestival(AppController.getAppInstance().getFestival());
			    	sadStageController.setStage(AppController.getAppInstance().getFestival().findStage("Sad Stage"));
					break;
					
				case BLUE_STAGE:
					loader = new FXMLLoader(Launcher.class.getResource("/view/stage.fxml"));
					StageController blueStageController = new StageController();
					loader.setController(blueStageController);
					blueStageController.setFestival(AppController.getAppInstance().getFestival());
			    	blueStageController.setStage(AppController.getAppInstance().getFestival().findStage("Blue Stage"));
					break;
					
				case RED_STAGE:
					loader = new FXMLLoader(Launcher.class.getResource("/view/stage.fxml"));
					StageController redStageController = new StageController();
					loader.setController(redStageController);
					redStageController.setFestival(AppController.getAppInstance().getFestival());
			    	redStageController.setStage(AppController.getAppInstance().getFestival().findStage("Red Stage"));
					break;
					
				case HAPPY_STAGE:
					loader = new FXMLLoader(Launcher.class.getResource("/view/stage.fxml"));
					StageController happyStageController = new StageController();
					loader.setController(happyStageController);
					happyStageController.setFestival(AppController.getAppInstance().getFestival());
			    	happyStageController.setStage(AppController.getAppInstance().getFestival().findStage("Happy Stage"));
					break;
					
				case BIG_STAGE:
					loader = new FXMLLoader(Launcher.class.getResource("/view/stage.fxml"));
					StageController bigStageController = new StageController();
					loader.setController(bigStageController);
					bigStageController.setFestival(AppController.getAppInstance().getFestival());
			    	bigStageController.setStage(AppController.getAppInstance().getFestival().findStage("Big Stage"));
					break;
					
				case TINY_STAGE:
					loader = new FXMLLoader(Launcher.class.getResource("/view/stage.fxml"));
					StageController tinyStageController = new StageController();
					loader.setController(tinyStageController);
					tinyStageController.setFestival(AppController.getAppInstance().getFestival());
			    	tinyStageController.setStage(AppController.getAppInstance().getFestival().findStage("Tiny Stage"));
					break;
					
				case FESTIVAL_VIEW:
					loader = new FXMLLoader(Launcher.class.getResource("/view/festival.fxml"));
					FestivalController festivalController = new FestivalController();
					loader.setController(festivalController);
					festivalController.setFestival(AppController.getAppInstance().getFestival());
					break;
			}
			
			Parent view = loader.load();
			rootPane.setCenter(view);
		
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	

}
