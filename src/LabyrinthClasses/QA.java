package LabyrinthClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QA {
	
	public ArrayList<String> ques = new ArrayList<String>();
	public ArrayList<String> ans = new ArrayList<String>();
	
	
	 private Position _qPos1;
	 private Position _qPos2;
	 private Position _qPos3;
	 private Position _qPos4;
	 private Position _qPos5;
	
	   private static Position[] qu1 = { new Position(13,11), new Position (6,5), new Position(13,8), new Position(13,8),new Position (12,24)};
	   private Position _qu1r = qu1[(int) (Math.random()*1)];
	    
	   private static Position[] qu2 = {new Position(13,7), new Position(13,22), new Position(10,12), new Position(13,15), new Position(10,12)};
	   private Position _qu2r = qu2[(int) (Math.random()*1)];
	    
	   private static Position[] qu3 = {new Position(8,1), new Position(1,16), new Position(1,18), new Position(4,6), new Position(13,19)};
	   private Position _qu3r = qu3[(int) (Math.random()*1)];
	    
	   private static Position[] qu4 = {new Position(4,7), new Position(1,16), new Position(1,18), new Position(4,6), new Position(13,19)};
	   private Position _qu4r = qu4[(int) (Math.random()*1)];
	    
	   private static Position[] qu5 = {new Position(8,14), new Position(1,16), new Position(1,18), new Position(4,6), new Position(13,19)};
	   private Position _qu5r = qu5[(int) (Math.random()*1)];
	
	
	public void add() {
		ques.add("One");
		ques.add("two");
		ques.add("three");
		ques.add("four");
		ques.add("five");
		ques.add("six");
		ques.add("seven");
		ques.add("eight");
		ques.add("nine");
		ques.add("ten");
		
		ans.add("One");
		ans.add("two");
		ans.add("three");
		ans.add("four");
		ans.add("five");
		ans.add("six");
		ans.add("seven");
		ans.add("eight");
		ans.add("nine");
		ans.add("ten");
		
		long seed = System.nanoTime();
		Collections.shuffle(ques,new Random(seed));
		Collections.shuffle(ans,new Random(seed));
		
	}

	public QA() {
		add();
		_qPos1 = new Position(_qu1r.getRow(),_qu1r.getCol());
		_qPos2 = new Position(_qu2r.getRow(),_qu2r.getCol());
		_qPos3 = new Position(_qu3r.getRow(),_qu3r.getCol());
		_qPos4 = new Position(_qu4r.getRow(),_qu4r.getCol());
		_qPos5 = new Position(_qu5r.getRow(),_qu5r.getCol());

	}
	
	public String getQuestion(int n){
		String s = ques.get(n);
		ques.remove(n);
		return s;
	}
	
	public String getAnswer(int n){
		String s = ans.get(n);
		ans.remove(n);
		return s;
		
	}
	
	public Position q1pos() {
		return _qPos1;
	}
	public Position q2pos() {
		return _qPos2;
	}
	public Position q3pos() {
		return _qPos3;
	}
	public Position q4pos() {
		return _qPos4;
	}
	public Position q5pos() {
		return _qPos5;
	}
	
	public void setPos1(int r, int c) {
		_qPos1 = new Position(r,c);
	}
	public void setPos2(int r, int c) {
		_qPos2 = new Position(r,c);
	}
	public void setPos3(int r, int c) {
		_qPos3 = new Position(r,c);
	}
	public void setPos4(int r, int c) {
		_qPos4 = new Position(r,c);
	}
	public void setPos5(int r, int c) {
		_qPos5 = new Position(r,c);
	}
	
}
