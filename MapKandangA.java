//Eliezer Christanto
//18213020

import java.util.Scanner;
import java.io.Serializable;

public class MapKandangA implements Serializable {
	Scanner in = new Scanner(System.in);
	public Tile mapu[][] = new Tile[8][13];
	private int x;
	private int y;
	public Player pemain;
	
	public MapKandangA(Player a){
		this.x = 8;
		this.y = 13;
		for (int i = 0; i <=7; i++) {
			for (int j = 0; j <=12; j++) {
					this.mapu[i][j] = new TileKandangAyam();
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
	
	public void action(MapMain a, MapRumah b, MapKandangSD c) {
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
				if ((((this.pemain).getCoord()).getX() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX() - 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke kiri");
				}
				else {
					(this.pemain).move('a');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang ayam");
				}
			}
			break;
			
			case'd':
			{
				if ((((this.pemain).getCoord()).getX() + 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX() + 1)][(((this.pemain).getCoord()).getY())]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke kanan");
				}
				else {
					(this.pemain).move('d');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang ayam");
				}
			}
			break;
			
			case'w':
			{
				if ((((this.pemain).getCoord()).getY() - 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() - 1)]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke atas");
				}
				else {
					(this.pemain).move('w');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang ayam");
				}
			}
			break;
			
			case's':
			{
				if ((((this.pemain).getCoord()).getY() + 1) < 0 || (((this.mapu[(((this.pemain).getCoord()).getX())][(((this.pemain).getCoord()).getY() + 1)]).cekIsi == true))) {
					System.out.println("Maaf, anda tidak bisa pindah ke bawah");
				}
				else {
					(this.pemain).move('s');
					System.out.println("Player sudah dipindahkan ke (" + ((this.pemain).getCoord()).getX() + "," + ((this.pemain).getCoord()).getY() + ") di map kandang ayam");
				}
			}
			break;
			
			case'e':
			{
				//cek equip tool player
				if ((((this.pemain).tool).getName() != "hand") && (((this.pemain).tool).getName() != "move")) {
					System.out.println("Anda berada dalam kandang, tool yang anda equip tidak ada gunanya disini");
				}
				else {
					if (((this.pemain).tool).getName() != "move") {
						if (((TileKandangAyam)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi != null) {
							switch ((this.pemain).getOrient()) {
								case TOP: {
									((TileKandangAyam)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY() - 1]).isi = ((TileKandangAyam) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi;
								}
								break;
								case BOTTOM: {
									((TileKandangAyam)this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY() + 1]).isi = ((TileKandangAyam) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi;
								}
								break;
								case RIGHT: {
									((TileKandangAyam)this.mapu[((this.pemain).inFrontOf()).getX() + 1][((this.pemain).inFrontOf()).getY()]).isi = ((TileKandangAyam) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi;
								}
								break;
								case LEFT: {
									((TileKandangAyam)this.mapu[((this.pemain).inFrontOf()).getX() - 1][((this.pemain).inFrontOf()).getY()]).isi = ((TileKandangAyam) this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]).isi;
								}
								break;
							}
						}
						else {
							System.out.println("Anda tidak dapat memindahkan objek didepan anda");
						}
					}
					else {
						if ((this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()]) instanceof TileHM) {
							//ambil item dari bag taruh di tile tempat hewan makan
						}
						else {
							System.out.println("Anda tidak dapat menaruh item didepan anda");
						}
					}
				}
			}
			break;
			
			case'm':
			{
					(this.pemain).setCoord(4,16);
					a.pemain = this.pemain;
					this.pemain = null;
					System.out.println("Player sudah dipindahkan ke map utama");
					a.action(b,this,c);
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
					System.out.println("Masukkan arah hadap (Top, Bottom, Right, Left)");
					String an = in.nextLine();
					if(((this.mapu[x][y]).building == false) && (this.mapu[x][y].cekIsi == false) && (an == "Top" || an == "Bottom" || an == "Right" | an == "Left")) {
						this.pemain.setCoord(x,y);
						this.pemain.setOrient(an);
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
				//cek status, item, tools player, disini ada pilihan ganti tools juga
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
				if (!(this.mapu[((this.pemain).inFrontOf()).getX()][((this.pemain).inFrontOf()).getY()] instanceof TileHM)) {
					System.out.println("Anda menghadap petak kosong, tidak ada yang bisa dicek");
				}
				else {
					System.out.println("Ini tempat makan hewan anda");
				}
			}
			break;
		}
		this.action(a,b,c);
	}
}

