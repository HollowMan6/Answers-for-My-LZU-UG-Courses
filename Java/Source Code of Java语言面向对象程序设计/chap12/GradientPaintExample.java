import java.awt.*;

/** An example of gradient fills with Java2D in Java 1.2.
 *
 *  From tutorial on learning Java2D at
 *  http://www.apl.jhu.edu/~hall/java/Java2D-Tutorial.html
 *
 *  1998 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 */

public class GradientPaintExample extends ShapeExample {
  // Red at (0,0), yellow at (175,175), changes gradually between.
  private GradientPaint gradient =
    new GradientPaint(0, 0, Color.red, 175, 175, Color.yellow,
                      true); // true means to repeat pattern

  public void paintComponent(Graphics g) {
    clear(g);
    Graphics2D g2d = (Graphics2D)g;
    drawGradientCircle(g2d);
  }

  protected void drawGradientCircle(Graphics2D g2d) {
    g2d.setPaint(gradient);
    g2d.fill(getCircle());
    g2d.setPaint(Color.black);
    g2d.draw(getCircle());
  }

  public static void main(String[] args) {
    WindowUtilities.openInJFrame(new GradientPaintExample(),
                                 380, 400);
  }
}