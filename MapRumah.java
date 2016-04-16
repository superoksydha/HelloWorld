//Eliezer Christanto
//18213020

import java.util.Scanner;
import java.io.*;

public class MapRumah {
	Scanner in = new Scanner(System.in);
	private Tile mapu[][] = new Tile[9][16];
	private int x;
	private int y;
	public Player pemain;

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
				//cek status, item, tools player, disini ada pilihan ganti tools juga (yang bikin inventory player siapa??)
			}
			break;

			case'v':
			{
				try {
					FileOutputStream out = new FileOutputStream("mapRumah.ser");
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(this.mapu);
					oos.close();
				} catch(Exception ex){
					ex.printStackTrace();
				}
				try {
					FileOutputStream out = new FileOutputStream("player.ser");
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(this.pemain);
					oos.close();
				} catch(Exception ex){
					ex.printStackTrace();
				}
				try {
					FileOutputStream out = new FileOutputStream("mapMain.ser");
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(a.mapu);
					oos.close();
				} catch(Exception ex){
					ex.printStackTrace();
				}
				try {
					FileOutputStream out = new FileOutputStream("mapKanA.ser");
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(b.mapu);
					oos.close();
				} catch(Exception ex){
					ex.printStackTrace();
				}	
				try {
					FileOutputStream out = new FileOutputStream("mapKanSD.ser");
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(c.mapu);
					oos.close();
				} catch(Exception ex){
					ex.printStackTrace();
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
				//do shopping (yang bikin inventory siapa??)
			}
			break;
		}
		this.action(a,b,c);
	}
}
