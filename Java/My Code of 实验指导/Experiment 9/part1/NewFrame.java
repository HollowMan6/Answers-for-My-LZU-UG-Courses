import java.awt.*;

class NewFrame extends Frame {
    static final String title = "GUI窗口标题";

    public NewFrame(String s) {
        super(s);
        setSize(200, 200);
        setVisible(true);
        addWindowListener(new WindowCloser());
    }

    public NewFrame() {
        super(title);
    }

    public static void main(String[] args) {
        NewFrame n = new NewFrame("基本GUI编程");
    }
}