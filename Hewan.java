public class Hewan extends Resource{

	/* ATRIBUT */
	private boolean harvested;
	//Apakah hewan telah diperah/digunting/diambil telur pada hari itu
		//setter: String harvest()
		//getter: - (Hanya digunakan pada metode harvest())
	private boolean fed[] = new boolean[6];
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

	public void showStatus(){
		System.out.println("Hewan " + this.getJenis());
		System.out.println("Hewan ini berada di (" + this.getLokasi().getX() + "," + this.getLokasi().getY() + ") di kandangnya");
		System.out.print("Hewan ini ");
		if (this.harvested == true){
			System.out.print("telah");
		} else {
			System.out.print("belum");
		}
		System.out.print(" diambil ");
		switch (this.getJenis()){
			case SAPI: System.out.print("susunya");
			break;
			case DOMBA: System.out.print("susunya");
			break;
			case AYAM: System.out.print("susunya");
			break;
			default: System.out.print("errorspecies!");
			break;
		}
		System.out.println(" hari ini.");
		System.out.println("Hewan ini telah diberi makan " + totalFed() + " selama enam hari terakhir.");
		System.out.print("Hewan ini ");
		if (isFedToday()){
			System.out.print("telah");
		} else {
			System.out.print("belum");
		}
		System.out.println(" diberi makan hari ini.");
	}

	public void kickTo(int x, int y){
	/* Mengubah koordinat lokasi hewan menjadi (x,y) */
		super.setLokasi(x, y);
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
		if (this.alive){
			if ((this.harvested == false) && (isCaredToday())){
				switch (this.getJenis()){
					case SAPI: return Produce.SUSU;
					case DOMBA: return Produce.WOL;
					case AYAM: return Produce.TELUR;
					default: return Produce.NONE;
				}
			}
		}
		return Produce.NONE;
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
		if (this.totalFed() <= 0) {
			this.alive = false;					//jika hewan tidak diberi makan selama 6 hari terakhir, hewan mati
		}
	}
}
