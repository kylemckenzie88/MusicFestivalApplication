package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import model.Band;
import model.Festival;
import model.FestivalStage;

public class StageController implements Initializable
{
	//Variables
    ObservableList<Band> stageItems = FXCollections.observableArrayList();
    ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
    ArrayList<Band> bandData = new ArrayList<Band>();
    ArrayList<String> menuData = new ArrayList<String>();
	private Festival festival;
	private FestivalStage festivalStage;
	
	//Constructor
	public StageController()
	{
		this.festival = null;
		this.festivalStage = null;
	}
	
	public void setStage(FestivalStage stage)
	{
		this.festivalStage = stage;
	}
	
	public FestivalStage getStage()
	{
		return this.festivalStage;
	}
	
	public void setFestival(Festival festival)
	{
		this.festival = festival;
	}
	
	public Festival getFestival()
	{
		return this.festival;
	}
	
	@FXML
    private Text stageName;

    @FXML
    private ListView<Band> listView;

    @FXML
    private Text bandsInLineup;

    @FXML
    private ComboBox<String> relocationOptions;
    
    
    /**This method moves an item up one index in the list view and refreshes it. If
     * the item is at the top, or the list view is empty,
     *  an alert is displayed and the method returns.
     * @param event
     */
    @FXML
    void moveUp(ActionEvent event) 
    {
    	if(listView.getSelectionModel().getSelectedIndex() == -1 || listView.getSelectionModel().getSelectedIndex() < -1)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("NO BAND SELECTED");
    		alert.setContentText("Please select a band");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		return;
    	}
    	
    	if(listView.getSelectionModel().getSelectedIndex() == 0)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("CANNOT MOVE UP");
    		alert.setContentText("Band is already first in list."
    				+ "Please choose a differet band.");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		return;
    	}
    	
    	festivalStage.moveBandUp(bandData, listView.getSelectionModel().getSelectedIndex());
    	stageItems.setAll(bandData);
    	listView.getItems().setAll(stageItems);
    }

    /**This method moves an item down one index in the list view and refreshes it. If
     * the item is at the bottom, or the list view is empty,
     *  an alert is displayed and the method returns.
     * @param event
     */
    @FXML
    void moveDown(ActionEvent event) 
    {
    	if(listView.getSelectionModel().getSelectedIndex() == -1 || listView.getSelectionModel().getSelectedIndex() < -1)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("NO BAND SELECTED");
    		alert.setContentText("Please select a band");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		return;
    	}
    	
    	if(listView.getSelectionModel().getSelectedIndex() ==  festivalStage.getBandList().size()-1)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("CANNOT MOVE DOWN");
    		alert.setContentText("Band is already last in the list."
    				+ "\nPlease choose a different band");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		return;
    	}
    	
    	festivalStage.moveBandDown(bandData, listView.getSelectionModel().getSelectedIndex());
    	stageItems.setAll(bandData);
    	listView.getItems().setAll(stageItems);
    }

    /**This method moves an item to the selected stage. If the destination stage
     * already has the max number of bands allowed, or the user tries to
     * move a the empty index to a destination stage, an alert is displayed.
     * @param event
     */
    @FXML
    void relocateBand(ActionEvent event) 
    {
    	FestivalStage destinationStage = this.festival.findStage(relocationOptions.getValue());

    	if(this.listView.getSelectionModel().getSelectedIndex() < 0)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("EMPTY STAGE");
    		alert.setContentText("Stage currently has no bands." + "\nPlease try again with a differnt stage." + "\nor move bands to this stage.");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		return;
    	}
    	
    	Band band = this.bandData.get(listView.getSelectionModel().getSelectedIndex());
    	//Band band = festival.findBand(festivalStage, bandName);
    	
    	if(destinationStage.getBandCount() < 5)
    	{
    		this.festivalStage.getBandList().remove(band);
    		this.bandData.remove(band);
    		destinationStage.addBand(band);
    		band.setAssignedStage(destinationStage.getStageName());
    		this.festivalStage.decreaseBandCount();
    	}
    	else
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("BAND LIMIT REACHED");
    		alert.setContentText("Stage already has max number of bands." + "\nPlease try again with a differnt stage.");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		return;
    	}

    	this.bandsInLineup.setText("# of bands lineup: " + Integer.toString(festivalStage.getBandCount()));
    	this.stageItems.setAll(bandData);
    	this.listView.getItems().setAll(this.stageItems);
    	this.festival.saveStageBandsFile("stage_bands.csv");
    	
    }
    
	/**This method switches the view back to the festival view
	 * @param event
	 */
	@FXML
	void goHome(ActionEvent event) 
	{
		AppController.getAppInstance().switchView(AppController.FESTIVAL_VIEW);
	}
	
	/**This method populates the list view for data manipulation
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{	
		this.stageName.setText(this.festivalStage.getStageName());
		this.bandsInLineup.setText("# of bands lineup: " + Integer.toString(this.festivalStage.getBandCount()));
		
		Set<?> setOfKeys = this.festival.getStageMap().keySet();
		Iterator<?> iterator = setOfKeys.iterator();
		
		while(iterator.hasNext())
		{
			String key = (String) iterator.next();
			FestivalStage value = this.festival.getStageMap().get(key);
			this.relocationOptions.getItems().add(value.getStageName());
		}
		this.relocationOptions.setPromptText("Select a Stage");
		
		for(Band band: festivalStage.getBandList())
		{
			this.bandData.add(band);
		}
		
		this.stageItems.setAll(this.bandData);
		this.listView.getItems().setAll(this.stageItems);
	}

}
