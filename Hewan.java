public class Hewan extends Resource{

	/* ATRIBUT */
	private boolean harvested;
	//Apakah hewan telah diperah/digunting/diambil telur pada hari itu
		//setter: String harvest()
		//getter: - (Hanya digunakan pada metode harvest())
	private boolean[] fed;
	//Pencatatan pemberian makan hewan selama 6 hari terakhir
		//setter: - (Diset pada konstruktor dan hanya dapat diubah via resetDay())
		//getter: - (Hanya digunakan pada metode resetDay()), int totalFed() utk mengetahui berapa kali hewan telah diberi makan

	/* METHOD */
	public Hewan(String nama, Spesies jenis, int x, int y){
	/* Konstruktor kelas hewan. Hewan tidak dapat dibuat tanpa informasi nama,
	jenis, serta koordinat. */
		/* Algoritma */
		super(nama, jenis, x, y);			//set nama, jenis, lokasi, status, serta status hidup hewan
		this.harvested = false;				//set hewan sebagai belum diperah/digunting/diambil telur pada hari itu
		for (int i = 0;i < 6;i++){
			this.fed[i] = true;				//set pemberian makan hewan menjadi true selama 6 hari terakhir
		}
	}

	public void kickTo(Koordinat k){
	/* Mengubah koordinat lokasi hewan menjadi (x,y) */
		super.setLokasi(k.getX(), k.getY());
	}

	public void feed(){
	/* Mengubah status hewan menjadi sudah diberi makan pada hari itu */
		super.care();
	}

	public boolean isFedToday(){
	/* Mengembalikan status hewan apakah ia sudah diberi makan pada hari itu */
		return super.isCaredToday();
	}

	public Produce harvest(){
	/* Mengembalikan hasil produksi hewan dalam bentuk String. */
		if ((this.harvested == false) && (isCaredToday())){
			switch (this.getJenis()){
				case SAPI: return Produce.SUSU;
				case DOMBA: return Produce.WOL;
				case AYAM: return Produce.TELUR;
				default: return Produce.NONE;
			}
		} else {
			return Produce.NONE;
		}
	}

	public int totalFed(){
	/* Mengembalikan berapa kali hewan diberi makan selama 6 hari terakhir */
		int i, s;							//i untuk menghitung, s sebagai total
		s = 0;								//inisiasi total
		for(i = 0;i < 6;i++){
			if (this.fed[i]){
				s++;						//jika hewan diberi makan pada i hari sebelumnya, s ditambah satu
			}
		}
		return s;
	}

	public void resetDay(){
	/* Me-reset status hewan pada hari itu */
		for(int i = 5;i > 0;i--){
			this.fed[i] = this.fed[i - 1];	//memindahkan isi array fed 1 hari ke belakang
		}
		this.fed[0] = this.isFedToday();	//memindahkan isi this.status ke fed[0]
		super.resetCare();
		this.harvested = false;				//set hewan sebagai belum diperah/digunting/diambil telur pada hari itu
		if (this.totalFed() < 0) {
			super.kill();					//jika hewan tidak diberi makan selama 6 hari terakhir, hewan mati
		}
	}
}
