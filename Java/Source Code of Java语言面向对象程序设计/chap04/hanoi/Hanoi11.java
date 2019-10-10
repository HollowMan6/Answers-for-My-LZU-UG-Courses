/**********************************************************
   Hanoi11.java - Tower of Hanoi puzzle
                  Version 1.1
   by Bob Kirkland
   bob@mazeworks.com

   1.1 - added Timer, Title bar
         removed CC class
**********************************************************/	
import java.applet.* ;
import java.awt.* ;
import java.net.* ;
import java.util.* ;

/******** HANOI11 ********
   main class: initializes applet panel, starts games,
   runs autosolve thread, handles disc moving events
*/	
public class Hanoi11 extends Applet implements Runnable {
   static final Font titleFont=new Font("Helvetica", Font.BOLD, 12),
                     textFont=new Font("Helvetica", Font.PLAIN, 11),
                     monoFont=new Font("Courier", Font.BOLD, 12) ;
   static final int CANVAS_WIDTH=450, CANVAS_HEIGHT=250, TABLE_TOP=225,
                    PEG1=0, PEG2=1, PEG3=2, MIN_DISCS=3, MAX_DISCS=12 ;
   private boolean gameOver ;
   private int sourceDisc, sourcePeg, targetPeg ;
   private String gameStatus ;
   private Panel cpBack, titlePanel ;
   private Label titleLabel ;
   private Board bd ;
   private BoardCanvas bc ;
   private StatusPanel sp ;
   private ControlPanel cp ;
   private Image boardImage ;
   private String boardImageFile = "board.gif" ;
   private Thread solveThread, timer ;

   public void init() {

      // load board image
      MediaTracker tracker = new MediaTracker(this) ;
      URL url = getCodeBase() ;
      boardImage = getImage(url,boardImageFile) ;
      tracker.addImage(boardImage,0) ;
      try { tracker.waitForID(0); }
      catch (InterruptedException e) {}

      // initialize applet layout
      setBackground(Color.black) ;
      Panel mainPanel = new Panel() ;
      mainPanel.setLayout(new BorderLayout(0,0)) ;
      // BoardCanvas
      bc = new BoardCanvas(this) ;
      bc.resize(CANVAS_WIDTH,CANVAS_HEIGHT) ;
      // Title panel
      titlePanel = new Panel() ;
      titlePanel.setLayout(new GridLayout(1,1)) ;
      titlePanel.setBackground(Color.gray) ;
      titlePanel.setFont(titleFont) ;
      titlePanel.add(titleLabel=new Label("   Tower of Hanoi  1.1",Label.LEFT)) ;
      titleLabel.setForeground(Color.white) ;
      // Control Panel
      cpBack = new Panel() ;
      cpBack.setBackground(Color.gray) ;
      cp = new ControlPanel(this) ;
      cpBack.add(cp) ;
      // Status panel
      sp = new StatusPanel(this) ;
      sp.setBackground(Color.gray) ;
      // construct applet panel
      mainPanel.add("Center",bc) ;
      mainPanel.add("North",titlePanel) ;
      mainPanel.add("East",cpBack) ;
      mainPanel.add("South",sp) ;
      add(mainPanel) ;
      validate() ;

      newGame() ;
   }
   // startup
   void newGame() {
      int discs = cp.getDiscs() ;

      System.gc() ;
      gameOver = false ; 
      bd = new Board(discs,this) ;
      bc.drawBoard(bd,boardImage,0,0,0) ;
      sp.setMoveCount(0) ;
      if (cp.isTimerOn()) timer = new Timer(cp) ;
      else cp.setTimer(" ") ;
      if (solveThread == null)
         sp.setStatus("Move all " + discs + " discs to the rightmost peg.") ;
      bc.requestFocus() ;
   }		
   // handle Reset event
   void restartGame() { 
      stop() ; 
      cp.setAutoSolveEnable(true) ;
      newGame() ;
   }
   // kill all threads
   public void stop() {
      if (solveThread!=null) {
         solveThread.stop() ;
         solveThread = null ;
      }
      if (timer!=null) {
         timer.stop() ;
         timer = null ;
      }
   }
   // spawn Autosolve thread
   public void startSolveThread() {
      stop() ;
      solveThread = new Thread(this) ;
      solveThread.start() ;
   }
   // run Autosolve thread
   public void run() {
      newGame() ;
      sp.setStatus("Autosolving ...") ;
      solve(cp.getDiscs(),PEG1,PEG2,PEG3) ;
      sp.setStatus("Finished!") ;
      gameOver = true ;
      solveThread = null ;
      cp.setAutoSolveEnable(true) ;
   }
   // here's the famous algorithm
   void solve(int discs,int source,int aux,int target) {
      if (discs==0) return ;                      // base to end recursion
      solve(discs-1,source,target,aux) ;          // recursive call #1
      bd.moveDisc(source,target) ;                // move disc
      sp.setMoveCount(bd.getMoveCount()) ;        // update display
      bc.drawBoard(bd,boardImage,0,0,0) ;
      try { solveThread.sleep(cp.getDelay()) ; }  // Autosolve delay
      catch (InterruptedException e) {}
      solve(discs-1,aux,source,target) ;          // recursive call #2
   }
   // handle mouse drag event
   void dragDisc(int x,int y) {
      if (!gameOver&&(sourceDisc!=0)) 
         bc.drawBoard(bd,boardImage,sourceDisc,x,y) ;
   }
   // handle mouse down event
   void selectDisc(int x,int y) {
      if (!gameOver&&(solveThread==null)) {
         if ((timer!=null)&&(!timer.isAlive())) timer.start() ;
         sourcePeg = pixelToPeg(x,y) ;
         if (bd.isStartPeg(sourcePeg)) { 
            sourceDisc = bd.getTopDisc(sourcePeg) ;
            bc.drawBoard(bd,boardImage,sourceDisc,x,y) ; 
         }
      }
   }
   // handle mouse up event
   void dropDisc(int x,int y) {
      if (!gameOver&&(sourceDisc!=0)) {
         targetPeg = pixelToPeg(x,y) ;
         if (bd.moveDisc(sourceDisc,sourcePeg,targetPeg)) {
            gameStatus = bd.getBoardStatus() ;
            sp.setMoveCount(bd.getMoveCount()) ;
            if (gameStatus==null) 
               sp.setStatus("The minimum number of moves required is " + 
                             bd.getMinMoves() + ".") ;
            else {
               gameOver = true ; 									
               stop() ;
               sp.setStatus(gameStatus) ;
            }
         }
         bc.drawBoard(bd,boardImage,0,0,0) ;
         sourceDisc = 0 ;
      }
   }
   // conversion for mouse down/up events
   int pixelToPeg(int x,int y) {
      int peg = -1 ;
      if ((y>40)&&(y<TABLE_TOP)) {
         if ((x>50)&&(x<100)) peg = PEG1 ;
         else if ((x>200)&&(x<250)) peg = PEG2 ;
         else if ((x>350)&&(x<400)) peg = PEG3 ;
      }
      return peg ;
   }
}
/******** TIMER ********
	controls Timer thread
*/	
final class Timer extends Thread {
   static final int ONE_SECOND=1000 ;
   private long startTime, rem ;
   private int hours, min, sec ;
   private String sMin, sSec, sTime ;
   private ControlPanel cp ;

   // constructor
   Timer(ControlPanel cp) {
      this.cp = cp ;
      cp.setTimer(setTime(0)) ;
   }
   // run thread
   public void run() {
      startTime = System.currentTimeMillis() ;
      while (true) {
         try { Thread.sleep(ONE_SECOND) ; } 
         catch (InterruptedException e) {}
         cp.setTimer(setTime(System.currentTimeMillis() - startTime)) ;
      }
   }
   // return h:mm:ss string from milliseconds
   String setTime(long millisec) { 
      hours = (int)(millisec/3600000) ;
      rem = millisec - (hours*3600000) ;
      min = (int)(rem/60000) ;
      rem = rem - (min*60000) ;
      sec = (int)(rem/1000) ;      

      sMin = Integer.toString(min) ;
      if (sMin.length()==1) sMin = "0" + sMin ;
      sSec = Integer.toString(sec) ;
      if (sSec.length()==1) sSec = "0" + sSec ;
      sTime = "  " + Integer.toString(hours) + ":" + sMin + ":" + sSec ;
      return sTime ;
   }
}			
/******** BOARD ********
   controls disc positions, rules for moving discs
*/   
final class Board {
   static final int PEGS=3, 
                    DISC_SIZES[][]={ {68,18},{76,16},{84,14},{92,13},{100,12},
                                     {108,12},{112,11},{116,10},{120,9},{124,9} } ;
   private int peg[][], pegTop[]=new int[PEGS], discWidth[] ;
   private int discs, moveCount, minMoves ;
   private Hanoi11 main ;

   // constructor
   Board(int discs,Hanoi11 main) {
      this.discs = discs ;
      this.main = main ;
      peg = new int[discs][PEGS] ;
      // put all the disks on the first peg
      for (int i=0; i<discs; i++) peg[i][main.PEG1] = discs-i ;
      pegTop[main.PEG1] = discs-1 ;
      for (int i=1; i<PEGS; i++) pegTop[i] = -1 ;
      // calculate disc widths
      discWidth = new int[discs] ;
      for (int i=discs-1; i>=0; i--) 
         discWidth[i] = DISC_SIZES[discs-main.MIN_DISCS][0] - 
                        (DISC_SIZES[discs-main.MIN_DISCS][1] * 
                        (discs-1-i)) ;
      moveCount = 0 ;
      // minimum moves is (2**discs)-1
      minMoves = ((int)Math.pow(2.0,discs)) - 1 ;
   }
   void setDisc(int d,int p) { peg[++pegTop[p]][p] = d ; }
   int getDisc(int d,int p) { return peg[d][p] ; }
   int getTopDisc(int p) { return peg[pegTop[p]--][p] ; }
   int getPegTop(int p) { return pegTop[p] ; }
   int getMoveCount() {return moveCount ; }
   int getMinMoves() {return minMoves ; }
   int getDiscWidth(int d) { return discWidth[d-1] ; }
   boolean isStartPeg(int i) { 
      if ((i >= 0)&&(pegTop[i]>=0)) return true ;
      else return false ;
   }
   String getBoardStatus() {
      String status = null ;
      if (pegTop[PEGS-1]==(discs - 1)) {
         if (moveCount==minMoves)   
            status = "Congratulations!" ;
         else status = "You did it!  Now try again, making only " + minMoves + " moves." ;
      }
      return status ;
   }
   // manual move
   boolean moveDisc(int d,int p1,int p2) {
      if ((p1>=0)&&(p2>=0)) {
         // to different peg which is empty or has larger disc
         if ( (p1!=p2)&&((pegTop[p2]<0)||(peg[pegTop[p2]][p2]>d)) ) {
            setDisc(d,p2) ;
            moveCount++ ;
            return true ;
         }
      }
      setDisc(d,p1) ;
      return false ;
   }            
   // AutoSolve move
   void moveDisc(int p1,int p2) {
      setDisc(getTopDisc(p1),p2) ;
      moveCount++ ;
   }            
}
/******** BOARD CANVAS ********
	draws the board using double buffer,
   passes canvas mouse events to main class
*/	
final class BoardCanvas extends Canvas {
   static final int PEG_SPACE=75, DISC_HEIGHT=15 ;
   static final Color COLOR_1=new Color(102,51,0), 
                      COLOR_2=new Color(153,102,0),
                      COLOR_3=new Color(204,153,51),
                      COLOR_4=new Color(255,204,0),
                      COLOR_5=new Color(255,255,204) ;
   private Image bufferImage ;
   private Graphics buffer ;
   private Hanoi11 main ;
   // constructor
   BoardCanvas(Hanoi11 main) { 
      this.main = main ;
   }	
   void drawBoard(Board b,Image boardImage,int dragDisc,int dragX,int dragY) {
      int width=0, disc=0 ;

      if (buffer==null) {
         bufferImage = createImage(main.CANVAS_WIDTH,main.CANVAS_HEIGHT) ;
         buffer = bufferImage.getGraphics() ;
      }
      // draw board
      buffer.drawImage(boardImage,0,0,this) ;
      // draw discs
      for (int p=main.PEG1; p<=main.PEG3; p++) {
         for (int d=0; d<=b.getPegTop(p); d++) {
            disc = b.getDisc(d,p) ;
            if (disc!=0) {
               width = b.getDiscWidth(disc) ;
               drawDisc( (((2*p)+1)*PEG_SPACE)-((int)(width/2)),
                  main.TABLE_TOP-((d+1)*DISC_HEIGHT),width ) ;
            }
         }
      }
      // draw dragged disc
      if (dragDisc!=0) {
         width = b.getDiscWidth(dragDisc) ;
         drawDisc(dragX-(int)(width/2),
                  dragY-(int)(DISC_HEIGHT*.75),width) ;
      }
      repaint() ; 
   }
   // draw single disc 15 pixel height with primitives
   void drawDisc(int x,int y,int width) {
      buffer.setColor(COLOR_3) ;
      buffer.drawLine(x+4,y,x+width-4,y) ;         // 1
      buffer.drawLine(x+2,y+1,x+width-2,y+1) ;     // 2
      buffer.drawRect(x,y+7,width,1) ;             // 8,9
      buffer.setColor(COLOR_4) ;
      buffer.drawLine(x+1,y+2,x+width-1,y+2) ;     // 3
      buffer.drawRect(x,y+5,width,1) ;             // 6,7
      buffer.setColor(COLOR_5) ;
      buffer.drawLine(x+1,y+3,x+width-1,y+3) ;     // 4
      buffer.drawLine(x,y+4,x+width,y+4) ;         // 5
      buffer.setColor(COLOR_2) ;
      buffer.drawRect(x,y+9,width,1) ;             // 10,11
      buffer.drawLine(x+1,y+11,x+width-1,y+11) ;   // 12
      buffer.setColor(COLOR_1) ;
      buffer.drawLine(x+1,y+12,x+width-1,y+12) ;   // 13
      buffer.drawLine(x+2,y+13,x+width-2,y+13) ;   // 14
      buffer.drawLine(x+4,y+14,x+width-4,y+14) ;   // 15
   }
   public void paint(Graphics g) { update(g) ; }
   public void update(Graphics g) { g.drawImage(bufferImage,0,0,this) ; }

   // mouse event handlers
   public boolean mouseDown(Event e,int x,int y) { main.selectDisc(x,y) ; return true ; }
   public boolean mouseDrag(Event e,int x,int y) { main.dragDisc(x,y) ; return true ; }
   public boolean mouseUp(Event e,int x,int y) { main.dropDisc(x,y) ; return true ; }
}
/******** CONTROL PANEL ********
   main UI components
*/	
final class ControlPanel extends Panel {
   static final int MAX_DELAY=1000 ;
   private Panel discsPanel ;
   private Button bDiscsMinus, bDiscsPlus, bReset, bSolve ;
   private Checkbox cbTimer ;
   private TextField tfDiscs, tfTimer ;
   private Scrollbar sbSpeed ;
   private Hanoi11 main ;
   private int discs=main.MIN_DISCS, delay=200 ;

   // constructor
   ControlPanel(Hanoi11 main) {
      this.main = main ;
      setLayout(new GridLayout(8,1,0,3)) ;
      setFont(main.textFont) ;
      // DISCS
      discsPanel = new Panel() ;
      discsPanel.setLayout(new BorderLayout(2,0)) ;
      tfDiscs = new TextField(3) ;
      tfDiscs.setFont(main.monoFont) ;
      tfDiscs.setForeground(Color.black) ;
      tfDiscs.setBackground(Color.lightGray) ;
      tfDiscs.setEditable(false) ;
      setDiscs(discs) ;
      discsPanel.add("West",bDiscsMinus=new Button("-")) ;
      bDiscsMinus.setFont(main.monoFont) ;
      discsPanel.add("Center",tfDiscs) ;
      discsPanel.add("East",bDiscsPlus=new Button("+")) ;
      bDiscsPlus.setFont(main.monoFont) ;
      discsPanel.validate() ;
      // SPEED
      sbSpeed = new Scrollbar(Scrollbar.HORIZONTAL,
                              (MAX_DELAY-delay),0,0,(MAX_DELAY-9)) ;
      sbSpeed.setBackground(Color.lightGray) ;
      sbSpeed.setPageIncrement((int)(MAX_DELAY/10)) ;
      sbSpeed.setLineIncrement((int)(MAX_DELAY/100)) ;
      bSolve = new Button("AUTOSOLVE") ;
      // TIMER
      cbTimer = new Checkbox("   TIMER") ;
      cbTimer.setBackground(Color.gray) ;
      tfTimer = new TextField(10) ;
      tfTimer.setFont(main.monoFont) ;
      tfTimer.setForeground(Color.white) ;
      tfTimer.setBackground(Color.darkGray) ;
      tfTimer.setEditable(false) ;
      // construct panel
      add(new Label("DISCS",Label.CENTER)) ;
      add(discsPanel) ; 
      add(bReset=new Button("RESET")) ;
      add(bSolve=new Button("AUTOSOLVE")) ; 
      add(new Label("SPEED",Label.CENTER)) ;
      add(sbSpeed) ;
      add(cbTimer) ;
      add(tfTimer) ;
      validate() ;
      setPlusMinusEnable() ;
      cbTimer.setState(true) ;
   }
   void setTimerEnable(boolean b) { cbTimer.enable(b) ; }
   void setAutoSolveEnable(boolean b) { bSolve.enable(b) ; }
   void setPlusMinusEnable() { 
      bDiscsPlus.enable(discs<main.MAX_DISCS) ;
      bDiscsMinus.enable(discs>main.MIN_DISCS) ;
   }
   void setDiscs(int i) { 
      String s = Integer.toString(i) ;
      if (s.length()==1) s = "  " + s ;
      else if (s.length()==2) s = " " + s ;
      tfDiscs.setText(s) ;
   }
   void setTimer(String s) { tfTimer.setText(s) ; }
   int getDiscs() { return discs ; } 
   int getDelay() { return delay ; } 
   boolean isTimerOn() { return cbTimer.getState() ; }

   // handle button clicks
   public boolean action(Event e,Object o) { 
      if ("AUTOSOLVE".equals(o)) {
         setAutoSolveEnable(false) ;
         main.startSolveThread() ;
      }
      else {
         if ("+".equals(o)) setDiscs(++discs) ; 
         else if ("-".equals(o)) setDiscs(--discs) ; 
         setPlusMinusEnable() ;
         main.restartGame() ;
      }
      return true ;
   }
   // handle scrollbar
   public boolean handleEvent(Event e) {
      if (e.target instanceof Scrollbar) {
         delay = MAX_DELAY - sbSpeed.getValue() ;
         return true ;
      }
      else return super.handleEvent(e) ;
   }
}
/******** STATUS PANEL ********
   UI components for status messages and move counter
*/   
final class StatusPanel extends Panel {
   private TextField tfStatus, tfMoveCount ;
   private Hanoi11 main ;

   // constructor
   StatusPanel(Hanoi11 main) {
      this.main = main ;
      setFont(main.textFont) ;
      tfStatus = new TextField(50) ;
      tfStatus.setForeground(Color.white) ;
      tfStatus.setBackground(Color.darkGray) ;
      tfStatus.setEditable(false) ;

      tfMoveCount = new TextField(6) ;
      tfMoveCount.setFont(main.monoFont) ;
      tfMoveCount.setForeground(Color.white) ;
      tfMoveCount.setBackground(Color.darkGray) ;
      tfMoveCount.setEditable(false) ;

      add(tfStatus) ;
      add(tfMoveCount) ;
      add(new Label("MOVES")) ;
      validate() ;
   }
   void setMoveCount(int i) { 
      String s=Integer.toString(i) ;

      switch (s.length()) {
         case 1: s = "    " + s ; break ;
         case 2: s = "   " + s ; break ;
         case 3: s = "  " + s ; break ;
         case 4: s = " " + s ; break ;
      }
      tfMoveCount.setText(s) ;
   }
   void setStatus(String s) { tfStatus.setText(s) ; } 
}