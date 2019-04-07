/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsu.fpmi.educational_practice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Glowzer-PC
 */
public class Line extends Canvas{
    
    private int widthline;
    private Color color;
    
    public Line(){
        widthline = 30;
        this.color = Color.BLACK;
        repaint();
    }


    public Line(int width, Color color) {
        widthline = width;
        this.color = color;
        repaint();
    }

    public int getWidthLine(){
        return widthline;
    }
    public Color getColor() {
        return color;
    }

    public void setWidthLine(int width) {
        widthline = width;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    
        public void paint(Graphics g) 
    {
        
        g.setColor(color);
        g.fillRect(0, 0,getWidth(), widthline);
    }
    
    public Dimension getPreferredSize() 
    {
        return new Dimension(widthline, 5);
    }    
    
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }
}
