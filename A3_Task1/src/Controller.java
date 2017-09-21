import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    static Scanner reader = new Scanner(System.in);
    int step; // steps to forward
    int action = 0; // action to take
    boolean inputFlag = true;
    boolean displayFlag = false;
    int marker = 0; //
    int direction;
    ArrayList<Tuple> moveStep = new ArrayList<Tuple>();
    int arrayX;
    int arrayY;

    class Tuple {
        int marker = 0;
        int direction = 0;
        int step = 0;

        Tuple(int marker, int direction, int step) {
            this.marker = marker;
            this.direction = direction;
            this.step = step;
        }

    }

    ArrayList processCommand(Playground pg) {
        while (inputFlag) {
            System.out.println("Enter command:");
            action = reader.nextInt();
            takeAction(pg);
        }
        return moveStep;

    }


    public void takeAction(Playground pg) {

        switch (action) {
            case (1):
                marker = 0;
                break;
            case (2):
                marker = 1;
                break;
            case (3):
                changeDirection(1);
                break;
            case (4):
                changeDirection(-1);
                break;
            case (5):
                System.out.println("Enter forward spaces:");
                step = reader.nextInt();
                int tempStep = reachBound(pg);
                forwardSpace(direction, tempStep);
                Tuple t = new Tuple(marker, direction, tempStep);
                moveStep.add(t);
                break;
            case (6):
                displayFlag = true;
                break;
            case (9):
                inputFlag = false;
                break;

        }

    }

    public int reachBound(Playground pg) {
        int len = pg.land.length;

        switch (direction) {
            case (0):
                if (arrayY + step >= len - 1)
                    return len - 1 - arrayY;
                else return step;
            case (1):
                if (arrayX + step >= len - 1)
                    return len - 1 - arrayX;
                else return step;
            case (2):
                if (arrayY - step <= 0)
                    return arrayY;
                else return step;
            case (3):
                if (arrayX - step <= 0)
                    return arrayX;
                else return step;
        }
        return step;
    }

    public void changeDirection(int n) {
        direction = (direction + n) % 4;

    }

    public void forwardSpace(int direction, int step) {
        switch (direction) {
            case (0):
                arrayY += step;
                break;
            case (1):
                arrayX += step;
                break;
            case (2):
                arrayY -= step;
                break;
            case (3):
                arrayX -= step;
                break;
        }


    }
}
