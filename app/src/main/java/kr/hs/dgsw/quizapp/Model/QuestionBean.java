package kr.hs.dgsw.quizapp.Model;

public class QuestionBean {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;

    private int id;
    private String question;
    private int score;
    private int answer; // 1, 2, 3, 4 중 하나
    private String[] choices;
    private int type;

    public QuestionBean(){
        choices = new String[4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
