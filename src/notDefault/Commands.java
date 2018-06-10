package notDefault;

import java.util.ArrayList;

public class Commands {
    public static void commands() {
    	/*testWelcome to Data Seek: does stuff that /ds doesn't™. When you want 
    	to use it, simply type your commands in this method and click run. I 
    	recommend reading the README file first, though. */
    	ds("movepool>=128,!learnsalltypes");
    	dt("mewtwo,movepool,pound");
    	mean("movepool,learnsghosttype");
    	cover("fire,fighting,ground,flying,bug,dark,steel,freeze-dry");
    	bestaddition("3,fire,fighting,ground,flying,bug,dark,steel,freeze-dry");
    }
    
    private static void ds(String in) {
    	/*finds all Pokemon that match a list of criteria
    	 * To search truths, just type the name of the 
    	 * truth. Put a ! in front of the name to negate 
    	 * it. To search a stat, type the name of the 
    	 * stat, one of =, <, >, !=, <=, or >=, and then
    	 * the number. For example, 
    	 * ds("movepool>=128,!learnsalltypes"); prints all
    	 * Pokemon learning at least 128 moves but can't 
    	 * learn a move of every type. */
    	System.out.println("/ds " + in);
    	System.out.println(Main.search(Main.toList(in)));
    }
    
    private static void dt(String in) {
    	/*displays details about a Pokemon
    	 * Type the Pokemon's name followed by a list of 
    	 * things you want to see. Entering a stat 
    	 * prints the number, and entering a truth prints 
    	 * either "true" or "false". For example, 
    	 * dt("mewtwo,movepool,pound"); prints the size 
    	 * of Mewtwo's movepool, followed by whether or 
    	 * not it can learn pound. */
    	System.out.println("/dt " + in);
    	ArrayList<String> list = Main.toList(in);
    	Pokemon pokemon = Main.getPokemon(list.remove(0));
    	String out = "";
    	
    	/*for(int i = 0; i < list.size(); i ++) {
    		System.out.println(list.get(i));
    	}*/
    	
    	for(int x = 0; x < list.size(); x ++) {
    		boolean isTruth = true;
    		
    		for(int y = 0; y < Main.statistics.length; y ++) {
    			//System.out.println(list.get(x) + " " + Main.statistics[y]);
    			
    			if(list.get(x).equals(Main.statistics[y])) {
    				isTruth = false;
    				out = out + pokemon.getStatistic(list.get(x)) + ',';
    			}
    		}
    		
    		if(isTruth) {
    			out = out + pokemon.isTrue(list.get(x)) + ',';
    		}
    	}
    	
    	System.out.println(out);
    }
    
    private static void mean(String in) {
    	/*finds an average stat of all Pokemon meeting certain criteria
    	 * Type the name of the stat you want to average followed 
    	 * by whatever criteria you want. To average all Pokemon, 
    	 * don't type anything after the name of the stat. For 
    	 * example, mean("movepool,learnsghosttype"); prints the 
    	 * movepool size of the average ghost move learner. */
    	System.out.println("/mean " + in);
    	ArrayList<String> searchList = Main.toList(in);
    	String string = searchList.remove(0);
    	ArrayList<Pokemon> foundList = Main.search(searchList);
    	double total = 0.0;
    	
        for(int i = 0; i < foundList.size(); i ++) {
        	total += foundList.get(i).getStatistic(string);
        }
        
        if(foundList.size() > 0) {
            System.out.println("Mean: " + (total / foundList.size()));
        }else {
        	System.out.println("no Pokemon found");
        }
    }
    
    private static void cover(String in) {
    	/*figures out how well an attack type combination covers all Pokemon type combinations
    	 * Type the list of attack types. For example, 
    	 * cover("fire,fighting,ground,flying,bug,dark,steel,freeze-dry"); 
    	 * prints all the type combinations that this 
    	 * attack combination is not very effective, 
    	 * regularly effective, or super effective 
    	 * against. */
    	System.out.println("/cover " + in);
    	System.out.println(new Coverage(Main.toList(in)));
    }
    
    private static void bestaddition(String in) {
    	/*prints a list of types that would cover the most when added to the given attack combination
    	 * Type the number of best types (1st best, 2nd best, etc.) that 
    	 * you want to see, followed by the list of attack types. For 
    	 * example, 
    	 * bestaddition("3,fire,fighting,ground,flying,bug,dark,steel,freeze-dry"); 
    	 * prints the 3 best types for covering stuff when added to that 
    	 * combination, starting with the very best one. */
    	System.out.println("/bestaddition " + in);
    	ArrayList<String> list = Main.toList(in);
    	int additions = Integer.parseInt(list.remove(0));
    	System.out.println((new Coverage(list)).bestAddition(additions));
    }
}
