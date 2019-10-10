import javax.swing.*; 
import javax.swing.tree.*;	
import java.awt.*;
import javax.swing.event.*;
class TestTree extends JFrame implements TreeSelectionListener
{   
    JTree tree;
    public TestTree()
    {
      Container con=getContentPane();
      DefaultMutableTreeNode root=new DefaultMutableTreeNode("面向对象程序设计");
      DefaultMutableTreeNode node=new DefaultMutableTreeNode("java语言");//节点。
      DefaultMutableTreeNode nodeson1=new DefaultMutableTreeNode("C++语言");
      DefaultMutableTreeNode nodeson2=new DefaultMutableTreeNode("SmallTalk语言");
      root.add(node);
      node.add(nodeson1);
      node.add(nodeson2);
      tree=new JTree(root); 
      tree.addTreeSelectionListener(this);
      JScrollPane scrollpane=new JScrollPane(tree);
      con.add(scrollpane);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setBounds(80,80,300,300);
      con.validate();
      validate();
    }
    public void valueChanged(TreeSelectionEvent e)
    {
       DefaultMutableTreeNode node=
           (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
       if(node.isLeaf())
           this.setTitle((node.getUserObject()).toString());
    }
   public static void main(String args[])
    {
      TestTree myapp=new TestTree(); 
    } 
}
