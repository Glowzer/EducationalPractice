import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class AppletLB extends java.applet.Applet{

    private static final long serialVersionUID = 1L;
    private Color colorBorder;
    private Stroke stroke;
    private Figure myFigure;

    public void drawFigure(Graphics2D graphics2D, Figure figure){

        AffineTransform shadowTransform =
                AffineTransform.getShearInstance(-1.0, 0.0);
        shadowTransform.scale(1.0, 0.5);

        graphics2D.setPaint(Color.pink);
        graphics2D.translate(190,60);
        graphics2D.fill(shadowTransform.createTransformedShape(figure));
        graphics2D.translate(-100,-95);

        graphics2D.setColor(colorBorder);
        graphics2D.draw(figure);
        graphics2D.setPaint(new GradientPaint(140, 120, Color.GRAY,
                250, 120, Color.BLACK));
        graphics2D.fill(figure);

        graphics2D.setColor(colorBorder);
        Font font = new Font("Serif", Font.BOLD, 100);
        graphics2D.setFont(font);
        graphics2D.drawString("-", 175, 140);

    }

    public void drawImage(Graphics2D graphics2D){

        AffineTransform shadowTransform =
                AffineTransform.getShearInstance(-1.0, 0.0);
        shadowTransform.scale(1.0, 0.5);

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        Figure figure = new Figure(image.getWidth()/2, image.getHeight()/2, 70);

        Graphics2D ig = image.createGraphics();

        ig.setPaint(new GradientPaint(0, 0, Color.GRAY,
                getWidth(), getHeight(), Color.BLACK));
        ig.fillRect(0, 0, getWidth(), getHeight());

        ig.setColor(Color.RED);
        ig.setStroke(new BasicStroke(4));
        ig.setFont(new Font("Serif", Font.BOLD, 100));

        ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ig.setPaint(Color.pink);
        ig.translate(60,70);
        ig.fill(shadowTransform.createTransformedShape(figure));
        ig.translate(-85,-84);

        ig.setColor(Color.RED);
        ig.draw(figure);
        ig.setPaint(new GradientPaint(0, 120, Color.GRAY,
                200, 120, Color.BLACK));
        ig.fill(figure);

        ig.setColor(colorBorder);

        ig.drawString("-", image.getWidth()/2 - 20, image.getHeight()/2 + 20);


        graphics2D.drawImage(FiltrBlur.filtrBlur(image), 0, 0, null);

    }

    public void paint(Graphics g){

        Graphics2D graphics2D=(Graphics2D) g;

        graphics2D.setPaint(new GradientPaint(0, 0, Color.GRAY,
                getWidth(), getHeight(), Color.BLACK));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setStroke(stroke);

        drawImage(graphics2D);
        drawFigure(graphics2D, myFigure);

    }

    public void init(){

        this.setSize(400, 300);
        colorBorder = Color.RED;
        stroke = new BasicStroke(4);
        myFigure = new Figure(190, 120, 70);
    }

}