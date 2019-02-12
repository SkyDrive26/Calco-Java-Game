package Main;

import GameObjects.GameObject;

public class Camera {

	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject object){
		
		x += ((object.getX() - x) - 1000/2) * 0.05f;			//X-as van de camera, loopt vloeiend achter de speler aan
		y += ((object.getY() - y) - 563/2) * 0.05f;				//Y-as van de camera, loopt vloeiend achter de speler aan
		
		//if(x <= 0) x = 0; 				//Bounds voor de camera, aan te passen per level zodat de camera niet naast het level komt
		//if(x >= 1032) x = 1032;
		//if(y <= 0) y = 0;
		//if(y >= 563+48) y = 563+48;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
