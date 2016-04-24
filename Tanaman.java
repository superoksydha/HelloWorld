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

	public void showStatus(){
		System.out.println("Tanaman " + this.getJenis());
		System.out.println("Tanaman ini berada di (" + this.getLokasi().getX() + "," + this.getLokasi().getY() + ")");
		System.out.println("Tanaman ini berada pada fase ke-" + this.fase);
		System.out.println("Tanaman ini telah disiram " + totalWatered() + " selama empat hari terakhir.");
		System.out.print("Tanaman ini ");
		if (isWateredToday()){
			System.out.print("telah");
		} else {
			System.out.print("belum");
		}
		System.out.println(" disiram hari ini.");
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
		if (this.alive) {
			if (this.daytoharvest <= 0){
				this.harvested = true;
				switch (this.getJenis()){
					case KOL: this.alive = false;
						return Produce.KOL;
					case JAGUNG:
						return Produce.JAGUNG;
					case LOBAK: this.alive = false;
						return Produce.LOBAK;
					default: return Produce.NONE;
				}
			}
		}
		return Produce.NONE;
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
			System.out.print(i + ",");
		}
		this.watered[0] = this.isWateredToday();
		super.resetCare();
		this.daytoharvest--;
		if (this.totalWatered() <= 0){
			this.alive = false;
		}
	}
}
