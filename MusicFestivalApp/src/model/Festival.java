package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Festival 
{
	//Variables
	private String festivalName;
	private HashMap<String, FestivalStage> stageMap;
	
	//Constructors
	public Festival()
	{
		this.festivalName = "";
		this.stageMap = new HashMap<String, FestivalStage>();
	}
	
	public Festival(String festivalName)
	{
		this.festivalName = festivalName;
		this.stageMap = new HashMap<String, FestivalStage>();
	}

	public String getFestivalName() 
	{
		return festivalName;
	}

	public void setFestivalName(String festivalName) 
	{
		this.festivalName = festivalName;
	}

	
	/**This method returns a hasMap containing stages
	 * @return stageMap
	 */
	public HashMap<String, FestivalStage> getStageMap() 
	{
		return stageMap;
	}
	
	/**This method returns the FestivalStage searched for
	 * @param stageName
	 * @return
	 */
	public FestivalStage findStage(String stageName)
	{
		return this.stageMap.get(stageName);
	}
	
	
	/**This method returns the Band searched for
	 * @param stage
	 * @param bandToFind
	 * @return
	 */
	public Band findBand(FestivalStage stage, String bandToFind)
	{
		for(Band band: stage.getBandList())
		{
			if(band.getBandName().equalsIgnoreCase(bandToFind))
			{
				return band;
			}
		}
		return null;
	}

	/**This method populates a festival using provided data files
	 * @param festivalFileName
	 * @param bandFileName
	 */
	public void loadFestival(String festivalFileName, String bandFileName)
	{
		File festivalFile = new File(festivalFileName);
		File bandFile = new File(bandFileName);
		
		FestivalStage stage = null;
		
		try 
		{
			Scanner festivalInput = new Scanner(festivalFile);
			String festivalTitle = festivalInput.nextLine();
			String[] festivalTitleTokens = festivalTitle.split(",");
			this.setFestivalName(festivalTitleTokens[0]);
			
			while(festivalInput.hasNextLine())
			{
				String line = festivalInput.nextLine();
				String[] festivalTokens = line.split(",");
				
				stage = findStage(festivalTokens[0]);
				
				if(stage == null)
				{
					stage = new FestivalStage(festivalTokens[0]);
					this.stageMap.put(stage.getStageName(), stage);
				}
			}
			festivalInput.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		Band band = null;
		
		try 
		{
			Scanner bandInput = new Scanner(bandFile);
			
			while(bandInput.hasNextLine())
			{
				String line = bandInput.nextLine();
				String[] bandInputTokens = line.split(",");
				
				stage = findStage(bandInputTokens[0]);
				band = findBand(stage ,bandInputTokens[1]);
				
				if(band == null)
				{
					band = new Band(bandInputTokens[0], bandInputTokens[1], bandInputTokens[2]);
					stage.addBand(band);
					
				}
				
			}
			bandInput.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
	}
	
	/**This method saves data to the data files after manipulation by the use
	 * @param stage_bandsFileName
	 */
	public void saveStageBandsFile(String stage_bandsFileName)
	{
		try 
		{
			PrintWriter output = new PrintWriter(stage_bandsFileName);
			
			Set<?> setOfKeys = this.getStageMap().keySet();
			Iterator<?> iterator = setOfKeys.iterator();
			
			while(iterator.hasNext())
			{
				String key = (String) iterator.next();
				FestivalStage value = this.getStageMap().get(key);
				
				for(Band band: value.getBandList())
				{
					output.print(value.getStageName() + "," + band.getBandName() + "," + band.getGenre());
					output.println();
				}	
			}
			
			output.flush();
			output.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

}
