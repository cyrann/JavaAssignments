public class Dog {
    boolean marker = false; // false = up, true = down
    int xTemp = 0;  // xAxis before movement
    int yTemp = 0;  // yAxis before movement
    int xAxis = 0;  // xAxis after movement
    int yAxis = 0;  // yAxis after movement
    int direction = 0;  // 0: right; 1:down; 2:left; 3:up;
    boolean flag = false;





    public void changeDirection(int n){
        direction = (direction + n) % 4;

    }

    public void forwardSpace(int direction, int step){
        xTemp = xAxis;
        yTemp = yAxis;
        switch (direction){
            case(0):
                yAxis += step;
                break;
            case(1):
                xAxis += step;
                break;
            case(2):
                yAxis -= step;
                break;
            case(3):
                xAxis -= step;
                break;

        }


    }
}
