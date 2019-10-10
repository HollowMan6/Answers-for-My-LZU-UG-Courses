import javax.swing.*; // For JPanel, etc.
import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.

/** An example of drawing/filling shapes with Java2D in Java 1.2.
 *
 *  From tutorial on learning Java2D at
 *  http://www.apl.jhu.edu/~hall/java/Java2D-Tutorial.html
 *
 *  1998 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 */

public class ShapeExample extends JPanel {
  private Ellipse2D.Double circle =
    new Ellipse2D.Double(10, 10, 350, 350);
  private Rectangle2D.Double square =
    new Rectangle2D.Double(10, 10, 350, 350);

  public void paintComponent(Graphics g) {
    clear(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.fill(circle);
    g2d.draw(square);
  }

  // super.paintComponent clears offscreen pixmap,
  // since we're using double buffering by default.

  protected void clear(Graphics g) {
    super.paintComponent(g);
  }

  protected Ellipse2D.Double getCircle() {
    return(circle);
  }

  public static void main(String[] args) {
    WindowUtilities.openInJFrame(new ShapeExample(), 380, 400);
  }
}