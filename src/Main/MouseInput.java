package Main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private Camera camera;
	private CalcoJavaGame game; 
	
	public MouseInput(Handler handler, Camera camera, CalcoJavaGame game) {
		this.handler = handler;
		this.camera = camera;
		this.game = game; 
	}
	
	public void mousePressed (MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
	}
}
