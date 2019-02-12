package Main;

import java.awt.Graphics;
import java.util.LinkedList;

import GameObjects.GameObject;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private boolean 
			up = false,
			down = false,
			right = false,
			left = false,
			one = false,
			two = false,
			three = false,
			four = false,
			escape = false;

	private static boolean 
			inventory = false;

	
	
	public void tick(){
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject (GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject (GameObject tempObject) {
		object.remove(tempObject);
	}


	public boolean isUp() {
		return up;
	}


	public void setUp(boolean up) {
		this.up = up;
	}


	public boolean isDown() {
		return down;
	}


	public void setDown(boolean down) {
		this.down = down;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isOne() {
		return one;
	}


	public void setOne(boolean one) {
		this.one = one;
	}


	public boolean isTwo() {
		return two;
	}


	public void setTwo(boolean two) {
		this.two = two;
	}


	public boolean isThree() {
		return three;
	}


	public void setThree(boolean three) {
		this.three = three;
	}


	public boolean isFour() {
		return four;
	}


	public void setFour(boolean four) {
		this.four = four;
	}
	
	public static boolean isInventory() {
		return inventory;
		
	}


	public void setInventory(boolean inventory) {
		Handler.inventory = inventory;
		
	}
	
	public boolean isEscape() {
		return escape;
		
	}
	
	public void setEscape(boolean escape) {
		this.escape = escape;
		
	}

	
		
}

