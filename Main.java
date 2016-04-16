import java.util.Scanner;

public class Main {

	public static void main (String args[]) {

		Scanner in = new Scanner (System.in);
		int pil;
		MapMain farm;
		MapRumah home;
		MapKandangA chickencoop;
		MapKandangSD barn;
		Player player;
		String s;
		char c;
		Waktu time;
		boolean dummy;

		System.out.println("  **     **     **     ********   **      ** ********   *****   **********");
		System.out.println("  **     **    ****    **     **   **    **  **       **            **");
		System.out.println("  **     **   **  **   **      **  **    **  **       **            **");
		System.out.println("  *********  **    **  ********     **  **   *****     ******       **");
		System.out.println("  **     **  ********  **    **     **  **   **             **      **");
		System.out.println("  **     ** **      ** **     **     ****    **             **      **");
		System.out.println("  **     ** **      ** **      **     **     ********  ******       **");
		System.out.println("");
		System.out.println("        **      **   **     **       **       ******** **      **");
		System.out.println("         **    **   ****    **       **       **        **    **");
		System.out.println("         **    **  **  **   **       **       **         **  **");
		System.out.println("          **  **  **    **  **       **       *****       ****");
		System.out.println("          **  **  ********  **       **       **           **");
		System.out.println("           ****  **      ** **       **       **          **");
		System.out.println("            **   **      ** ******** ******** ********   **");
		System.out.println("\n");

		System.out.println("                 +-------------------------------------+");
		System.out.println("                 |  Selamat datang di Harvest Valley!  |");
		System.out.println("                 +-------------------------------------+");
		System.out.println("                 | Menu:                               |");
		System.out.println("                 | 1. New game                         |");
		System.out.println("                 | 2. Load game                        |");
		System.out.println("                 | 3. Keluar                           |");
		System.out.println("                 +-------------------------------------+");
		pil = 0;
		while (pil != 3){
			System.out.print("\n  > Pilihan anda : ");
			pil = in.nextInt();
			switch (pil) {
				case 1:
					System.out.print("\n  > Masukkan nama pemain : ");
					s = in.next();
					player = new Player(s, 7, 1);
					farm = new MapMain(player);
					home = new MapRumah(player);
					chickencoop = new MapKandangA(player);
					barn = new MapKandangSD(player);
					time = new Waktu();
					try{
						time.start();
						System.out.println("\n    Selamat datang, " + player.getNama() + "!");
						System.out.println("");
						System.out.println("                       +--------------------------+");
						System.out.println("                       | Pilihan                  |");
						System.out.println("                       +--------------------------+");
						System.out.println("                       | w, a, s, d = gerakan     |");
						System.out.println("                       | e = menggunakan tool     |");
						System.out.println("                       | x = exit                 |");
						System.out.println("                       | m = masuk bangunan       |");
						System.out.println("                       | t = cek waktu            |");
						System.out.println("                       | p = cek status player    |");
						System.out.println("                       | v = save                 |");
						System.out.println("                       | c = cek objek            |");
						System.out.println("                       | n = navigasi koordinat   |");
						System.out.println("                       +--------------------------+");
						farm.action(home, chickencoop, barn);
					} catch (saveException e) {
						if (!((time.getJJ() > 3) && (time.getJJ() < 6))){
							farm.resetDay();
							barn.resetDay();
							chickencoop.resetDay();
						}
					} catch (wakeException e) {
						time.setJJ(06);
						time.setMM(00);
					}
					break	;
				case 2:
				System.out.println("Loading... \n");
					System.out.println("\n");
					//load game
					break;
				case 3:
					System.out.println("\n    Terima kasih. Selamat tinggal!");
					System.exit(0);
					break;
				default:
					System.out.println("\n    Pilihan salah. Mohon diulangi!");
					break;
			}
		}
	}
}
