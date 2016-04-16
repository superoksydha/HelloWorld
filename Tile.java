//Eliezer Christanto
//18213020

public class Tile {
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
}
