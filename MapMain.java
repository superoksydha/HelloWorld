//Eliezer Christanto
//18213020

import java.util.Scanner;
import java.io.Serializable;

public class MapMain implements Serializable {
	Scanner in = new Scanner(System.in);
	public Tile mapu[][] = new Tile[32][29];
	private int x;
	private int y;
	public Player pemain;

	public MapMain(Player a){
		this.x = 32;
		this.y = 29;
		for (int i = 0; i <= 31; i++) {
			for (int j = 0; j <=28; j++) {
					this.mapu[i][j] = new TileK();
			}
		}

		//untuk tile home
		for (int i = 2; i <= 5; i++) {
			for (int j = 2; j<= 8; j++) {
				this.mapu[i][j] = new TileHome();
			}
		}
		this.mapu[5][3] = new TileK();
		(this.mapu[4][3]).isPintu = true;

		//untuk tile kandang ayam
		for (int i = 2; i <= 4; i++) {
			for (int j = 15; j<= 18; j++) {
				this.mapu[i][j] = new TileKandangAyam();
			}
		}
		this.mapu[4][16] = new TileK();
		(this.mapu[3][16]).isPintu = true;

		//untuk kandang sapi domba
		for (int i = 12; i <= 14; i++) {
			for (int j = 1; j<= 4; j++) {
				this.mapu[i][j] = new TileKandangSapiD();
			}
		}
		this.mapu[13][4] = new TileK();
		(this.mapu[13][3]).isPintu = true;

		//untuk sawah
		for (int i = 10; i <= 29; i++) {
			for (int j = 7; j<= 26; j++) {
				this.mapu[i][j] = new TileSawah();
			}
		}

		this.pemain = a;
	}

	public void action(MapRumah a, MapKandangA b, MapKandangSD c) {
		//terima masukan tombol, lakukan aksi sesuai tombol
		//daftar tombol:
		/*
			wasd = pindah
			e = gunakan tool
			x = exit
			m = masuk
			t = cek waktu
			p = cek status player
			v = save
			c = cek objek
			n = navigasi koordinat
		*/

		System.out.println("Masukkan kode aksi");
		char pil = in.next().charAt(0);
		switch(pil) {
			case 'a':
			{
				if ((((this.pemain).getCoord()).getX() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true) || ((this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())]).building == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke kiri");
				}
				else {
					(this.pemain).move('a');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama");
				}
			}
			break;

			case'd':
			{
				if ((((this.pemain).getCoord()).getX() + 1) > 31 || (((this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true) || ((this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())]).building == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke kanan");
				}
				else {
					(this.pemain).move('d');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama");
				}
			}
			break;

			case'w':
			{
				if ((((this.pemain).getCoord()).getY() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)]).cekIsi == true) || ((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)]).building == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke atas");
				}
				else {
					(this.pemain).move('w');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama");
				}
			}
			break;

			case's':
			{
				if ((((this.pemain).getCoord()).getY() + 1) > 28 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() + 1)]).cekIsi == true) || ((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() + 1)]).building == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke bawah");
				}
				else {
					(this.pemain).move('s');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama");
				}
			}
			break;

			case'e':
			{
				switch (((this.pemain).tool).getName()) {
				
					case "milker"	: 
					{
						System.out.println("Milker tidak dapat digunakan disini"); break;
					}
					
					case "gunting"	: 
					{
						System.out.println("Gunting tidak dapat digunakan disini"); break;
					}
									 
					case "move"		: 
					{
						System.out.println("Move tidak dapat digunakan disini"); break;
					}
					
					case "hand" : 
					{
						if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi == null) {
							if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.getDayToHarvest() <= 0) {
								for (int i = 0; i<12; i++) {
									if ((this.pemain).bagI[i] != null) {
										Resource.Produce hasilpanen = ((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.harvest();
										
										switch (hasilpanen) {
											case KOL : {
															(this.pemain).bagI[i] = new Items("Kol", 100); break;
													   }
											case JAGUNG : {
															(this.pemain).bagI[i] = new Items("Jagung", 100); break;
													   }
											case LOBAK : {
															(this.pemain).bagI[i] = new Items("Lobak", 100); break;
													   }
										}
									}
								}
							} else {
								System.out.println("Tidak ada slot di tas, panen dibatalkan");
							}
						} else {
							System.out.println("Tidak ada yang bisa dipanen");
						}
						 break;
					}
						 
					case "pacul" : 
					{
						((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).paculed = true; break;
					}
									  
					case "penyiramTanaman" : 
					{
						if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
							((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.water();
						}
						 break;
					}

					case "bijiKol" : 
					{
						if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi == null) {
							((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).tanam(Resource.Spesies.KOL, (this.pemain).inFrontOf());
							(this.pemain).bagT[5].jumlah--;
							
						} else {
							System.out.println("Tidak dapat menanam biji kol");	
						}
						 break;
					}
									  
					case "bijiLobak" : 
					{
						if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi == null) {
							((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).tanam(Resource.Spesies.LOBAK, (this.pemain).inFrontOf());
							(this.pemain).bagT[4].jumlah--;
						} else {
							System.out.println("Tidak dapat menanam biji lobak");
						}							
						 break;
					}
									  
					case "bijiJagung" : 
					{
						if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi == null) {
							((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).tanam(Resource.Spesies.JAGUNG, (this.pemain).inFrontOf());
							(this.pemain).bagT[6].jumlah--;
						} else {
							System.out.println("Tidak dapat menanam biji jagung");				
						}
						 break;
					}
									 
					case "arit"		 : 
					{
						//HARUS DICEK DULU APAKAH TILE YANG DIDEPAN PLAYER ITU TANAMAN
						if (((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
							((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi = null;
						} else {
							System.out.println("Tidak ada tanaman untuk diarit");
						}
						 break;
					}
				}
			}
			break;

			case'm':
			{
				if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isPintu == false) {
					System.out.println("Anda tidak menghadap ke pintu...");
				}
				else {
					if (((this.pemain).getCoord()).getX() == 5 && ((this.pemain).getCoord()).getY() == 3) {
						System.out.println("Player sudah dipindahkan kedalam Rumah");
						this.pemain.setCoord(0,15);
						a.pemain = this.pemain;
						this.pemain = null;
						a.action(this,b,c);
						throw new inBuildingException();
					}
					if (((this.pemain).getCoord()).getX() == 13 && ((this.pemain).getCoord()).getY() == 4) {
						System.out.println("Player sudah dipindahkan kedalam Kandang Sapi Domba");
						this.pemain.setCoord(0,12);
						c.pemain = this.pemain;
						this.pemain = null;
						c.action(this,a,b);
						throw new inBuildingException();
					}
					if (((this.pemain).getCoord()).getX() == 4 && ((this.pemain).getCoord()).getY() == 16) {
						System.out.println("Player sudah dipindahkan kedalam Kandang Ayam");
						this.pemain.setCoord(0,12);
						b.pemain = this.pemain;
						this.pemain = null;
						b.action(this,a,c);
						throw new inBuildingException();
					}
				}
			}
			break;

			case't':
			{
				Waktu.printWaktu();
				//cek waktu
			}
			break;

			case'n':
			{
				//navigasi koordinat
				boolean ulang = true;
				while(ulang) {
					System.out.println("Masukkan koordinat x");
					int x = in.nextInt();
					System.out.println("Masukkan koordinat y");
					int y = in.nextInt();
					System.out.print("Masukkan arah hadap (Top, Bottom, Right, Left)");
					String ac = in.nextLine();
					System.out.println(ac);
					if(((this.mapu[x][y]).building == false) && (this.mapu[x][y].cekIsi == false) && (ac == "Top" || ac == "Bottom" || ac == "Right" | ac == "Left")) {
						(this.pemain).setCoord(x,y);
						(this.pemain).setOrient(ac);
						ulang = false;
					}
					else {
						System.out.println("Kotak tidak dapat ditempati pemain atau masukan arah hadap salah... Ulangi masukan");
					}
				}
			}
			break;

			case'p':
			{
				System.out.println(this.pemain.getNama() + " memiliki tools sebagai berikut:");
				for(int i = 0; i<9; i++) {
					System.out.println(this.pemain.bagT[i].getName() + " sebanyak " + this.pemain.bagT[i].jumlah);
				}
				System.out.println("Tools yang sedang di-equip adalah: " + this.pemain.tool.getName());
				System.out.println("Inventory sebagai berikut:");
				for(int i = 0; i<12; i++) {
					if(this.pemain.bagI[i] != null) {
						System.out.println(this.pemain.bagI[i].getName());
					}
				}
				System.out.println("Gold yang dimiliki adalah: " + this.pemain.gold);
				System.out.println("0-9: Pilih tool equip");
				System.out.println("x: back to map");
				System.out.println("Masukkan pilihan anda");
				char pilak = in.next().charAt(0);
				switch(pilak) {
					case '0': {
						this.pemain.selectTool(0);
					}
					break;
					case '1': {
						this.pemain.selectTool(1);
					}
					break;
					case '2': {
						this.pemain.selectTool(2);
					}
					break;
					case '3': {
						this.pemain.selectTool(3);
					}
					break;
					case '4': {
						this.pemain.selectTool(4);
					}
					break;
					case '5': {
						this.pemain.selectTool(5);
					}
					break;
					case '6': {
						this.pemain.selectTool(6);
					}
					break;
					case '7': {
						this.pemain.selectTool(7);
					}
					break;
					case '8': {
						this.pemain.selectTool(8);
					}
					break;
					case '9': {
						this.pemain.selectTool(9);
					}
					break;
					case 'x': {
						System.out.println("OK");
					}
					break;
					default: {
						System.out.println("\n    Pilihan salah. Anda kembali ke map");
					}
					break;
				}
			}
			break;

			case'v':
			{
				//save, karena ini di map utama, ga bisa save
				System.out.println("Maaf, anda tidak bisa tidur disini");
			}
			break;

			case'x':
			{
				System.out.println("Terimakasih telah bermain");
				System.exit(0);
				//exit
			}
			break;

			case'c':
			{
				//cek objek arah hadap player
				if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).building == true) {
					System.out.println("Di hadapan anda ada bangunan");
				}
				else if (this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()] instanceof TileSawah) {
					if (((TileSawah) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
						System.out.println((((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi).getNama() + " dengan status siram hari ini " + ((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.isWateredToday() + " dan status hidup " + ((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.isAlive());
						System.out.println(((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.totalWatered());
					}
					else {
						System.out.println("Di hadapan anda ada petak sawah");
					}
				}
				else {
					System.out.println("Di hadapan anda tidak ada apa-apa");
				}
			}
			break;
			default: {
				System.out.println("\n    Pilihan salah. Mohon ulangi");
			}
			break;
		}
		this.action(a,b,c);
	}

	public void resetDay(){
		for (int i = 10; i <= 29; i++) {
			for (int j = 7; j<= 26; j++) {
				((TileSawah)this.mapu[i][j]).resetDay();
			}
		}
	}
}
