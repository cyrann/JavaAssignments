public class QuizTime {


    public static void main(String[] args) {
        Question q1 = new Question("1 + 1 =", "2");
        Question q2 = new Question("2 + 2 =", "4");
        Question q3 = new Question("3 + 3 =", "6");
        Question q4 = new Question("4 + 4 =", "8");
        Question q5 = new Question("5 + 5 =", "10");
        Question q6 = new Question("6 + 6 =", "12");
        Question q7 = new Question("6 * 6 =", "36");
        Question q8 = new Question("7 * 7 =", "49");
        Question q9 = new Question("8 * 8 =", "64");
        Question q10 = new Question("9 * 9 =", "81");
        Question q11 = new Question("10 * 10 =", "100");
        Question q12 = new Question("11 * 11 =", "121");
        q1.setComplexity(1);
        q2.setComplexity(1);
        q3.setComplexity(2);
        q4.setComplexity(2);
        q5.setComplexity(3);
        q6.setComplexity(3);
        q7.setComplexity(4);
        q8.setComplexity(4);
        q9.setComplexity(5);
        q10.setComplexity(5);
        q11.setComplexity(6);
        q12.setComplexity(6);



        Quiz quiz = new Quiz();
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);
        quiz.addQuestion(q5);
        quiz.addQuestion(q6);
        quiz.addQuestion(q7);
        quiz.addQuestion(q8);
        quiz.addQuestion(q9);
        quiz.addQuestion(q10);
        quiz.addQuestion(q11);
        quiz.addQuestion(q12);

        quiz.giveQuiz(1,4);
        quiz.result();

    }


}
