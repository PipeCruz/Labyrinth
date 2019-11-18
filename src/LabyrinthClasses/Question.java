package LabyrinthClasses;

public class Question {
    //instance vars
    private final String question;
    private final String answer;
    private final Position qPos;

    //Question class constructor
    Question(String question, String answer, Position qPos) {
        this.question = question;
        this.answer = answer;
        this.qPos = qPos;
    }

    public String getAnswer() {
        return answer;
    }

    public Position getPos() {
        return qPos;
    }

    //getters
    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Question: " + question
                + "\nAnswer: " + answer + "\n"
                + qPos.toString()
                + "\n";
    }

}
