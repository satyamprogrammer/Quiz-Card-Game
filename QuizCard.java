package QuizCardGame;

public class QuizCard {   
    private String question;
    private String answer;
  
    public QuizCard(String q, String a) {
        question = q;
        answer = a;
   }
        
    public void setQuestion(String q) {
       question = q;
    }

    public String getQuestion() {
       return question;
    }

    public void setAnswer(String a) {
       answer = a;
    }

    public String getAnswer() {
       return answer;
    }
}
