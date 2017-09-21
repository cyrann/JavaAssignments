import java.util.ArrayList;

public class Dog {
    //    boolean marker = false; // false = up, true = down
//    int xTemp = 0;  // xAxis before movement
//    int yTemp = 0;  // yAxis before movement
    int xAxis = 0;  // xAxis after movement
    int yAxis = 0;  // yAxis after movement
//    int direction = 0;  // 0: right; 1:down; 2:left; 3:up;


    public void move(Controller c, Playground pg) {
        for (int i = 0; i < c.moveStep.size(); i++) {
            int step = c.moveStep.get(i).step;
            int marker = c.moveStep.get(i).marker;
            switch (c.moveStep.get(i).direction) {
                case (0):
                    for (int j = yAxis; j <= yAxis + step ; j++) {
                        pg.land[xAxis][j] = 1 * marker;
                    }
                    yAxis += step;
                    break;
                case (1):
                    for (int j = xAxis; j <= xAxis + step ; j++) {
                        pg.land[j][yAxis] = 1 * marker;
                    }
                    xAxis += step;
                    break;
                case (2):
                    for (int j = yAxis - step; j <= yAxis ; j++) {
                        pg.land[xAxis][j] = 1 * marker;
                    }
                    yAxis -= step;
                    break;
                case (3):
                    for (int j = xAxis - step; j <= xAxis ; j++) {
                        pg.land[j][yAxis] = 1 * marker;
                    }
                    xAxis -= step;
                    break;

            }
        }


    }
}
