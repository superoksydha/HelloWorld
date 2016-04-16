public class Jagung extends Tanaman {

	/* METHOD */

	public Jagung(String nama, int x, int y){
		super(nama, "Jagung", 15, x, y);
	}

	@Override
	public void resetDay(){
		super.resetDay();
		//urusin grow dan daytoharvest
		if ((super.getDayToHarvest() == 12) || (super.getDayToHarvest() == 8) || (super.getDayToHarvest() == 4) || (super.getDayToHarvest() == 0)){
			super.grow();
			if (super.isHarvested()){
				super.resetHarvest();
				super.setDayToHarvest(4);
			}
		}
	}
}
