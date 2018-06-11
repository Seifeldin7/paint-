
package paint;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
enum Style {FREE, LINE, RECT, CIRC }
abstract class Shape{
    protected Color c;
    protected boolean filled;
    protected int x;
    protected int y;
    public Shape(int x,int y,Color c,boolean filled){
        this.x=x;
        this.filled=filled;
        this.c=c;
        this.y=y;
    }
    public abstract void paint(Graphics g);
}
 class Rec extends Shape{
    
    private int w;
    private int h;
    public Rec(int w,int h,Color c,boolean filled,int x,int y){
        super(x,y,c,filled);
        this.w=w;
        this.h=h;
    }
    public  void paint(Graphics g){
        g.setColor(c);
        if(this.filled)
            g.fillRect(x, y, w, h);
        else
            g.drawRect(x, y, w, h);
        
    }
}
class circ extends Shape{
    
    private int w;
    private int h;
    public circ(int w,int h,Color c,boolean filled,int x,int y){
        super(x,y,c,filled);
        this.w=w;
        this.h=h;
    }
    public  void paint(Graphics g){
        g.setColor(c);
        if(this.filled)
            g.fillOval(x, y, w, h);
        else
            g.drawOval(x, y, w, h);
        
    }
}
class Line extends Shape{
    
    private int x2;
    private int y2;
    public Line(int x2,int y2,Color c,boolean filled,int x,int y){
        super(x,y,c,filled);
        this.x2=x2;
        this.y2=y2;
    }
    public  void paint(Graphics g){
        g.setColor(c);
        g.drawLine(x, y, x2, y2);
        
    }
}

class paintframe extends JPanel{
private ArrayList shapes=new ArrayList();
public void addShape(Shape s){
    if(s!=null) shapes.add(s);
}
public void removeShape(Shape s){
    if(s!=null) shapes.remove(s);
}
public void paint(Graphics g){
    super.paint(g);
    for(int i=0;i<shapes.size();i++){
      ((Shape)shapes.get(i)).paint(g);
    }
}
}
public class paint extends JFrame implements ActionListener{
    private Style sty=Style.RECT;
    private JButton Black =new JButton("Black");
    private JButton Blue =new JButton("Blue");
    private JButton Red =new JButton("Red");
    private JButton Green =new JButton("Green");
    private JButton Other =new JButton("Other");
    private JButton erase =new JButton("erase");
    private JButton free =new JButton("free");
    private JButton circle =new JButton("circle");
    private JButton rectangle =new JButton("rectangle");
    private JButton line =new JButton("line");
    private JPanel pnlBottom =new JPanel();
    private JPanel pnlright=new JPanel();
    private paintframe pnlDraw =new paintframe();
    private JPanel pnlcolors =new JPanel();
    private JPanel pnlscroll =new JPanel();
    private JPanel pnlleftlabels =new JPanel();
    private JPanel pnlrighttext =new JPanel();
    private JPanel pnldownbtns =new JPanel();
    private JCheckBox b = new JCheckBox("filled");
    private JMenuBar bar= new JMenuBar();
    private JLabel lblRed = new JLabel("Red");
    private JLabel lblGreen = new JLabel("Green");
    private JLabel lblBlue = new JLabel("Blue");
    private JScrollBar scrRed = new JScrollBar(JScrollBar.HORIZONTAL, 0, 10, 0, 265);
    private JScrollBar scrGreen = new JScrollBar(JScrollBar.HORIZONTAL, 0, 10, 0, 265);
    private JScrollBar scrBlue = new JScrollBar(JScrollBar.HORIZONTAL, 0, 10, 0, 265);
    private JMenu mnuFile = new JMenu("File");
    private JMenu mnuColor = new JMenu("Color");
    private JMenu mnuShape = new JMenu("Shape");
    private JMenu mnuHelp = new JMenu("Help");
    private JTextField txtRed = new JTextField("0", 3);
    private JTextField txtGreen = new JTextField("0", 3);
    private JTextField txtBlue = new JTextField("0", 3);
    private JMenuItem mniNew = new JMenuItem("New");
    private JMenuItem mniOpen = new JMenuItem("Open...");
    private JMenuItem mniSave = new JMenuItem("Save...");
    private JMenuItem mniExit = new JMenuItem("Exit");
    private JMenuItem mniAbout = new JMenuItem("About...");
    private JMenuItem mniFree = new JMenuItem("Free");
    private JMenuItem mniLine = new JMenuItem("Line");
    private JMenuItem mniRect = new JMenuItem("Rectangle");
    private JMenuItem mniCirc = new JMenuItem("Circle");
    private JMenuItem mniBlack = new JMenuItem("Black");
    private JMenuItem mniRed = new JMenuItem("Red");
    private JMenuItem mniGreen = new JMenuItem("Green");
    private JMenuItem mniBlue = new JMenuItem("Blue");
    private JMenuItem mniErase = new JMenuItem("Erase");
    private JMenuItem mniOther = new JMenuItem("Other...");
    private Color colo=Color.BLACK;
    private JPanel pnlSample = new JPanel();
    private Shape oldShape;
    private int x,y;
    
    public paint(){
        this.setBounds(100,100,1000,800);
        
        mnuFile.add(mniNew);
        mnuFile.add(mniOpen);
        mnuFile.add(mniSave);
        mnuFile.add(mniExit);
        mnuShape.add(mniFree);
        mnuShape.add(mniLine);
        mnuShape.add(mniRect);
        mnuShape.add(mniCirc);
        mnuShape.addSeparator(); 
        mnuColor.add(mniBlack);
        mnuColor.add(mniRed);
        mnuColor.add(mniGreen);
        mnuColor.add(mniBlue);
        mnuColor.addSeparator();
        mnuColor.add(mniErase);
        mnuColor.addSeparator();
        mnuColor.add(mniOther);
        mnuHelp.add(mniAbout);
        bar.add(mnuFile);
        bar.add(mnuShape);
        bar.add(mnuColor);
        bar.add(mnuHelp);
        this.setJMenuBar(bar);
        Container c = this.getContentPane();
        pnlBottom.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnlcolors.setLayout(new GridLayout(8,1));
        pnlcolors.add(Blue);
        pnlcolors.add(Black);
        pnlcolors.add(Green);
        pnlcolors.add(Red);
        pnlcolors.add(Other);
        pnlcolors.add(erase);
        pnlcolors.add(b);
        pnlcolors.add(pnlSample);
        pnldownbtns.add(free);
        pnldownbtns.add(line);
        pnldownbtns.add(circle);
        pnldownbtns.add(rectangle);
        pnlrighttext.setLayout(new GridLayout(3,1));
        pnlscroll.setLayout(new GridLayout(3,1));
        pnlleftlabels.setLayout(new GridLayout(3,1));
        
        pnlrighttext.add(txtRed);
        pnlrighttext.add(txtGreen);
        pnlrighttext.add(txtBlue);
        pnlscroll.add(scrGreen);
        pnlscroll.add(scrRed);
        pnlscroll.add(scrBlue);
        pnlleftlabels.add(lblRed);
        pnlleftlabels.add(lblGreen);
        pnlleftlabels.add(lblBlue);
        pnlBottom.add(pnlrighttext,BorderLayout.EAST);
        pnlBottom.add(pnlleftlabels,BorderLayout.WEST);
        pnlBottom.add(pnlscroll);
        c.add(pnlBottom,BorderLayout.SOUTH);
        c.add(pnlDraw);
        pnlright.setLayout(new GridLayout(2,1));
        pnlDraw.setBackground(Color.WHITE);
        pnldownbtns.setLayout(new GridLayout(4,1));
        pnlright.add(pnlcolors);
        pnlright.add(pnldownbtns);
        c.add(pnlright,BorderLayout.EAST);
        Black.addActionListener(this);
        Blue.addActionListener(this);
        Green.addActionListener(this);
        Red.addActionListener(this);
        Other.addActionListener(this);
        free.addActionListener(this);
        circle.addActionListener(this);
        rectangle.addActionListener(this);
        line.addActionListener(this);
        
          
         pnlDraw.addMouseListener(new MouseAdapter(){
             public void mousePressed(MouseEvent e){
                 x=e.getX();
                 y=e.getY();
             }
             @Override
             public void mouseReleased(MouseEvent e){
                 int x2=e.getX();
                 int y2=e.getY();
                 Shape s =null;
                 switch(sty){
                     case RECT:
                           s =new Rec(Math.abs(x2-x),Math.abs(y2-y),colo,b.isSelected(),Math.min(x,x2),Math.min(y,y2));
                     break;
                      case LINE:
                         s=new Line(x2,y2,colo,true,x,y);
                     break;
                     case FREE:
                         break;
                     case CIRC:
                         s =new circ(Math.abs(x2-x),Math.abs(y2-y),colo,b.isSelected(),Math.min(x,x2),Math.min(y,y2));
                         break;
                     
                 }
                 pnlDraw.addShape(s);
                
             }});
         pnlDraw.addMouseMotionListener(new MouseAdapter(){
                 @Override
             public void mouseDragged(MouseEvent e){
                 int x2=e.getX();
                 int y2=e.getY();
                 Shape s ;
                 switch(sty){
                     case RECT:
                           s =new Rec(Math.abs(x2-x),Math.abs(y2-y),colo,b.isSelected(),Math.min(x,x2),Math.min(y,y2));
                           pnlDraw.addShape(s);
                           pnlDraw.removeShape(oldShape);
                           oldShape=s;
                     break;
                     case LINE:
                         s=new Line(x2,y2,colo,true,x,y);
                         pnlDraw.addShape(s);
                         pnlDraw.removeShape(oldShape);
                         oldShape=s;
                     break;
                     case CIRC:
                         s =new circ(Math.abs(x2-x),Math.abs(y2-y),colo,b.isSelected(),Math.min(x,x2),Math.min(y,y2));
                          pnlDraw.addShape(s);
                         pnlDraw.removeShape(oldShape);
                         oldShape=s;
                         break;
                    case FREE:
                        s=new Line(x2,y2,colo,true,x,y);
                        pnlDraw.addShape(s);
                        x=x2;
                        y=y2;
                        
                         break;
                 }
                 pnlDraw.repaint();
                }
                });
         scrRed.addAdjustmentListener(new AdjustmentListener(){
            
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
               int r=scrRed.getValue();
               int b=scrBlue.getValue();
               int g=scrGreen.getValue();
               Color c =new Color(r,g,b);
               setColor(c);
            }
         });
          scrGreen.addAdjustmentListener(new AdjustmentListener(){
            
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
               int r=scrRed.getValue();
               int b=scrBlue.getValue();
               int g=scrGreen.getValue();
               Color c =new Color(r,g,b);
               setColor(c);
            }
         });
          scrBlue.addAdjustmentListener(new AdjustmentListener(){
            
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
               int r=scrRed.getValue();
               int b=scrBlue.getValue();
               int g=scrGreen.getValue();
               Color c =new Color(r,g,b);
               setColor(c);
            }
         });
       
    }
    public static void main(String[] args) {
       paint m = new paint();
       m.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object o = e.getSource();
       if(o==Black){
           colo=Color.BLACK;
           setColor(colo);
       }
       else if(o==Blue){
           colo=Color.BLUE;
           setColor(colo);
       }
        else if(o==Red){
           colo=Color.RED;
           setColor(colo);
       
        }
         else if(o==Green){
           colo=Color.GREEN;
           setColor(colo);
       }
        else if(o==erase){
           colo=pnlDraw.getBackground();
           setColor(colo);
       }
        else if(o==Other){
           colo=JColorChooser.showDialog(null, "Choose A Color", colo);
           if(colo!=null)
                    setColor(colo);   
       }
       else if(o==rectangle){
           sty =Style.RECT;
       }
       else if(o==free){
           sty =Style.FREE;
       }
       else if(o==circle){
           sty =Style.CIRC;
       }
       else if(o==line){
           sty =Style.LINE;
       }
       
    }
   
    public void setColor(Color c){
           int g=c.getGreen();
           int r=c.getRed();
           int b=c.getBlue();
           txtRed.setText(""+r);
           txtGreen.setText(""+g);
           txtBlue.setText(""+b);
           scrRed.setValue(r);
           scrGreen.setValue(g);
           scrBlue.setValue(b);
           pnlSample.setBackground(c);
           
    }

    
    
  
    
}
