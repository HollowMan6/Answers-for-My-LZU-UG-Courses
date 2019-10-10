import java.awt.event.*;
public class WindowCloser extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}