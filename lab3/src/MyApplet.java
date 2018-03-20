import java.applet.Applet;
import java.awt.*;

public class MyApplet extends Applet{

    private static final long serialVersionUID = 1L;
    private Figure myFigure;

    public void init(){
        myFigure = new Figure(0, 0, 300);
    }

    public void paint(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.translate(getWidth()/2, getHeight()/2);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setStroke(new MyStroke(2));
        graphics2D.draw(myFigure);
    }
}