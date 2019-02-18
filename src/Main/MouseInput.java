package Main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput extends MouseAdapter {
	
	public static final int MouseX = 0;
	public static final int MouseY = 0;
	private Handler handler;
	private Camera camera;
	private CalcoJavaGame game; 
	
	public MouseInput(Handler handler, Camera camera, CalcoJavaGame game) {
		this.handler = handler;
		this.camera = camera;
		this.game = game; 
	}
	
	/*public void mousePressed (MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
		handler.setMousePressed(true);
		//return e;
	}*/

	public void mouseClicked(MouseEvent e) {
		handler.setMousePressed(true, (int)(e.getX() + camera.getX()), (int)(e.getY() + camera.getY()));
	}
}
