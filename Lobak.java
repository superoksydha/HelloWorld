public class Lobak extends Tanaman {

	/* METHOD */

	public Lobak(String nama, int x, int y){
		super(nama, "Lobak", 5, x, y);
	}

	@Override
	public void resetDay(){
		super.resetDay();
		//urusin grow dan daytoharvest
		if ((super.getDayToHarvest() == 3) || (super.getDayToHarvest() == 0)){
			super.grow();
		}
	}
}
