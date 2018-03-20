import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class Figure implements Shape {

    private double centerX, centerY;
    private double a;

    public Figure(double centerX, double centerY, double a){

        this.centerX = centerX;
        this.centerY = centerY;
        this.a = a;

    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return false;
    }

    public PathIterator getPathIterator(AffineTransform arg0) {
        return new FigureIterator(arg0, 0.1);
    }

    public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
        return new FigureIterator(arg0, arg1);
    }


    class FigureIterator implements PathIterator {

        AffineTransform transform;
        double flatness;

        double angle = -2*Math.PI;
        double startAngle = angle;
        double endAngle = 0;
        boolean done;

        public FigureIterator(AffineTransform transform, double flatness) {
            this.transform = transform;
            this.flatness = flatness;
            done = false;
        }


        public int getWindingRule() { return WIND_NON_ZERO; }


        public boolean isDone() { return done; }

        public int currentSegment(float[] coords) {

            coords[0] = (float) (centerX + 2 * a * Math.sin(angle) * Math.pow(Math.cos(angle),2));
            coords[1] = (float) (centerY - 2 * a * Math.pow(Math.sin(angle), 2) * Math.cos(angle));

            if (transform != null) transform.transform(coords, 0, coords, 0, 1);
            if (angle >= endAngle) done = true;
            if (angle == startAngle) return SEG_MOVETO;

            return SEG_LINETO;
        }

        public int currentSegment(double[] coords) {

            coords[0] = centerX + 2 * a * Math.sin(angle) * Math.pow(Math.cos(angle),2);
            coords[1] = centerY - 2 * a * Math.pow(Math.sin(angle), 2) * Math.cos(angle);

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
