import java.util.Scanner;

public class Quiz {
    int quizNumber = 0;
    Scanner reader = new Scanner(System.in);
    Question qArray[] = new Question[25];
    Question wArray[] = new Question[25];
    String aArray[] = new String[25];

    void addQuestion(Question q) {
        qArray[quizNumber] = q;
        quizNumber += 1;
    }

    void giveQuiz() {
        for (int i = 0; i < quizNumber; i++) {
            System.out.println(qArray[i].getQuestion());
            System.out.println("Input your answer:");
            aArray[i] = reader.nextLine();
        }
    }

    void giveQuiz(int lower, int upper) {
        for (int i = 0; i < quizNumber; i++) {
            if (qArray[i].getComplexity() >= lower && qArray[i].getComplexity() <= upper) {
                System.out.println(qArray[i].getQuestion());
                System.out.println("Input your answer:");
                aArray[i] = reader.nextLine();
            }

        }
    }



    void result() {
        int count = 0;
        int wNumber = 0;
        for (int i = 0; i < quizNumber && aArray[i] != null; i++) {
            if (qArray[i].getAnswer().equals(aArray[i])) {
                count += 1;
            }
            else {
                wArray[wNumber] = qArray[i];
                wNumber += 1;
            }
        }
        System.out.println("Correct answers:" + " " + count);
        System.out.println("You answered the following questions incorrectly. The correct answers should be:");
        for (int i = 0; wArray[i] != null; i++) {
            System.out.println(wArray[i].toString());
        }
    }
}
