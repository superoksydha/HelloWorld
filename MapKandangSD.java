//Eliezer Christanto
//18213020

import java.util.Scanner;
import java.io.Serializable;

public class MapKandangSD implements Serializable {
	Scanner in = new Scanner(System.in);
	public Tile mapu[][] = new Tile[8][13];
	private int x;
	private int y;
	public Player pemain;

	public MapKandangSD(Player a){
		this.x = 8;
		this.y = 13;
		for (int i = 0; i <=7; i++) {
			for (int j = 0; j <=12; j++) {
					this.mapu[i][j] = new TileKandangSapiD();
			}
		}
		this.mapu[4][1] = new TileHM();
		this.mapu[6][1] = new TileHM();
		this.mapu[4][3] = new TileHM();
		this.mapu[6][3] = new TileHM();
		this.mapu[4][5] = new TileHM();
		this.mapu[6][5] = new TileHM();
		this.mapu[4][7] = new TileHM();
		this.mapu[6][7] = new TileHM();
		this.mapu[4][9] = new TileHM();
		this.mapu[6][9] = new TileHM();
		this.mapu[4][11] = new TileHM();
		this.mapu[6][11] = new TileHM();
		this.pemain = a;
	}

	public void action(MapMain a, MapRumah b, MapKandangA c) {
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

		System.out.println("Masukkan pilihan");
		char pil = in.next().charAt(0);
		switch(pil) {
			case 'a':
			{
				if ((((this.pemain).getCoord()).getX() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true)) || (this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())] instanceof TileHM)) {
					System.out.println("Maaf, anda tidak bisa pindah ke kiri");
				}
				else {
					(this.pemain).move('a');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang sapi domba");
				}
			}
			break;

			case'd':
			{
				if ((((this.pemain).getCoord()).getX() + 1) > 7 || (((this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true)) || (this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())] instanceof TileHM)) {
					System.out.println("Maaf, anda tidak bisa pindah ke kanan");
				}
				else {
					(this.pemain).move('d');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang sapi domba");
				}
			}
			break;

			case'w':
			{
				if ((((this.pemain).getCoord()).getY() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)]).cekIsi == true)) || (this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)] instanceof TileHM)) {
					System.out.println("Maaf, anda tidak bisa pindah ke atas");
				}
				else {
					(this.pemain).move('w');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang sapi domba");
				}
			}
			break;

			case's':
			{
				if ((((this.pemain).getCoord()).getY() + 1) > 12 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() + 1)]).cekIsi == true)) || (this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY() + 1)] instanceof TileHM)) {
					System.out.println("Maaf, anda tidak bisa pindah ke bawah");
				}
				else {
					(this.pemain).move('s');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang sapi domba");
				}
			}
			break;

			case'e':
			{
				//cek equip tool player
				if ((((this.pemain).tool).getName() != "hand") && (((this.pemain).tool).getName() != "move") && (((this.pemain).tool).getName() != "gunting") && (((this.pemain).tool).getName() != "milker")) {
					System.out.println("Anda berada dalam kandang, tool yang anda equip tidak ada gunanya disini");
				}
				else {
					if (((this.pemain).tool).getName() == "move") {
						if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]) instanceof TileKandangSapiD) {
							if (((TileKandangSapiD)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
								switch(this.pemain.getOrient()) {
									case TOP : {
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.kickTo((((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getX()), (((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getY() - 1));
										((TileKandangSapiD) this.mapu[this.pemain.getCoord().getX()][this.pemain.getCoord().getY() - 2]).newHewan(((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi);
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).removeHewan();
									}
									break;
									case BOTTOM : {
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.kickTo((((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getX()), (((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getY() + 1));
										((TileKandangSapiD) this.mapu[this.pemain.getCoord().getX()][this.pemain.getCoord().getY() + 2]).newHewan(((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi);
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).removeHewan();
										}
									break;
									case RIGHT : {
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.kickTo((((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getX() + 1), (((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getY()));
										((TileKandangSapiD) this.mapu[this.pemain.getCoord().getX() + 2][this.pemain.getCoord().getY()]).newHewan(((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi);
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).removeHewan();
									}
									break;
									case LEFT : {
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.kickTo((((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getX() - 1), (((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).isi.getLokasi().getY()));
										((TileKandangSapiD) this.mapu[this.pemain.getCoord().getX() - 2][this.pemain.getCoord().getY()]).newHewan(((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi);
										((TileKandangSapiD) this.mapu[this.pemain.inFrontOf().getX()][this.pemain.inFrontOf().getY()]).removeHewan();
									}
									break;
								}
							}
							else {
								System.out.println("Anda tidak dapat memindahkan objek didepan anda");
							}
						}
						else {
							System.out.println("Anda tidak dapat menggunakan tools tesebut disini");
						}
					}
					else {
						if (((this.pemain).tool).getName() == "hand") {
							if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]) instanceof TileHM) {
								System.out.println("Masukkan nomor item yang ingin ditaruh di tempat makanan hewan");
								int piltar = in.nextInt();
								((TileHM) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi = this.pemain.bagI[piltar];
								this.pemain.bagI[piltar] = null;
								for (int i = 0; i <=3; i++) {
									for (int j = 0; j <=12; j++) {
										if (((TileKandangSapiD) this.mapu[i][j]).isi != null) {
											((TileKandangSapiD) this.mapu[i][j]).isi.feed();
										}
									}
								}
							}
							else {
								System.out.println("Anda tidak dapat menaruh item didepan anda");
							}
						}
						else {
							if (this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()] instanceof TileKandangSapiD) {
							if ( (((this.pemain).tool).getName() == "milker") && (((TileKandangSapiD)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) ) {
								int urut = 0;
								while(this.pemain.bagI[urut] != null) {
									urut = urut + 1;
								}
								if (urut == 12) {
									System.out.println("Bag anda penuh");
								}
								else {
									this.pemain.bagI[urut] = new Items("susu",100);
								}
							}
							else {
								if(((TileKandangSapiD)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
									int urut = 0;
									while(this.pemain.bagI[urut] != null) {
										urut = urut + 1;
									}
									if (urut == 12) {
										System.out.println("Bag anda penuh");
									}
									else {
										this.pemain.bagI[urut] = new Items("wool",100);
									}
								}
							}
						}
						else {
							System.out.println("Anda tidak dapat menggunakan tools tesebut disini");
						}
						}
					}
				}
			}
			break;

			case'm':
			{
					(this.pemain).setCoord(13,4);
					a.pemain = this.pemain;
					this.pemain = null;
					System.out.println("Player sudah dipindahkan ke map utama");
					a.action(b,c,this);
					throw new outBuildingException();
			}

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
					System.out.println("Masukkan arah hadap (Top, Bottom, Right, Left)");
					String ac = in.nextLine();
					if(((this.mapu[x][y]).building == false) && (this.mapu[x][y].cekIsi == false) && (ac == "Top" || ac == "Bottom" || ac == "Right" | ac == "Left")) {
						this.pemain.setCoord(x,y);
						this.pemain.setOrient(ac);
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
				for(int i = 0; i<=9; i++) {
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
				//save, karena ini di map kandang, ga bisa save
				System.out.println("Maaf, anda tidak bisa tidur disini");
			}
			break;

			case'x':
			{
				System.exit(0);
				//exit
			}
			break;

			case'c':
			{
				//cek objek arah hadap player
				if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()] instanceof TileHM)) {
					System.out.println("Ini tempat makan hewan anda berisi " + ((TileHM) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.getName());
				}
				else {
					if((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()] instanceof TileKandangSapiD) && ((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi == null) {
						System.out.println("Anda menghadap petak kosong");
					}
					else {
						System.out.println("Didepan anda ada sapi bernama " + ((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.getNama() + " dengan status makan " + ((TileKandangSapiD) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi.isFedToday());
					}
				}
			}
			break;
		}
		this.action(a,b,c);
	}

	public void resetDay(){
		for (int i = 0; i <=7; i++) {
			for (int j = 0; j <=12; j++) {
				this.mapu[i][j].resetDay();
			}
		}
	}
}
