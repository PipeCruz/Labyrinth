package LabyrinthClasses;

import java.util.ArrayList;
import java.util.Collections;

public class ArrQs {
    //the list of questions already used
    private final ArrayList<Question> usedQuestions;

    public ArrayList<Question> getUsedQuestions() {
        return usedQuestions;
    }

    //the list of questions, their possible positions,
    public ArrQs() {
        ArrayList<Question> allQuestions = new ArrayList<>();
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(16, 11));
        positions.add(new Position(21, 17));
        positions.add(new Position(16,21));
        positions.add(new Position(1,16));
        positions.add(new Position(19,6));
        positions.add(new Position(14,1));
        positions.add(new Position(6,3));
        positions.add(new Position(12,19));
        positions.add(new Position(3,7));
        positions.add(new Position(11,5));
        positions.add(new Position(12,7));
        positions.add(new Position(7,11));
        positions.add(new Position(12,15));
        positions.add(new Position(16,9));
        positions.add(new Position(13,9));
        positions.add(new Position(18,19));
        positions.add(new Position(7,7));
        positions.add(new Position(15,5));
        positions.add(new Position(21, 5));
        positions.add(new Position(1, 21));

        Collections.shuffle(positions);
        Collections.shuffle(positions);

        allQuestions.add(new Question("_________ the pants or I'll _________ you!\n(1 word)","Eliminate",positions.get(0)));
        allQuestions.add(new Question("_____ blue uniform pants","Ugly",positions.get(1)));
        allQuestions.add(new Question("Take out a _____ __ _____!","Sheet Of Paper",positions.get(2)));
        allQuestions.add(new Question("[REDACTED] In F_______!\nUsed by Rico to express controversial ideas\n(complete word)","ortnite",positions.get(3)));
        allQuestions.add(new Question("A very common name Rico yells out:\n_______!","LINDNER",positions.get(4)));
        allQuestions.add(new Question("The bane of every teachers existence\n(singular article of clothing)","Hoodie",positions.get(5)));
        allQuestions.add(new Question("Put on your __!","ID",positions.get(6)));
        allQuestions.add(new Question("Tuck in your _____!","Shirt",positions.get(7)));
        allQuestions.add(new Question("Oye ____!\n(Phrase of Mr.Escobedo, also found in FRC team's name)","meng",positions.get(8)));
        allQuestions.add(new Question("\"As long as you get me my __ bucks.\"\n-Rico\n(answer with number)","50",positions.get(9)));
        allQuestions.add(new Question("(According to Rico)\nThe best teacher at this school is Mr._______\nHint:(Room 3103)","Holbrook",positions.get(10)));
        allQuestions.add(new Question("There are no smart _______ this year!","freshmen",positions.get(11)));
        allQuestions.add(new Question("_____ the dog\nThe reason many disliked this class", "Karel", positions.get(12)));
        allQuestions.add(new Question("__._ ____ per second squared\nWhat rico has to say about gravity", "32.2 feet", positions.get(13)));
        allQuestions.add(new Question("What does Rico always wear the fist day of school?\n(Open House too)\n(Single word)", "Suit", positions.get(14)));
        allQuestions.add(new Question("The Rico games pitts what two colors against each other?\nR__ and ____)","red blue",positions.get(15)));
        allQuestions.add(new Question("What's the acronym for the second project\ngiven to students in their freshman year by Rico? (____)","WBRL",positions.get(16)));
        allQuestions.add(new Question("The classroom 4412 belongs to which Engineering Teacher?\nMr.________","Escobedo",positions.get(17)));
        allQuestions.add(new Question("What number will undoubtedly bring you respect and greatness in AP Computer Science?\nIn a word","Five",positions.get(18)));

        Collections.shuffle(allQuestions);
        Collections.shuffle(allQuestions);

        usedQuestions = new ArrayList<>(12);//12 questions on the map
        for(int i = 0; i < 12; i++){
            usedQuestions.add(allQuestions.get(i));
        }

    }


}
