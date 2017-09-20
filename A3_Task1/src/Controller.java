import java.util.Scanner;

public class Controller {
    static Scanner reader = new Scanner(System.in);
    int step; // steps to forward
    int action = 0; // action to take
    void input(Dog d, Playground pg){
        System.out.println("Enter command (9 to end input):");
        action = reader.nextInt();
        if (action == 5){
            System.out.println("Enter forward spaces:");
            step = reader.nextInt();
            while (! reachBound(d, pg)){
                System.out.println("Not enough space. Reenter forward spaces:");
                step = reader.nextInt();
                reachBound(d, pg);
            }


        }
    }
    public void takeAction(Dog d, Playground p){

        switch (action){
            case(1):
                d.marker = false;
                break;
            case(2):
                d.marker = true;
                break;
            case(3):
                d.changeDirection(1);
                break;
            case(4):
                d.changeDirection(-1);
                break;
            case(5):
                d.forwardSpace(d.direction, step );
                if (d.marker == true){p.mark(d);}
                break;
            case(6):
                d.flag = true;
                break;
            case(9):
                if(d.flag == true){
                    p.display();
                }
                break;

        }

    }

    public boolean reachBound(Dog d, Playground pg){
        int len = pg.land.length;
        boolean flag = false;

        switch (d.direction){
            case(0):
                if (d.yAxis + step <= len - 1) {flag = true;}
                break;
            case(1):
                if (d.xAxis + step <= len - 1) {flag = true;}
                break;
            case(2):
                if (d.yAxis - step >= 0) {flag = true;}
                break;
            case(3):
                if (d.xAxis - step >= 0) {flag = true;}
                break;
        }
        return flag;
    }


}
