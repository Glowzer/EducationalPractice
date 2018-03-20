import java.awt.*;
import java.awt.geom.*;


public class Figure implements Shape{

    private Ellipse2D circle;
    private double centerX, centerY;
    private double radius;


    public Figure(double centerX, double centerY, double r){

        this.centerX = centerX;
        this.centerY = centerY;
        radius = r;
        circle = new Ellipse2D.Double(this.centerX - this.radius, this.centerY - this.radius,
                this.radius*2, this.radius*2);

    }

    public boolean contains(Point2D arg0) {
        return circle.contains(arg0);
    }

    public boolean contains(Rectangle2D arg0) {
        return circle.contains(arg0);
    }

    public boolean contains(double arg0, double arg1) {
        return circle.contains(arg0, arg1);
    }

    public boolean contains(double arg0, double arg1, double arg2, double arg3) {
        return circle.contains(arg0, arg1, arg2, arg3);
    }

    public Rectangle getBounds() {
        return circle.getBounds();
    }

    public Rectangle2D getBounds2D() {
        return circle.getBounds2D();
    }

    public PathIterator getPathIterator(AffineTransform arg0) {
        return new FigureIterator(arg0, 0.1);
    }

    public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
        return new FigureIterator(arg0, arg1);
    }

    public boolean intersects(Rectangle2D arg0) {
        return circle.intersects(arg0);
    }

    public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
        return circle.intersects(arg0, arg1, arg2, arg3);
    }


    class FigureIterator implements PathIterator {

        AffineTransform transform;
        double flatness;
        double angle = -Math.PI/2;
        double startAngle = angle;
        double endAngle = 3*Math.PI/2;
        boolean done = false;
        boolean hyphenIsDone = false;

        public FigureIterator(AffineTransform transform, double flatness) {
            this.transform = transform;
            this.flatness = flatness;
            hyphenIsDone = false;
        }

        public int getWindingRule() { return WIND_NON_ZERO; }

        public boolean isDone() { return done; }

        public int currentSegment(float[] coords) {

            coords[0] = (float) (centerX + radius * Math.cos(angle));
            coords[1] = (float) (centerY - radius * Math.sin(angle));

            if (transform != null) transform.transform(coords, 0, coords, 0, 1);
            if (angle >= endAngle) {
                done = true;
            }

            if (angle == startAngle) return SEG_MOVETO;

            return SEG_LINETO;
        }

        public int currentSegment(double[] coords) {

            coords[0] = centerX + radius * Math.cos(angle);
            coords[1] = centerY - radius * Math.sin(angle);

            if (transform != null) transform.transform(coords, 0, coords, 0, 1);
            if (angle >= endAngle) done = true;
            if (angle == startAngle) return SEG_MOVETO;

            return SEG_LINETO;
        }

        public void next() {
            if (done) return;
            angle += Math.PI / 4*flatness;
        }
    }
}