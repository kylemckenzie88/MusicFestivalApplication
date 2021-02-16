package model;

import java.util.ArrayList;

public class FestivalStage 
{
	//Variables
	private String stageName;
	private ArrayList<Band> bandList;
	private int bandCount;

	//Constructors
	public FestivalStage()
	{
		this.stageName = "";
		this.bandList = new ArrayList<Band>();
		this.bandCount = 0;
	}
	
	public FestivalStage(String stageName)
	{
		this.stageName = stageName;
		this.bandList = new ArrayList<Band>();
		this.bandCount = 0;
	}
	
	public String getStageName() {
		return stageName;
	}
	
	public void setStageName(String stageName) 
	{
		this.stageName = stageName;
	}

	/**This method adds a band and increments the band count
	 * @param band
	 */
	public void addBand(Band band)
	{
		this.bandList.add(band);
		this.bandCount++;
	}
	
	public ArrayList<Band> getBandList() 
	{
		return this.bandList;
	}

	public int getBandCount() 
	{
		return this.bandCount;
	}
	
	public void decreaseBandCount()
	{
		this.bandCount--;
	}
	
	/**This method moves a band up in the ArrayList
	 * @param stageItems
	 * @param index
	 */
	public void moveBandUp(ArrayList<Band> stageItems, int index)
	{
		Band itemToMove = stageItems.get(index);
		stageItems.add(index-1, itemToMove);
		stageItems.remove(index+1);
		
	}
	
	/**This method moves a band down in the ArrayList
	 * @param stageItems
	 * @param index
	 */
	public void moveBandDown(ArrayList<Band> stageItems, int index)
	{
		Band itemToMove = stageItems.get(index);
		stageItems.add(index + 2, itemToMove);
		stageItems.remove(index);
	}
}
