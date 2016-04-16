public class Player {
	private String nama;
	private Koordinat coord;
	public Tools tool; // Tools yang dipilih
	public enum Orientation { // Orientasi dari Player (Player menghadap kemana)
		TOP, BOTTOM, LEFT, RIGHT
	}
	private Orientation orient;
	private Tools[] bagT = new Tools[10];
	private Items[] bagI = new Items[6];
	public int gold;

	public Player (String nama, int x, int y) {
		this.nama = nama;
		this.coord = new Koordinat(x, y);
		this.gold = 0;
	}

	public String getNama(){
		return this.nama;
	}

	public Koordinat getCoord () { // Method untuk mendapatkan koordinat Player
		return this.coord;
	}

	public void selectTool (int i) { // Method untuk memilih tools
		if (this.tool != this.bagT[i]) {
			this.tool = this.bagT[i];
		} else {
			System.out.println ("Tool sudah dipilih!");
		}
	}

	public void move (char c) { // Method untuk mengatur perpindahan Player
		switch (c) {
			case 'w' : {
				this.orient = Orientation.TOP;
				this.coord.setY (this.coord.getY () - 1);

				break;
			}
			case 's' : {
				this.orient = Orientation.BOTTOM;
				this.coord.setY (this.coord.getY () + 1);

				break;
			}
			case 'a' : {
				this.orient = Orientation.LEFT;
				this.coord.setX (this.coord.getX () - 1);

				break;
			}
			case 'd' : {
				this.orient = Orientation.RIGHT;
				this.coord.setX (this.coord.getX () + 1);

				break;
			}
		}
	}

	public Koordinat inFrontOf () { // Mengembalikan koordinat yang ada di depan Player
		Koordinat temp = new Koordinat ();
		switch (this.orient) {
			case TOP : {
				temp.setK ((this.coord.getX ()), (this.coord.getY () - 1));
				break;
			}
			case BOTTOM : {
				temp.setK ((this.coord.getX ()), (this.coord.getY () + 1));
				break;
			}
			case LEFT : {
				temp.setK ((this.coord.getX () - 1), (this.coord.getY ()));
				break;
			}
			case RIGHT : {
				temp.setK ((this.coord.getX () + 1), (this.coord.getY ()));
				break;
			}
		}
		return temp;
	}

	public void setCoord(int a, int b) {
		(this.coord).setX(a);
		(this.coord).setY(b);
	}

	public void setOrient(String a) {
		switch(a) {
			case "Top" : {
				this.orient = Orientation.TOP;
			}
			break;

			case "Bottom" : {
				this.orient = Orientation.BOTTOM;
			}
			break;

			case "Right" : {
				this.orient = Orientation.RIGHT;
			}
			break;

			case "Left" : {
				this.orient = Orientation.LEFT;
			}
			break;
		}
	}

	public Orientation getOrient() {
		return (this.orient);
	}
}
