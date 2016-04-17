//Eliezer Christanto
//18213020

import java.io.*;

public class Tile implements Serializable {
	public boolean cekIsi; //false = kosong, true = ada objek
	public boolean building; // true = building, ga bisa dilewatin
	public boolean isPintu; // true = pintu, false = bukan
	public boolean breedable; //true = dapat diisi hewan

	public Tile() {
		this.cekIsi = false;
		this.isPintu = false;
		this.breedable = false;
		this.building = false;
	}

	public void resetDay(){}
}
