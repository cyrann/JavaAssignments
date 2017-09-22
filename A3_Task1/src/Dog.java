import java.util.ArrayList;

public class Dog {

    public void move(Controller c, Playground pg) {
        for (int i = 1; i < c.moveStep.size(); i++) { //moveStep.get(1) is [0,[0,0]]
//            int step = c.moveStep.get(i).step;
            boolean marker = c.moveStep.get(i).marker;
            int x0 = c.moveStep.get(i - 1).position.x;
            int x1 = c.moveStep.get(i).position.x;
            int y0 = c.moveStep.get(i - 1).position.y;
            int y1 = c.moveStep.get(i).position.y;
            int temp = 0;
            if(x0 > x1){
                temp = x0;
                x0 = x1;
                x1 = temp;
            }
            if (y0 > y1){
                temp = y0;
                y0 = y1;
                y1 = temp;
            }
            for (int j = x0 ; j < x1 + 1 ; j++) {
                for (int k = y0; k < y1 + 1 ; k++) {
                    if (marker){
                        pg.land[j][k] = 1;
                    }
                }
            }

        }


    }
}
