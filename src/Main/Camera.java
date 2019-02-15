package Main;

import GameObjects.GameObject;

public class Camera {

	private float x, y;
	private float offsetMaxX = 0;
	private float offsetMaxY = 0;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject object){
		
		x += ((object.getX() - x) - 1000/2) * 0.05f;			//X-as van de camera, loopt vloeiend achter de speler aan
		y += ((object.getY() - y) - 563/2) * 0.05f;				//Y-as van de camera, loopt vloeiend achter de speler aan

		/*xx = object.getX() - (1000/2);
		yy = object.getY() - (563/2);*/
		
		if(x <= 0) x = 0; 				//Bounds voor de camera, aan te passen per level zodat de camera niet naast het level komt
		if(x > offsetMaxX) x = offsetMaxX;
		if(y <= 0) y = 0;
		if(y >= offsetMaxY) y = offsetMaxY;
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

	public void setCameraBounds(float levelWidth, float levelHeight){
		this.offsetMaxX = (levelWidth*32) - 1000;
		this.offsetMaxY = (levelHeight*32) - 563;
		System.out.println("levelWidth: "+levelWidth+" levelHeight: "+levelHeight);
		System.out.println("offsetMaxX: "+offsetMaxX+" offsetMaxY: "+offsetMaxY);
	}
}
