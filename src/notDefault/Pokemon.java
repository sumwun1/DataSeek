package notDefault;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Pokemon {
	private String name;
    private ArrayList<String> truths;
    private ArrayList<String> movepool;
    private int[] statistics;
    private Pokemon prevolution;
    
    public Pokemon(String string) {
    	name = string;
    	truths = new ArrayList<String>();
    	movepool = new ArrayList<String>();
    	statistics = new int[Main.statistics.length];
    }
    
    @Override
    public String toString() {
    	return(name);
    }
    
    public boolean isTrue(String string) {
    	for(int i = 0; i < truths.size(); i ++) {
    		if(truths.get(i).equals(string)) {
    			return(true);
    		}
    	}
    	
    	return(false);
    }
    
    public int getStatistic(String string) {
    	return(statistics[Main.indexOf(Main.statistics, string)]);
    }
    
    public int getStatistic(int index) {
    	return(statistics[index]);
    }
    
    public boolean addTruth(String string) {
    	for(int i = 0; i < truths.size(); i ++) {
    		if(truths.get(i).equals(string)){
    			return(false);
    		}
    	}
    	
    	truths.add(string);
    	return(true);
    }
    
    public boolean addMove(String string) throws Exception{
    	for(int i = 0; i < movepool.size(); i ++) {
    		if(movepool.get(i).equals(string)){
    			return(false);
    		}
    	}
    	
    	//no comments: 27 sec
    	movepool.add(string);
    	Scanner input = new Scanner(new File("movetypes.txt"));
    	
    	while(input.hasNext()) {//11 sec
    		String line = input.nextLine();
    		
    		if(line.startsWith(string + ' ')) {
    			String type = line.substring(line.indexOf(' ') + 1);
    			addTruth("learns" + type + "type");//27 sec
    			break;
    		}
    	}
    	
    	//truths.add(string);
    	statistics[0] = movepool.size();
    	return(true);
    }
    
    public void setStat(String string, int number) {
    	statistics[Main.indexOf(Main.statistics, string)] = number;
    }
    
    public void incrementStat(int index) {
    	statistics[index] ++;
    }
    
    public void transferMoves(Pokemon other) throws Exception{
    	if(other == null) {
    		return;
    	}
    	
    	for(int i = 0; i < other.statistics[0]; i ++) {
    		if(!other.movepool.get(i).equals("sketch") && !other.movepool.get(i).equals("chatter")) {
        		addMove(other.movepool.get(i));
    		}
    	}
    }
    
    public void setPrevolution(Pokemon pokemon) {
    	prevolution = pokemon;
    }
    
    public Pokemon getPrevolution() {
    	return(prevolution);
    }
    
    public void debug() {
    	System.out.println(toString());
    	
    	for(int i = 0; i < statistics[0]; i ++) {
    		System.out.println(movepool.get(i));
    	}
    	
    	System.out.println(prevolution);
    	System.out.println(isTrue("learnswatertype"));
    }
    
    public void confirmMoves() {
    	for(int i = 0; i < movepool.size(); i ++) {
    		truths.add(movepool.get(i));
    	}
    }
}