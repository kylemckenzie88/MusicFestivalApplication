package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.Festival;

public class FestivalController implements Initializable
{
	//variables
	private Festival festival;
	private String festivalName;
	
    @FXML
    private Text festivalNameHeader;
    
    //private static FestivalController festivalInstance;
    
    //Constructor
	public FestivalController()
	{
		this.festival = null;
		this.festivalName = "";
	}
    
    public String getFestivalName() 
    {
		return festivalName;
	}

	public void setFestivalName(String festivalName) 
	{
		this.festivalName = festivalName;
	}
    
    public void setFestival(Festival festival)
    {
    	this.festival = festival;
    }
    
    public Festival getFestival()
    {
    	return this.festival;
    }
    
    //The following methods facilitate transition to each stage
    @FXML
    void changeToSadStage(ActionEvent event) 
    {
    	AppController.getAppInstance().switchView(AppController.SAD_STAGE);
    }

    @FXML
    void changeToBlueStage(ActionEvent event) 
    {
    	AppController.getAppInstance().switchView(AppController.BLUE_STAGE);
    }

    @FXML
    void changeToRedStage(ActionEvent event) 
    {
    	AppController.getAppInstance().switchView(AppController.RED_STAGE);
    }

    @FXML
    void changeToHappyStage(ActionEvent event) 
    {
    	AppController.getAppInstance().switchView(AppController.HAPPY_STAGE);
    }

    @FXML
    void changeToBigStage(ActionEvent event) 
    {
    	AppController.getAppInstance().switchView(AppController.BIG_STAGE);
    }

    @FXML
    void changeToTinyStage(ActionEvent event) 
    {
    	AppController.getAppInstance().switchView(AppController.TINY_STAGE);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		festivalNameHeader.setText(AppController.getAppInstance().getFestival().getFestivalName());
	}
	
	
}
