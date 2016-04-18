//Eliezer Christanto
//18213020

import java.util.Scanner;
import java.io.*;

public class MapRumah implements Serializable {
	Scanner in = new Scanner(System.in);
	public Tile mapu[][] = new Tile[9][16];
	private int x;
	private int y;
	public Player pemain;
	private int menushop;
	private int menubeli;
	private int sumGold;


	public MapRumah(Player a){
		this.x = 9;
		this.y = 16;
		for (int i = 0; i <=8; i++) {
			for (int j = 0; j <=15; j++) {
				if(((i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 6)) && ((j == 2) || (j == 3) || (j == 4))) {
					this.mapu[i][j] = new TileSave();
				}
				else {
					if (((i == 4) || (i == 5) || (i == 6)) && ((j == 9) || (j == 10) || (j == 11) || (j == 12) || (j == 13))) {
						this.mapu[i][j] = new TileShop();
					}
					else {
					this.mapu[i][j] = new TileK();
					}
				}
			}
		}
		this.pemain = a;
	}

	public void action(MapMain a, MapKandangA b, MapKandangSD c) {
		//karena dibuilding, waktu dipause
		//(Waktu.Thread).sleep();

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
			h = shop
		*/

		System.out.println("Masukkan pilihan");
		char pil = in.next().charAt(0);
		switch(pil) {
			case 'a':
			{
				if ((((((this.pemain).getCoord()).getX() - 1) < 0) || ((this.mapu[((this.pemain).getCoord()).getX() - 1][((this.pemain).getCoord()).getY()]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke kiri");
				}
				else {
					(this.pemain).move('a');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map rumah");
				}
			}
			break;

			case'd':
			{
				if ((((((this.pemain).getCoord()).getX() + 1) > 8) || ((this.mapu[((this.pemain).getCoord()).getX() + 1][((this.pemain).getCoord()).getY()]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke kanan");
				}
				else {
					(this.pemain).move('d');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map rumah");
				}
			}
			break;

			case'w':
			{
				if ((((((this.pemain).getCoord()).getY() - 1) < 0) || ((this.mapu[((this.pemain).getCoord()).getX()][((this.pemain).getCoord()).getY() - 1]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke atas");
				}
				else {
					(this.pemain).move('w');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map rumah");
				}
			}
			break;

			case's':
			{
				if ((((((this.pemain).getCoord()).getY() + 1) > 15) || ((this.mapu[((this.pemain).getCoord()).getX()][((this.pemain).getCoord()).getY() + 1]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke bawah");
				}
				else {
					(this.pemain).move('s');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map rumah");
				}
			}
			break;

			case'e':
			{
				System.out.println("Anda ada di dalam rumah...");
			}
			break;

			case'm':
			{
					(this.pemain).setCoord(5,3);
					a.pemain = this.pemain;
					this.pemain = null;
					System.out.println("Player sudah dipindahkan ke map utama");
					a.action(this, b, c);
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
					String an = in.nextLine();
					if(((this.mapu[x][y]).building == false) && ((this.mapu[x][y]).cekIsi == false) && (an == "Top" || an == "Bottom" || an == "Right" | an == "Left")) {
						(this.pemain).setCoord(x,y);
						(this.pemain).setOrient(an);
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
				try {
					FileOutputStream out = new FileOutputStream("mapRumah.ser");
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject (pemain);
					oos.writeObject (this.mapu);
					oos.writeObject (a.mapu);
					oos.writeObject (b.mapu);
					oos.writeObject (c.mapu);
					oos.close ();
					if (!((Waktu.getJJ() > 3) && (Waktu.getJJ() < 6))){
							a.resetDay();
							b.resetDay();
							c.resetDay();
							Waktu.setJJ(06);
							Waktu.setMM(00);
						}
					//throw new saveException();
				} catch (Exception e) {
					e.printStackTrace ();
				}
				
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
				if ( ((((this.pemain).inFrontOf()).getX() == 2) || (((this.pemain).inFrontOf()).getX() == 3) || (((this.pemain).inFrontOf()).getX() == 4) || (((this.pemain).inFrontOf()).getX() == 5) || (((this.pemain).inFrontOf()).getX() == 6) ) && ( (((this.pemain).inFrontOf()).getY() == 2) || (((this.pemain).inFrontOf()).getY() == 3) || (((this.pemain).inFrontOf()).getY() == 4)) ) {
					System.out.println("Ini ranjang anda, anda bisa tidur dan melakukan save game disini");
				}
				else {
					if ( ((((this.pemain).inFrontOf()).getX() == 4) || (((this.pemain).inFrontOf()).getX() == 5) || (((this.pemain).inFrontOf()).getX() == 6) ) && ( (((this.pemain).inFrontOf()).getY() == 7) || (((this.pemain).inFrontOf()).getY() == 8) || (((this.pemain).inFrontOf()).getY() == 9) || (((this.pemain).inFrontOf()).getY() == 10) || (((this.pemain).inFrontOf()).getY() == 11) || (((this.pemain).inFrontOf()).getY() == 12) || (((this.pemain).inFrontOf()).getY() == 13)) ) {
						System.out.println("Ini meja, anda bisa membeli atau menjual benda-benda disini");
					}
					else {
					System.out.println("Anda menghadap petak kosong, tidak ada yang bisa dicek");
					}
				}
			}
			break;

			case 'h':
			{
				System.out.println("---MENU JUAL BELI---");
				System.out.println("\t1. Jual");
				System.out.println("\t2. Beli");

				System.out.print("\n\n Pilih menu anda : "); menushop = in.nextInt();

				switch (menushop) {
					case 1 :
					{
						sumGold = 0;
						for(int i=0 ; i<12; i++) {
							if(this.pemain.bagI[i] != null) {
								sumGold = sumGold + this.pemain.bagI[i].sellPrice;
								this.pemain.bagI[i] = null;
							}
						}
						this.pemain.gold = this.pemain.gold + sumGold;
						System.out.println("\n\nSemua barang di tas telah terjual");
						sumGold = 0;
						break;
					}

					case 2 :
					{
						System.out.println("\n\n---MENU BELI---");
						System.out.println("\t1. Beli biji kol");
						System.out.println("\t2. Beli biji lobak");
						System.out.println("\t3. Beli biji jagung");
						System.out.println("\t4. Beli sapi");
						System.out.println("\t5. Beli domba");
						System.out.println("\t6. Beli ayam");

						System.out.print("\n\n Pilih barang yang akan dibeli : "); menubeli = in.nextInt();

						switch (menubeli) {
							case 1 :
							{
								(this.pemain).bagT[5].jumlah++;
								(this.pemain).gold = (this.pemain).gold - 50;
								break;
							}

							case 2 :
							{
								(this.pemain).bagT[4].jumlah++;
								(this.pemain).gold = (this.pemain).gold - 50;
								break;
							}

							case 3 :
							{
								(this.pemain).bagT[6].jumlah++;
								(this.pemain).gold = (this.pemain).gold - 50;
								break;
							}

							case 4 :
							{
								//beli sapi
								int fax = 0;
								int fbx = 0;
								boolean stop = false;
								for(int ax = 1; ax <= 3; ax++) {
									for(int bx = 1;bx <= 12; bx++) {
										if((((TileKandangSapiD) c.mapu[ax][bx]).isi == null) && (stop == false)) {
											stop = true;
											fax = ax;
											fbx = bx;
										}
									}
								}
								((TileKandangSapiD) c.mapu[fax][fbx]).isi = new Hewan("sapi",Resource.Spesies.SAPI,fax,fbx);
								(this.pemain).gold = (this.pemain).gold - 50;
								break;
							}

							case 5 :
							{
								//beli domba
								int fax = 0;
								int fbx = 0;
								boolean stop = false;
								for(int ax = 1; ax <= 3; ax++) {
									for(int bx = 1;bx <= 12; bx++) {
										if((((TileKandangSapiD) c.mapu[ax][bx]).isi == null) && (stop == false)) {
											stop = true;
											fax = ax;
											fbx = bx;
										}
									}
								}
								((TileKandangSapiD) c.mapu[fax][fbx]).isi = new Hewan("domba",Resource.Spesies.DOMBA,fax,fbx);
								(this.pemain).gold = (this.pemain).gold - 50;
								break;
							}

							case 6 :
							{
								//beli ayam
								int fax = 0;
								int fbx = 0;
								boolean stop = false;
								for(int ax = 1; ax <= 3; ax++) {
									for(int bx = 1;bx <= 12; bx++) {
										if((((TileKandangAyam) b.mapu[ax][bx]).isi == null) && (stop == false)) {
											stop = true;
											fax = ax;
											fbx = bx;
										}
									}
								}
								((TileKandangAyam) b.mapu[fax][fbx]).isi = new Hewan("ayam",Resource.Spesies.AYAM,fax,fbx);
								(this.pemain).gold = (this.pemain).gold - 50;
								break;
							}

							default : System.out.println("Masukkan Anda salah!"); break;
						}
						break;
					}

					default : System.out.println("Masukan Anda salah!"); break;
				}

			}
			break;
		}
		this.action(a,b,c);
	}
}
