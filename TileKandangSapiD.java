//Eliezer Christanto
//18213020

public class TileKandangSapiD extends Tile {
	//ini tile yang dianggap kandang sapi domba
	public Hewan isi;

	public TileKandangSapiD() {
		super();
		this.isi = null;
		this.building = true;
		this.cekIsi = false;
	}

	public void newHewan(Hewan hewan){
		this.isi = hewan;
		this.cekIsi = true;
	}

	public void removeHewan(){
		this.isi = null;
		this.cekIsi = false;
	}

	public Resource.Produce harvest(){
		return this.isi.harvest();
	}

	public void resetDay(){
		if (this.cekIsi) {
			this.isi.resetDay();
			if (!(this.isi.isAlive())){
				this.removeHewan();
			}
		}
	}
}
