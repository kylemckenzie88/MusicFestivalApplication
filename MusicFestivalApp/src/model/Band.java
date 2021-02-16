package model;

public class Band 
{
	//Variables
	private String assignedStage;
	private String bandName;
	private String genre;
	
	//Constructors
	public Band()
	{
		this.assignedStage = "";
		this.bandName = "";
		this.genre = "";
	}
	
	public Band(String assignedStage, String bandName, String genre)
	{
		this.assignedStage = assignedStage;
		this.bandName = bandName;
		this.genre = genre;
	}
	
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAssignedStage() {
		return assignedStage;
	}
	public void setAssignedStage(String assignedStage) {
		this.assignedStage = assignedStage;
	}
	
	@Override
	public String toString() {
		String output = "";
		
		output += this.bandName + "(" + this.genre + ")";
		
		return output;
	}
	
}
