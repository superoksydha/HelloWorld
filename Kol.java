public class Kol extends Tanaman {

	/* METHOD */

	public Kol(String nama, int x, int y){
		super(nama, Spesies.KOL, 10, x, y);
	}

	@Override
	public void resetDay(){
		super.resetDay();
		//urusin grow dan daytoharvest
		if ((super.getDayToHarvest() == 7) || (super.getDayToHarvest() == 4) || (super.getDayToHarvest() == 0)){
			super.grow();
		}
	}
}
