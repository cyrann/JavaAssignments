public class Playground {
    int [][] land = new int [25][25];





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
