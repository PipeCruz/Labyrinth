package LabyrinthClasses;

import java.util.ArrayList;
import java.util.Collections;

public class ArrQs {
    private ArrayList<Question> usedQuestions;

    public ArrayList<Question> getUsedQuestions() {
        return usedQuestions;
    }

    public ArrQs(){
        ArrayList<Question> allQuestions = new ArrayList<Question>(12);
        ArrayList<Position> positions = new ArrayList<Position>();
        positions.add(new Position(16,11));//0
        positions.add(new Position(21,17));
        positions.add(new Position(16,21));
        positions.add(new Position(1,16));
        positions.add(new Position(14,1));
        positions.add(new Position(6,3));
        positions.add(new Position(12,19));
        positions.add(new Position(12,17));
        positions.add(new Position(11,5));
        positions.add(new Position(12,7));
        positions.add(new Position(7,11));
        positions.add(new Position(12,15));
        positions.add(new Position(16,9));
        positions.add(new Position(13,9));
        positions.add(new Position(18,19));
        positions.add(new Position(7,7));
        positions.add(new Position(15,5));//16

        Collections.shuffle(positions);

        allQuestions.add(new Question("1","1",positions.get(0)));
        allQuestions.add(new Question("2","2",positions.get(1)));
        allQuestions.add(new Question("3","3",positions.get(2)));
        allQuestions.add(new Question("4","4",positions.get(3)));
        allQuestions.add(new Question("5","5",positions.get(4)));
        allQuestions.add(new Question("6","6",positions.get(5)));
        allQuestions.add(new Question("7","7",positions.get(6)));
        allQuestions.add(new Question("8","8",positions.get(7)));
        allQuestions.add(new Question("9","9",positions.get(8)));
        allQuestions.add(new Question("10","10",positions.get(9)));
        allQuestions.add(new Question("11","11",positions.get(10)));
        allQuestions.add(new Question("12","12",positions.get(11)));
        allQuestions.add(new Question("13","13",positions.get(12)));
        allQuestions.add(new Question("14","14",positions.get(13)));
        allQuestions.add(new Question("15","15",positions.get(14)));
        allQuestions.add(new Question("16","16",positions.get(15)));
        allQuestions.add(new Question("17","17",positions.get(16)));

        Collections.shuffle(allQuestions);

        usedQuestions = new ArrayList<Question>(5);
        for(int i = 0; i < 5; i++){
            usedQuestions.add(allQuestions.get(i));
        }

    }


}
