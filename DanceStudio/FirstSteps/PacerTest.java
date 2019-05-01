import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class PacerTest extends JPanel
{
  private Image shoeLeft;
  private Image shoeRight;
  // Constructor
  public PacerTest()
  {
    shoeLeft = (new ImageIcon("leftshoe.gif")).getImage();
    shoeRight = (new ImageIcon("rightshoe.gif")).getImage();
  }

  // Called automatically when the panel needs repainting
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int x = 100;
    int y = 350;
    int stepLength = 100;

    Foot foot = new Foot(x, y, shoeLeft);
    Foot foot1 = new Foot(x,y+50, shoeRight);
	//foot.turn(-90);
    for (int count = 1; count <= 4; count++)
    {
    	//foot.turn(-90);
      	foot.draw(g);
      	//foot.turn(90);
      	foot.moveForward(350-stepLength);
      	foot.turn(-90);
      	foot1.draw(g);
      	foot1.moveForward(350-(stepLength-50));
      	foot1.turn(-90);
      	foot1.moveForward(stepLength/2);
    }

    // Draw a cursor at the expected center of the first "shoe":
    g.drawLine(x - 50, y, x + 50, y);
    g.drawLine(x, y - 50, x, y + 50);
  }
  public static void main(String[] args)
  {
    JFrame window = new JFrame("Feet");
    window.setBounds(100, 100, 500, 480);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    PacerTest panel = new PacerTest();
    panel.setBackground(Color.WHITE);
    Container c = window.getContentPane();
    c.add(panel);

    window.setVisible(true);
  }
}