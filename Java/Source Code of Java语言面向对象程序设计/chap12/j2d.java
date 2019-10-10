import java.awt.*;
import java.awt.geom.*;
public class j2d extends Frame {
    public static void main(String args[]) { new j2d(); }
    public j2d() {
        setSize(220,180);
        setVisible(true);
        addWindowListener(new WindowDestroyer());
    }
    public void paint(Graphics g) {
        // Obtain a Graphics2D object
        Graphics2D g2 = (Graphics2D)g;
        // Set the rendering quality.
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        // define a linear colour gradient
        GradientPaint gp = new GradientPaint(0, 60, Color.red,
                                             0, 120, Color.yellow);
        Ellipse2D r = new Ellipse2D.Float(30, 60, 160, 60);
        g2.setPaint(gp);
        g2.fill(r);
        // set rotation
        g2.transform(AffineTransform.getRotateInstance(Math.PI/8));
        g2.setFont(new Font("Serif", Font.BOLD, 85));
        g2.setPaint(Color.blue);
        // set compositing rule with transparency
        g2.setComposite(AlphaComposite.getInstance(
                                       AlphaComposite.SRC_OVER, 0.5f));
        g2.drawString("J2D",50,70);
    }
}
