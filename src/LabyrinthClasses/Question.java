package LabyrinthClasses;

public class Question {
    private String question, answer;
    private Position qPos;

    public String getQuestion(){return question;}
    public String getAnswer(){return answer;}
    public Position getqPos(){return qPos;}

    public Question(String question, String answer, Position qPos){
        this.question=question;
        this.answer=answer;
        this.qPos = qPos;
    }

    @Override
    public String toString(){return "Question: " + question
            +"\nAnswer: " +answer + "\n"
            + qPos.toString()
            + "\n";
    }

}
