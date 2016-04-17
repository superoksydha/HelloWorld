/*
Tools terdiri dari (hotkey + name)
1. hand (cost 0)
2. pacul (cost 0)
3. penyiramTanaman (cost 0)
4. arit (cost 0)
5. bijiLobak (cost 50)
6. bijiKol (cost 60)
7. bijiJagung (cost 70)
8. gunting (cost 0)
9. milker (cost 0)
0. move (cost 0)
*/

public class Tools extends Inventory {
	int cost;
	int jumlah;

	public Tools(String name, int cost){
		super(name);
		this.cost = cost;
		this.jumlah = 1;
	}

	//Setiap pembelian biji, jumlahnya bertambah 1
	public void incrementTools(){
		if (this.getName()=="bijiLobak" || this.getName()=="bijiKol" || this.getName()=="bijiJagung") {
			this.jumlah++;
		}
	}
	//Di Class Main nanti dibuat array of tools dengan size 10
	//di awal game, semua tools diinstantiasi
	//pemilihan tools yang dipegang (untuk melakukan action) juga di class Main atau di class Player
}
