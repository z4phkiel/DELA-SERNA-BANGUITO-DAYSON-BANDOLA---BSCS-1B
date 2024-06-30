/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dcitpaint;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DcitPaint extends Frame {
    private int x = -1, y = -1; 
    private Image img;
    private Graphics imgGraphics;
    private Color currentColor = Color.BLACK; 
    private final int eraserSize = 20; 
    private boolean eraserActive = false; 

    public DcitPaint() {
        super("Simple Paint Program");
        setSize(800, 600);

        
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        imgGraphics = img.getGraphics();
        imgGraphics.setColor(Color.WHITE);
        imgGraphics.fillRect(0, 0, 800, 600);

        
        Panel colorPanel = new Panel();
        colorPanel.setLayout(new FlowLayout());

        
        Button blackButton = new Button("Black");
        Button redButton = new Button("Red");
        Button greenButton = new Button("Green");
        Button blueButton = new Button("Blue");
        Button yellowButton = new Button("Yellow");
        Button eraseButton = new Button("Erase");
        Button clearButton = new Button("Clear"); 

      
        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.YELLOW;
                eraserActive = false;
            }
        });
        
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.BLACK;
                eraserActive = false;
            }
        });

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.RED;
                eraserActive = false;
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.GREEN;
                eraserActive = false;
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.BLUE;
                eraserActive = false;
            }
        });

        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraserActive = true;
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgGraphics.setColor(Color.WHITE);
                imgGraphics.fillRect(0, 0, 800, 600);
                repaint(); 
            }
        });

       
        colorPanel.add(blackButton);
        colorPanel.add(redButton);
        colorPanel.add(greenButton);
        colorPanel.add(blueButton);
        colorPanel.add(yellowButton);
        colorPanel.add(eraseButton);
        colorPanel.add(clearButton); 

        
        add(colorPanel, BorderLayout.SOUTH);

        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = -1;
                y = -1;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (x != -1 && y != -1) {
                    int newX = e.getX();
                    int newY = e.getY();
                    if (eraserActive) {
                        imgGraphics.setColor(Color.WHITE);
                        imgGraphics.fillRect(newX - eraserSize / 5, newY - eraserSize / 5, eraserSize, eraserSize);
                    } else {
                        imgGraphics.setColor(currentColor);
                        imgGraphics.drawLine(x, y, newX, newY);
                    }
                    x = newX;
                    y = newY;
                    repaint(); 
                }
            }
        });

       
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true); 
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this); 
    }

    public static void main(String[] args) {
        new DcitPaint();
    }
}
