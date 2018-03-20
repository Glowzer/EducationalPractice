import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame
{
    public static void main(String[] args) {

        JFrame frame = new JFrame("ScribbleDragAndDrop");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(800, 600);
        final ScribbleDragAndDrop scribblePane = new ScribbleDragAndDrop(90, 200, 200);
        frame.getContentPane().add(scribblePane, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}