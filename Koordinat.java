public class Koordinat {
	private int x;
	private int y;

	public Koordinat(){
		this.x = 0;
		this.y = 0;
	}

	public Koordinat(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setK(int x, int y){ //digunakan untuk mengeset koordinat objek
		this.x = x;
		this.y = y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
}
