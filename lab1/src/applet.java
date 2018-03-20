import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.applet.*;
import javax.swing.Timer;


public class applet extends Applet
{
    int centerX;
    int centerY;
    int radius;
    double angle;
    Color bgr;
    Color borderCol;
    Color fillCol;
    float lineWidth;
    Timer mover;
    private Shape sector;

    public applet()
    {
    }

    protected void update()
    {
        paint(getGraphics());
    }

    public void init()
    {
        angle = Math.PI / 3;

        try
        {
            bgr = Color.decode(getParameter("bgr"));
        } catch (Exception e)
        {
            bgr = Color.white;
        }
        try
        {
            radius = Integer.parseInt(getParameter("radius"));
        } catch (Exception e)
        {
            radius = 100;
        }
        try
        {
            centerX = Integer.parseInt(getParameter("centerX"));
        } catch (Exception e)
        {
            centerX = 150;
        }
        try
        {
            centerY = Integer.parseInt(getParameter("centerY"));
        } catch (Exception e)
        {
            centerY = 150;
        }
        try
        {
            borderCol = Color.decode(getParameter("borderCol"));
        } catch (Exception e)
        {
            borderCol = Color.black;
        }
        try
        {
            fillCol = Color.decode(getParameter("fillCol"));
        } catch (Exception e)
        {
            fillCol = Color.green;
        }
        try
        {
            lineWidth = Float.parseFloat(getParameter("lineWidth"));
        } catch (Exception e)
        {
            lineWidth = 1;
        }
        sector = new Sector(centerX, centerY, radius, angle);
        mover = new Timer(50, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AffineTransform at = AffineTransform.getRotateInstance(
                        0.2, centerX, centerY );

                sector = at.createTransformedShape(sector);
                update();
            }
        });
        mover.setRepeats(true);
    }

    @Override
    public void start()
    {
        mover.start();
    }

    @Override
    public void stop()
    {
        mover.stop();
    }

    @Override
    public void paint(Graphics g)
    {

        g.setColor(bgr);
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(fillCol);
        g2d.fill(sector);
        g2d.setColor(borderCol);
        g2d.setStroke(new BasicStroke(lineWidth));
        g2d.draw(sector);
    }

}

