import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;


public class MyStroke implements Stroke
{
    private BasicStroke stroke;

    public MyStroke(int width) {
        stroke = new BasicStroke(width);
    }

    public Shape createStrokedShape(Shape shape) {

        GeneralPath newshape = new GeneralPath();

        float[] coords = new float[2];
        float[] prevCoord = new float[2];

        for(PathIterator i = shape.getPathIterator(null); !i.isDone(); i.next()) {
            int type = i.currentSegment(coords);
            switch(type) {
                case PathIterator.SEG_MOVETO:
                    newshape.moveTo(coords[0], coords[1]);
                    break;
                case PathIterator.SEG_LINETO:
                    double x1 = prevCoord[0];
                    double y1 = prevCoord[1];
                    double x2 = coords[0];
                    double y2 = coords[1];
                    double dx = (x2 - x1) / 2;
                    double dy = (y2 - y1) / 2;
                    newshape.lineTo(x1 + dx, y1 + dy);
                    newshape.lineTo(x2 + dy, y2 - dx);
                    newshape.lineTo(x2, y2);
                    break;
            }

            prevCoord[0] = coords[0];
            prevCoord[1] = coords[1];
        }

        return stroke.createStrokedShape(newshape);
    }
}
