public class Waktu extends Thread{
	private static int x = 10; //Waktu lewat dalam detik
	private static int y = 10; //Waktu tambah dalam menit
	public static int jj; //Jam [0-24]
	public static int mm; //Menit [0-59]

	public Waktu(){
		this.jj = 06;
		this.mm = 00;
	}

	public static void setJJ(int jam){
		jj = jam;
	}

	public static void setMM(int mnt){
		mm = mnt;
	}

	public static int getJJ(){
		return jj;
	}

	public static int getMM(){
		return mm;
	}

	public void run(){
		for (;;) {
			try {
				Thread.sleep(this.x*1000);

			} catch (InterruptedException e) {
				System.err.println("Waktu is interrupted");
				return;
			}

			mm = mm + this.y;
			if (mm>59) {
				mm = 00;
				jj++;
			} else if (jj>23) {
					jj = 00;
					mm = 00;
				}

			if ((jj == 03) && (mm == 00)){
				throw new saveException();
			}
		}
	}

	public static void printWaktu() {
		System.out.println("Saat ini pukul " + jj + ":" + mm);
	}

}
