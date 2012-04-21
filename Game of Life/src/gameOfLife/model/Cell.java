package gameOfLife.model;

public class Cell {

	private Boolean isAlive;

	public Cell(Boolean isAlive){
		setState(isAlive);
	}
	
	public Boolean isAlive(){
		return this.isAlive;
	}
	
	public void setState(Boolean state){
		this.isAlive = state;
	}
	
	public String toString(){
		return isAlive ? "Y" : "N";
	}
}
