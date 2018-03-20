import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.RandomAccessFile;


class PrintListingPainter implements Printable
{
    private RandomAccessFile sourceFile;

    private int fontSize = 12;
    private Font font = new Font("Helvetica", Font.PLAIN, fontSize);
    private Color fontColor = Color.BLACK;

    private Color shapeColor = Color.BLACK;

    private int prevPageIndex = 0;
    private long prevFilePointer = 0;

    private boolean isEOF = false;

    private Figure figure;

    private int lineSpacing = 5;

    private boolean tmpLineIsOver = true;
    private String tmpLine = "";

    public PrintListingPainter(String fileName)
    {
        try
        {
            sourceFile = new RandomAccessFile(fileName, "r");
        }
        catch (Exception e)
        {
            isEOF = true;
        }
    }


    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        final int width = (int) pageFormat.getImageableWidth();
        final int height = (int) pageFormat.getImageableHeight();

        FontMetrics fontMetrics;

        g.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());

        try
        {
            if (pageIndex == 0)
            {
                double a = 100;

                Graphics2D g2d = (Graphics2D) g;

                figure = new Figure(width/2, height/4, a);

                String nameFigure = "Four-petal rose";

                g2d.setColor(shapeColor);
                g2d.setStroke(new MyStroke(1));

                g2d.draw(figure);

                g2d.setFont(font);
                g2d.setColor(fontColor);

                fontMetrics = g.getFontMetrics();

                g2d.drawString(nameFigure, (int)(width/2 - fontMetrics.stringWidth(nameFigure)/2), (int)(height/2));

                return Printable.PAGE_EXISTS;
            }

            if (pageIndex != prevPageIndex)
            {
                prevPageIndex = pageIndex;

                if (isEOF)
                    return Printable.NO_SUCH_PAGE;

                prevFilePointer = sourceFile.getFilePointer();
            } else
                sourceFile.seek(prevFilePointer);

            g.setColor(fontColor);
            g.setFont(font);

            int x = 0;
            int y = 0;

            fontMetrics = g.getFontMetrics();

            y += fontSize;

            while (y + fontSize + lineSpacing < pageFormat.getImageableHeight())
            {
                if (tmpLineIsOver)
                {
                    String line = sourceFile.readLine();

                    if (line == null)
                    {
                        isEOF = true;
                        break;
                    }

                    if (fontMetrics.stringWidth(line) < width)
                    {
                        g.drawString(line, x, y);
                        y += fontSize + lineSpacing;
                    }
                    else
                    {
                        tmpLineIsOver = false;

                        for(int i = line.length() - 1; i >= 0; i--)
                        {
                            if (line.charAt(i) == ' ' || line.charAt(i) == '.')
                            {
                                if (fontMetrics.stringWidth(line.substring(0, i)) < width)
                                {
                                    g.drawString(line.substring(0, i), x, y);

                                    y += fontSize + lineSpacing;

                                    tmpLine = line.substring(i, line.length());
                                    break;
                                }
                            }
                        }
                    }

                } else
                {
                    String line = tmpLine;

                    if (fontMetrics.stringWidth(line) < width)
                    {
                        tmpLineIsOver = true;
                        tmpLine = "";

                        g.drawString(line, x, y);

                        y += fontSize + lineSpacing;

                        continue;
                    }

                    for(int i = line.length() - 1; i >= 0; i--)
                    {

                        if (line.charAt(i) == ' ' || line.charAt(i) == '.')
                        {
                            if (fontMetrics.stringWidth(line.substring(0, i)) < width)
                            {
                                g.drawString(line.substring(0, i), x, y);
                                y += fontSize + lineSpacing;

                                tmpLine = line.substring(i, line.length());

                                break;
                            }
                        }
                    }

                }
            }
            return Printable.PAGE_EXISTS;
        }
        catch (Exception e)
        {
            return Printable.NO_SUCH_PAGE;
        }
    }

    public static void print()
    {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        PageFormat page = new PageFormat();

        page.setOrientation(PageFormat.LANDSCAPE);
        printerJob.setPrintable(new PrintListingPainter("D:\\Учёба\\Прога\\УП\\MyTasks\\lab4\\Figure.java"), page);

        PrintRequestAttributeSet parameters = new HashPrintRequestAttributeSet();

        parameters.add(Sides.DUPLEX);

        if (printerJob.printDialog())
        {
            try
            {
                printerJob.print(parameters);
            }
            catch(Exception e)
            {
                System.err.println(e);
            }
        }
    }
}