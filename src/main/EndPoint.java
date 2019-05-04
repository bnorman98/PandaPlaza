package main;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A kijáratot reprezentálja a játékban
 */
public class EndPoint extends Tile {
	/**
	 * A bejárat, ahova az emelet elhagyásakor kerül az állat
	 */
	private Tile startPoint;

	/**
	 * A kijárat konstruktora
	 */
	public EndPoint() {
		startPoint = null;
	}

	/**
	 * A kijárat konstruktora
	 */
	public EndPoint(Tile startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * Visszaadja a bejáratot
	 * @return
	 */
	public Tile getStartPoint() {
		return startPoint;
	}

	/**
	 * A kijárat lép egyet
	 */
	public void stepped() {
		System.out.println("Endpoint.stepped called");
		//Ha a kijáraton nincs állat, akkor nem történik semmi
		if(animal.countFollowers() == 0)
			return;
		//Ellenkező esetben pontot adunk az adott állatnak
		animal.addScore(animal.countFollowers());
		//Elvesszük az állat összes követőjét
		animal.killFollowers();
		//Az állatot a bejáratra tesszük
		animal.goTo(startPoint);
	}

	/**
	 * Szerializálja magát a paraméterként megadott objektumba
	 * @param pw Amibe szerializájuk az objektumot
	 */
	public void writeOut(PrintWriter pw){
		//Println függvénnyel kiírjuk az osztály nevét,
		//tagfüggvényeit és a tagfüggvények értékeit
		pw.println("EndPoint");
		pw.println("-ID: " + this.getID());
		for (int i=0;i<this.getNumOfNeighbours();i++){
			pw.println("-NeighbourID: " + this.getNeighbourAt(i).getID());
		}
		if (this.getAnimal() != null){
			pw.println("-AnimalID: " + this.getAnimal().getID());
		}
		if (this.getThing() != null){
			pw.println("-ThingID: " + this.getThing().getID());
		}
		if (this.getStartPoint() != null){
			pw.println("-StartPointID: " + startPoint.getID());
		}
		pw.println("-life: " + this.getLife());
	}

	/**
	 * Deszerializálja az objektumot
	 * @param lines Szövegsorok, amikből deszerializáljuk az objektumot
	 * @param idx index értéke
	 */
	public void readIn(ArrayList<String> lines, int idx){
		// Az indextől a szövegsorok végéig olvasunk
		for (int i=idx+1;i<lines.size();i++){
			//Space szerint kettéválasztjuk a stringeket
			String[] parts = lines.get(i).split(" ");
			//A '-' karaktert eltávolítjuk az elejéről
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			//switch-casezel beállítjuk a tagváltozókat
			switch (parts[0]){
				case "-StartPointID:":
					this.startPoint = Game.getInstance().getTileContained(Integer.parseInt(parts[1]));
					break;
				case "-NeighbourID:":
					this.addNeighbour(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-ThingID:":
					this.setThing(Game.getInstance().getThingContained(Integer.parseInt(parts[1])));
					break;
				case "-AnimalID:":
					Orangutan o = Game.getInstance().getOrangutanContained(Integer.parseInt(parts[1]));
					if (o != null){
						this.setAnimal(o);
						break;
					}
					Panda p = Game.getInstance().getPandaContained(Integer.parseInt(parts[1]));
					if (p != null){
						this.setAnimal(p);
					}
					break;
				case "-life:":
					this.life = Integer.parseInt(parts[1]);
					break;
				default: break;
			}
		}
	}
	
}
