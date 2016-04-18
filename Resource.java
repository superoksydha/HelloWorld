import java.io.*;

public abstract class Resource implements Serializable{

	/* ATRIBUT */
	private String nama;
	//Nama yang diberikan pemain pada sumber daya
		//setter: - (Diset pada konstruktor dan tidak dapat diubah)
		//getter: String getNama();
	public enum Spesies{
		KOL, LOBAK, JAGUNG, SAPI, DOMBA, AYAM
	}
	private Spesies jenis;
	//Spesies tanaman
		//setter: - (Diset pada konstruktor dan tidak dapat diubah)
		//getter: String getJenis();
	private Koordinat lokasi;
	//Koordinat lokasi sumber daya pada peta
		//setter: void setLokasi(int x, int y);
		//getter: Koordinat getLokasi();
	public boolean alive;
	//Mengembalikan apakah hewan hidup atau sudah mati
		//setter: kill();
		//getter: boolean isAlive();
	private boolean status;
	//Apakah sumber daya sudah dirawat untuk hari itu
		//setter: void care(); resetCare();
		//getter: boolean isCaredToday();
	public enum Produce{
		KOL, LOBAK, JAGUNG, SUSU, WOL, TELUR, NONE
	}

	/* METHOD */
	public Resource(String nama, Spesies jenis, int x, int y){
	/* Konstruktor kelas Resource. Resource tidak dapat dibuat tanpa informasi
	nama, jenis, serta koordinat. */
		this.nama = nama;
		this.jenis = jenis;
		this.lokasi = new Koordinat(x,y);
		this.alive = true;
		this.status = false;
	}

	public String getNama(){
	/* Mengembalikan nama sumber daya */
		return this.nama;
	}

	public Spesies getJenis(){
	/* Mengembalikan spesies sumebr daya dalam bentuk String */
		return this.jenis;
	}

	public void setLokasi(int x, int y){
	/* Mengubah koordinat lokasi sumber daya menjadi (x,y) */
		this.lokasi = new Koordinat(x,y);
	}

	public Koordinat getLokasi(){
	/* Mengembalikan koordinat lokasi sumber daya */
		return this.lokasi;
	}

	public boolean isAlive(){
	/* Mengembalikan status apakah hewan masih hidup atau tidak */
		return this.alive;
	}

	public void care(){
	/* Mengubah status sumber daya menjadi telah dirawat pada hari itu */
		this.status = true;
	}

	public boolean isCaredToday(){
	/* Mengembalikan apakah sumber daya telah dirawat pada hari itu */
		return this.status;
	}

	public void resetCare(){
		this.status = false;
	}

	public void kill(){
		this.alive = false;
	}

	public abstract void resetDay();
}
