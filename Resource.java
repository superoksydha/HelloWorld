public abstract class Resource{

	/* ATRIBUT */
	private String nama;
	//Nama yang diberikan pemain pada sumber daya
		//setter: - (Diset pada konstruktor dan tidak dapat diubah)
		//getter: String getNama();
	private String jenis;
	//Spesies tanaman
		//setter: - (Diset pada konstruktor dan tidak dapat diubah)
		//getter: String getJenis();
	private Koordinat lokasi;
	//Koordinat lokasi sumber daya pada peta
		//setter: void setLokasi(int x, int y);
		//getter: Koordinat getLokasi();
	private boolean alive;
	//Mengembalikan apakah hewan hidup atau sudah mati
		//setter: kill();
		//getter: boolean isAlive();
	private boolean status;
	//Apakah sumber daya sudah dirawat untuk hari itu
		//setter: void care(); resetCare();
		//getter: boolean isCaredToday();

	/* METHOD */
	public Resource(String nama, String jenis, int x, int y){
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

	public String getJenis(){
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