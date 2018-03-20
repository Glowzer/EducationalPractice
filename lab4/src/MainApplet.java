import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplet extends Applet {

    private static final long serialVersionUID = 1L;

    private Figure myFigure;
    private Button print;

    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.translate(getWidth() / 2, getHeight() / 2);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setStroke(new MyStroke(2));
        graphics2D.draw(myFigure);

    }


    public void init() {
        myFigure = new Figure(0, 0, 300);

        print = new Button("Print report");

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintListingPainter.print();
            }
        });
        add(print, "Center");
    }
}