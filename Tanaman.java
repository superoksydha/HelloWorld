public abstract class Tanaman extends Resource{

	/* ATRIBUT */
	private int fase;
	//Fase hewan. Bila biji mengembalikan 0, dst.
		//setter: void grow();
		//getter: int getFase()
	private boolean watered[] = new boolean[4];
	//Pencatatan penyiraman tanaman dalam 4 hari terakhir
		//setter: - (Diset pada konstruktor dan hanya dapat diubah via resetDay())
		//getter: - (Hanya digunakan pada metode resetDay()), int totalWatered() utk mengetahui berapa kali tanaman disiram
	private int daytoharvest;
	//Pencatatan penyiraman tanaman dalam 4 hari terakhir
		//setter: void setDayToHarvest() (Diset pada konstruktor child class dan hanya dapat diubah via resetDay() yang dioverride di child class, kecuali utk tanaman regrowable)
		//getter: int getDayToHarvest, String harvest();
	private boolean harvested;
	//Mencatat apakah tanaman sudah dipanen atau belum
		//setter: void resetHarvest (Diset pada konstruktor dan hanya dapat diubah via resetDay(), kecuali jagung)
		//getter: boolean isHarvested();

	/* METHOD */
	public Tanaman(String nama, Spesies jenis, int daytoharvest, int x, int y){
	/* Konstruktor kelas tanaman. Tanaman tidak dapat dibuat tanpa nama, jenis,
	daytoharvest, serta koordinat. */
		/* Algoritma */
		super(nama, jenis, x, y);			//set nama, jenis, lokasi, status, serta status hidup hewan
		this.fase = 0;						//set fase tanaman
		for (int i = 0;i < 4;i++){
			this.watered[i] = true;			//set tanaman sebagai telah disiram selama empat hari terakhir
		}
		this.daytoharvest = daytoharvest;	//set berapa hari lagi tanaman siap dipetik
		this.harvested = false;				//set tanaman sebagai belum dipanen
	}

	public void water(){
	/* Mengubah status tanaman menjadi telah disiram pada hari itu */
		this.care();
	}

	public boolean isWateredToday(){
	/* Mengembalikan apakah tanaman telah disiram pada hari itu */
		return this.isCaredToday();
	}

	public Produce harvest(){
	/* Mengembalikan hasil produksi tanaman dalam bentuk String */
		if (this.daytoharvest <= 0){
			this.harvested = true;
			switch (this.getJenis()){
				case KOL: return Produce.KOL;
				case JAGUNG: return Produce.JAGUNG;
				case LOBAK: return Produce.LOBAK;
				default: return Produce.NONE;
			}
		} else {
			return Produce.NONE;
		}
	}

	public void grow(){
		this.fase = this.fase++;
	}

	public int getFase(){
	/* Mengembalikan fase hidup tumbuhan */
		return this.fase;
	}

	public int totalWatered(){
	/* Mengembalikan berapa kali tanaman disiram pada 4 hari terakhir */
		int i,s;						//i untuk menghitung, s sebagai total
		s = 0;							//inisiasi total
		for (i = 0;i < 4;i++){
			if (watered[i]){
				s++;					//jika tanaman telah disiram pada hari itu, s ditambah satu
			}
		}
		return s;
	}

	public void setDayToHarvest(int x){
		this.daytoharvest = x;
	}

	public int getDayToHarvest(){
		return this.daytoharvest;
	}

	public void resetHarvest(){
		this.harvested = false;
	}

	public boolean isHarvested(){
		return this.harvested;
	}

	public void resetDay(){
		for(int i = 3;i > 0;i--){
			this.watered[i] = this.watered[i - 1];	//memindahkan isi array watered 1 hari ke belakang
		}
		this.watered[0] = this.isWateredToday();
		super.resetCare();
		this.daytoharvest--;
		if (this.totalWatered() < 0){
			super.kill();
		}
	}
}
