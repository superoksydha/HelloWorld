import java.io.*;

public class Inventory implements Serializable {
	private String name;

	public Inventory(String name){
		this.name = name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}


}
