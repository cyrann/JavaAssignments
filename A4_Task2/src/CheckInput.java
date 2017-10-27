import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckInput {
    static int inputInt(){
        boolean flag;
        Scanner input = new Scanner(System.in);
        int i = 0;
        do {
            flag = true;
            try{i = input.nextInt(); flag = false;}
            catch (InputMismatchException e){
                System.out.println("Invalid input. Please input again:");
                input.nextLine();
            }
        }
        while(flag);
        return i;
    }

    static int inputInt(int i1, int i2){
        int i;
        do {
            i = inputInt();
        }
        while (i < i1 || i > i2);
        return i;
    }

    static double inputDouble(){
        boolean flag;
        Scanner input = new Scanner(System.in);
        double d = 0;
        do {
            flag = true;
            try{d = input.nextDouble(); flag = false;}
            catch (InputMismatchException e){
                System.out.println("Invalid input. Please input again:");
                input.nextLine();
            }
        }
        while(flag);
        return d;
    }

    static String inputString(){
        boolean flag;
        Scanner input = new Scanner(System.in);
        String s = null;
        do {
            flag = true;
            try{s = input.nextLine(); flag = false;}
            catch (InputMismatchException e){
                System.out.println("Invalid input. Please input again:");
                input.nextLine();
            }
        }
        while(flag);
        return s;
    }

    static String inputString(String s1, String s2){
        String s = inputString();

        while (!s.equals(s1) && !s.equals(s2)){
            System.out.println("Invalid input. Please input again:");
            s = inputString();
        }
        return s;
    }
}
