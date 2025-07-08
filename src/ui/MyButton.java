package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {

  public int x, y, width, height, id;
  private String text;
  private Rectangle bounds;
  private boolean mouseOver, mousePressed;

  //* Constructor for normal buttons */ 
  public MyButton(String text, int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.text = text;
    this.id = -1; //! Default id for normal buttons
  
    initBounds();
  }

  //* Constructor for tile buttons */
  public MyButton(String text, int x, int y, int width, int height, int id) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.text = text;
    this.id = id; //! Id for tile buttons
  
    initBounds();
  }

  private void initBounds() {
    this.bounds = new Rectangle(x, y, width, height);
  }

  public void draw(Graphics g) {

    //! body
    drawBody(g);
    
    //! Border
    drawBorder(g);
    

    //! Text
    drawText(g);
    
  }

  public void drawExit(Graphics g) {

    //! body
    drawBodyExit(g);
    
    //! Border
    drawBorder(g);
    

    //! Text
    drawText(g);
    
  }

  private void drawBorder(Graphics g) {
      g.setColor(Color.BLACK.brighter());
      g.drawRect(x, y, width, height);
    if(mousePressed) {
      g.drawRect(x + 1, y + 1, width - 2, height - 2);
      g.drawRect(x + 2, y + 2, width - 4, height - 4);
    }
      
  }

  private void drawBody(Graphics g) {
    if(mouseOver)
      g.setColor(Color.WHITE.brighter());
    else
      g.setColor(Color.WHITE.darker());

    g.fillRect(x, y, width, height);

  }

  private void drawBodyExit(Graphics g) {
    if(mouseOver)
      g.setColor(Color.RED.brighter());
    else
      g.setColor(Color.WHITE.darker());

    g.fillRect(x, y, width, height);

  }

  private void drawText(Graphics g) {
    int w = g.getFontMetrics().stringWidth(text);
    int h = g.getFontMetrics().getHeight();
    g.drawString(text, x - w / 2 + width / 2, y + h / 3 + height / 2);
  }

  public void resetBooleans() {
    this.mouseOver = false;
    this.mousePressed = false;
  }

  public void setMousePressed(boolean mousePressed) {
    this.mousePressed = mousePressed;
  }

  public void setMouseOver(boolean mouseOver) {
    this.mouseOver = mouseOver;
  }

  public boolean isMouseOver() {
    return mouseOver;
  }

  public boolean isMousePressed() {
    return mousePressed;
  }

  public Rectangle getBounds() {
    return bounds;
  }

  public int getId() {
    return id;
  }

}
