/* Items berupa
telur
susu
wool
lobak
kol
jagung
*/

public class Items extends Inventory{
	int sellPrice;
	
	public Items(String name, int sellPrice){
		super(name);
		this.sellPrice = sellPrice;
	}
	
	//Di kelas Main, dibuat array of items dengan size 12
	
}
