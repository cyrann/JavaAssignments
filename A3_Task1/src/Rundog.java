public class Rundog {
    public static void main(String[] args) {
        Dog Sherlock = new Dog();
        Controller ctl = new Controller();
        Playground pg = new Playground();

        while (ctl.action != 9){
            ctl.input(Sherlock, pg);
            ctl.takeAction(Sherlock, pg);

//
//            System.out.println(Sherlock.xAxis);
//            System.out.println(Sherlock.yAxis);
//            System.out.println(Sherlock.xTemp);
//            System.out.println(Sherlock.yTemp);
//            System.out.println(Sherlock.direction);
            for (int i = 0; i <25 ; i++) {
                for (int j = 0; j <25; j++) {
                    System.out.print(pg.land[i][j]);
                }
                System.out.printf("%n");
            }
        }


    }
}
