package Main;

import GameObjects.GameObject;

/**
 * This class is used to follow the player.
 */
public class Camera {

	private float x, y;
	private float offsetMaxX = 0;
	private float offsetMaxY = 0;

	/**
	 * This constructor is used to initialize the camera instance.
	 * @param x X cord
	 * @param y	Y Cord
	 */
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This method adjusts the camera view every tick.
	 * @param object Takes an GameObject as parameter
	 * @see GameObject
	 */
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

	/**
	 * This method returns the value of the x cord
	 * @return Float for the x cord
	 */
	public float getX() {
		return x;
	}

	/**
	 * This method sets the value of the x cord
	 * @param x Float x for the x cord
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * This method returns the value of the y cord
	 * @return Float for the y cord
	 */
	public float getY() {
		return y;
	}

	/**
	 * This method sets the value of the y cord
	 * @param y Float for the y cord
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * This method is used to calculate the camera bounds based on the mapsize.
	 * @param levelWidth Float with the value of the map width.
	 * @param levelHeight Float with the value of the map height.
	 */
	public void setCameraBounds(float levelWidth, float levelHeight){
		this.offsetMaxX = (levelWidth*32) - 1000;
		this.offsetMaxY = (levelHeight*32) - 563;
	}
}
