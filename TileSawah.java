//Eliezer Christanto
//18213020

public class TileSawah extends Tile {
	//ini tile yang dianggap sawah
	//atribut isinya tanaman
	boolean paculed;
	Tanaman isi;

	public TileSawah() {
		super();
		this.paculed = false;
		//atribut tanaman isi dengan null
	}

	public void pacul(){
		this.paculed = true;
	}

	public void palu(){
		if (this.cekIsi == false){
			this.paculed = false;
		}
	}

	public void tanam(Resource.Spesies biji, Koordinat k){
		if (this.paculed == true){
			switch (biji){
				case KOL: this.isi = new Kol("Kol", k.getX(), k.getY());
				case LOBAK: this.isi = new Lobak("Lobak", k.getX(), k.getY());
				case JAGUNG: this.isi = new Jagung("Jagung", k.getX(), k.getY());
			}
			this.cekIsi = true;
		}
	}

	public void removeTanaman(){
		this.isi = null;
		this.cekIsi = false;
	}

	public void resetDay(){
		this.isi.resetDay();
		if (!(this.isi.isAlive())){
			this.removeTanaman();
		}
	}

	public Resource.Produce harvest(){
		return this.isi.harvest();
	}
}
