import javax.swing.*;
public class JOptionPaneTest3{
    public static void main(String[] args){
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues={"Pandas","Dogs","Horses"};
        String initialSelection="Dogs";
        Object selection=JOptionPane.showInputDialog(null,
            "What are your favorite animals?","Zoo Quitz",
            JOptionPane.QUESTION_MESSAGE,null,
            selectionValues,initialSelection);
        System.out.println(selection);
    }
}