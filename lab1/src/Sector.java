import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Sector implements Shape
{
    double centerX;
    double centerY;
    double radius;
    double angle;

    public Sector(double centerX, double centerY, double radius, double angle)
    {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.angle = angle;

        if ((radius < 0) || (angle < 0))
            throw new IllegalArgumentException("Radius and angle must be >= 0");
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (centerX - radius),
                (int) (centerY - radius), (int) (2 * radius),
                (int) (2 * radius));
    }

    @Override
    public Rectangle2D getBounds2D()
    {
        return new Rectangle2D.Double(centerX - radius, centerY - radius,
                2 * radius, 2 * radius);
    }

    @Override
    public boolean contains(double x, double y)
    {
        double r = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y)
                * (centerY - y));
        if (r <= radius)
            return true;
        return false;
    }

    @Override
    public boolean contains(Point2D p)
    {
        return contains(p.getX(), p.getY());
    }

    @Override
    public boolean contains(double x, double y, double w, double h)
    {
        return contains(x, y) && contains(x + w, y) && contains(x, y + h)
                && contains(x + w, y + h);
    }

    @Override
    public boolean contains(Rectangle2D r)
    {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public boolean intersects(double x, double y, double w, double h)
    {
        return getBounds().intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r)
    {
        return getBounds().intersects(r);
    }


    @Override
    public PathIterator getPathIterator(AffineTransform at)
    {
        return new SectorIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness)
    {
        return getPathIterator(at);
    }

    class SectorIterator implements PathIterator
    {
        int index = 0;
        boolean done = false;
        double curAngle = 0;
        double angleStep = Math.PI / 30;
        private AffineTransform at;

        public SectorIterator(AffineTransform at)
        {
            this.at = at;
        }

        @Override
        public int getWindingRule()
        {
            return WIND_NON_ZERO;
        }

        @Override
        public boolean isDone()
        {
            return done;
        }

        @Override
        public void next()
        {
            index++;
        }

        @Override
        public int currentSegment(float[] coords)
        {
            if (index == 0)
            {
                coords[0] = (float) (centerX + radius * Math.cos(angle));
                coords[1] = (float) (centerY - radius * Math.sin(angle));
                if (at != null)
                    at.transform(coords, 0, coords, 0, 1);
                return SEG_MOVETO;
            }
            if (index == 1)
            {
                coords[0] = (float) (centerX);
                coords[1] = (float) (centerY);
            } else if (index == 2)
            {
                coords[0] = (float) (centerX + radius);
                coords[1] = (float) (centerY);
            } else
            {
                if (curAngle > angle)
                {
                    curAngle = angle;
                    done = true;
                }
                coords[0] = (float) (centerX + radius * Math.cos(curAngle));
                coords[1] = (float) (centerY - radius * Math.sin(curAngle));
                curAngle += angleStep;

            }
            if (at != null)
                at.transform(coords, 0, coords, 0, 1);
            return SEG_LINETO;
        }

        @Override
        public int currentSegment(double[] coords)
        {
            if (index == 0)
            {
                coords[0] = (centerX + radius * Math.cos(angle));
                coords[1] = (centerY - radius * Math.sin(angle));
                if (at != null)
                    at.transform(coords, 0, coords, 0, 1);
                return SEG_MOVETO;
            }
            if (index == 1)
            {
                coords[0] = (centerX);
                coords[1] = (centerY);
            } else if (index == 2)
            {
                coords[0] = (centerX + radius);
                coords[1] = (centerY);
            } else
            {
                if (curAngle > angle)
                {
                    curAngle = angle;
                    done = true;
                }
                coords[0] = (centerX + radius * Math.cos(curAngle));
                coords[1] = (centerY - radius * Math.sin(curAngle));
                curAngle += angleStep;

            }
            if (at != null)
                at.transform(coords, 0, coords, 0, 1);
            return SEG_LINETO;
        }

    }
}
