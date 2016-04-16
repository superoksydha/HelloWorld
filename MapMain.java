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
			for (int j = 2; j<= 15; j++) {
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

		System.out.print("  Masukkan kode aksi");
		System.out.print("\n  > ");
		char pil = in.next().charAt(0);
		switch(pil) {
			case 'a':
			{
				if ((((this.pemain).getCoord()).getX() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true) && ((this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())]).building == false))) {
					System.out.println("  Maaf, anda tidak bisa pindah ke kiri");
				}
				else {
					(this.pemain).move('a');
					System.out.println("  Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama\n");
				}
			}
			break;

			case'd':
			{
				if ((((this.pemain).getCoord()).getX() + 1) > 31 || (((this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true) && ((this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())]).building == false))) {
					System.out.println("  Maaf, anda tidak bisa pindah ke kanan");
				}
				else {
					(this.pemain).move('d');
					System.out.println("  Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama\n");
				}
			}
			break;

			case'w':
			{
				if ((((this.pemain).getCoord()).getY() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)]).cekIsi == true) && ((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)]).building == false))) {
					System.out.println("  Maaf, anda tidak bisa pindah ke atas");
				}
				else {
					(this.pemain).move('w');
					System.out.println("  Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama\n");
				}
			}
			break;

			case's':
			{
				if ((((this.pemain).getCoord()).getY() + 1) > 28 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() + 1)]).cekIsi == true) && ((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() + 1)]).building == false))) {
					System.out.println("  Maaf, anda tidak bisa pindah ke bawah");
				}
				else {
					(this.pemain).move('s');
					System.out.println("  Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map utama\n");
				}
			}
			break;

			case'e':
			{
				//cek equip tool player
				/*if () {

				}
				else {

				}*/
			}
			break;

			case'm':
			{
				if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isPintu != true) {
					System.out.println("  Anda tidak menghadap ke pintu...");
				}
				else {
					//pindahin player, pake if (kalo di rumah player pindahin ke rumah, jalanin action() rumah dll)
					System.out.println("  Player sudah dipindahkan kedalam ");
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
					System.out.println("  Masukkan koordinat x");
					int x = in.nextInt();
					System.out.println("  Masukkan koordinat y");
					int y = in.nextInt();
					System.out.println("  Masukkan arah hadap (Top, Bottom, Right, Left)");
					String ac = in.nextLine();
					if(((this.mapu[x][y]).building == false) && (this.mapu[x][y].cekIsi == false) && (ac == "Top" || ac == "Bottom" || ac == "Right" | ac == "Left")) {
						(this.pemain).setCoord(x,y);
						(this.pemain).setOrient(ac);
						ulang = false;
					}
					else {
						System.out.println("  Kotak tidak dapat ditempati pemain atau masukan arah hadap salah... Ulangi masukan");
					}
				}
			}
			break;

			case'p':
			{
				//cek status, item, tools player, disini ada pilihan ganti tools juga
			}
			break;

			case'v':
			{
				//save, karena ini di map utama, ga bisa save
				System.out.println("  Maaf, anda tidak bisa tidur disini");
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
				if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).building = true) {
					System.out.println("  Di hadapan anda ada bangunan");
				}
				else if (((((this.pemain).inFrontOf()).getX() <= 29) || (((this.pemain).inFrontOf()).getX() >= 10)) && ((((this.pemain).inFrontOf()).getY() <= 26) || (((this.pemain).inFrontOf()).getY() >= 7))) {
					if (((TileSawah) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
						System.out.println((((TileSawah)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi).getNama());
					}
					else {
						System.out.println("  Di hadapan anda ada petak sawah");
					}
				}
			}
			break;
		}
		this.action(a,b,c);
	}
}
