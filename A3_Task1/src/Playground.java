public class Playground {
    int [][] land = new int [25][25];

    void mark(Dog d){
        switch(d.direction){
            case(0):
                for (int i = d.yTemp; i <= d.yAxis ; i++) {
                    land[d.xAxis][i] = 1;
                }
                break;
            case(1):
                for (int i = d.xTemp; i <= d.xAxis ; i++) {
                    land[i][d.yAxis] = 1;
                }
                break;
            case(2):
                for (int i = d.yAxis; i <= d.yTemp ; i++) {
                    land[d.xAxis][i] = 1;
                }
                break;
            case(3):
                for (int i = d.xAxis; i <= d.xTemp ; i++) {
                    land[i][d.yAxis] = 1;
                }
                break;
        }


        }



    void display(){
        for (int i = 0; i < 25 ; i++) {
            for (int j = 0; j < 25 ; j++) {
                if (land[i][j] == 1){
                    System.out.printf("*");
                }
                else System.out.printf(" ");
                }
            System.out.printf("%n");
            }



    }

}
