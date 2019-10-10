public class Screen {
   private final int w, h;
   private char[][] data;
   private int cx, cy, px;

   public Screen(int h, int w) {// 构造方法
      if (w >= 0 && w <= 1000)
         this.w = w;
      else
         this.w = 81;
      if (h >= 0 && h <= 1000)
         this.h = h;
      else
         this.h = 25;
      data = new char[h][w];
      cx = cy = px = 0;
   }

   public void setX(int x) {// 设置打印横坐标
      if (x >= 0 && x < w)
         cx = x;
      px = cx;
   }

   public void setY(int y) {// 设置打印纵坐标
      if (y >= 0 && y < h)
         cy = y;
   }

   public void cls() {// 清屏
      for (int i = 0; i < h; i++) {
         for (int j = 0; j < w; j++) {
            data[i][j] = ' ';
         }
      }
      cx = cy = 0;
   }

   public void print(int times, char ch) {// 实现模拟打印
      for (int i = 0; i < times; i++) {
         data[cy][cx] = ch;
         cx++;
         if (cx > w - 1) {
            cx = 0;
            cy++;
            if (cy > h - 1) {
               cy = h - 1;
            }
         }
      }
   }

   public void println() {// 实现换行
      cy++;
      cx = px;// 利用px实现每次换行后自动移位
   }

   public void display() {// 输出
      for (int i = 0; i < h; i++) {
         for (int j = 0; j < w; j++) {
            System.out.print(data[i][j]);
         }
         System.out.println();
      }
   }

   public void scroll() {// 滚屏
      for (int i = 1; i < h; i++)
         data[i - 1] = data[i];
      data[h - 1] = new char[w];
      for (int i = 0; i < w; i++)
         data[h - 1][i] = ' ';
   }
}