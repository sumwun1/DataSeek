package notDefault;

import java.io.*;
import java.util.ArrayList;
//import java.util.Scanner;

public class Main {
	public static final String[] statistics = {"movepool", "learnedtypes", "hp", "atk", "def", "spa", "spd", "spe", "bst"};
	public static ArrayList<Pokemon> pokedex;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("reading database...please be patient...");
		Coverage.fillChart();
		pokedex = new ArrayList<Pokemon>();
		BufferedReader learnIn = new BufferedReader(new FileReader("learnsets.txt"));
		Pokemon pokemon = new Pokemon(upToColon(learnIn.readLine().trim().toLowerCase()));
		int counter = 0;
		
		while(true) {
			String line = learnIn.readLine().trim().toLowerCase();
			
			if(line == null) {
				break;
			}
			
			if(line.equals("}},")) {
				//pokemon.confirmMoves();
				pokedex.add(pokemon);
				counter ++;
				
				if(counter % 64 == 0) {
					System.out.println("read " + counter + " movepools...");
				}
				
				line = learnIn.readLine();
				
				if(line == null) {
					break;
				}
				
				line = line.trim().toLowerCase();
				pokemon = new Pokemon(upToColon(line));
				continue;
			}
			
			pokemon.addMove(upToColon(line));
		}
		
		/*System.out.println(getPokemon("butterfree").isTrue("shadowball"));
		System.out.println(getPokemon("butterfree").isTrue("learnsghosttype"));*/
		//System.out.println("reading Smeargle's movepool...");
		
		for(int y = 0; y < pokedex.size(); y ++) {
			//System.out.println(pokedex.get(x) + " called sketch() on " + pokedex.get(y));
			getPokemon("smeargle").transferMoves(pokedex.get(y));
		}
		
		getPokemon("rotomheat").transferMoves(getPokemon("rotom"));
		getPokemon("rotomwash").transferMoves(getPokemon("rotom"));
		getPokemon("rotomfrost").transferMoves(getPokemon("rotom"));
		getPokemon("rotomfan").transferMoves(getPokemon("rotom"));
		getPokemon("rotommow").transferMoves(getPokemon("rotom"));
		//FileReader pokedexFile = ;
		System.out.println("reading stats...");
		
		for(int x = 0; x < pokedex.size(); x ++) {
			BufferedReader pokedexIn = new BufferedReader(new FileReader("pokedex.txt"));
			String entry = findEntry(pokedexIn, pokedex.get(x).toString(), "},", false);
			pokedex.get(x).setPrevolution(getPokemon(findQuality(entry, "prevo: \"")));
			ArrayList<String> statsList = toList(findQuality(entry, "baseStats: {"));
			
			for(int y = 0; y < 6; y ++) {
				pokedex.get(x).setStat(upToColon(statsList.get(y)), Integer.parseInt(statsList.get(y).substring(statsList.get(y).indexOf(':') + 1)));
			}
			
			pokedex.get(x).setStat("bst", pokedex.get(x).getStat(2) + pokedex.get(x).getStat(3) + pokedex.get(x).getStat(4) + pokedex.get(x).getStat(5) + pokedex.get(x).getStat(6) + pokedex.get(x).getStat(7));
		}
		
		System.out.println("adding pre-evolution moves...");
		
		for(int i = 0; i < pokedex.size(); i ++) {
			if(pokedex.get(i).getPrevolution() != null) {
			    pokedex.get(i).getPrevolution().transferMoves(pokedex.get(i).getPrevolution().getPrevolution());
			    pokedex.get(i).transferMoves(pokedex.get(i).getPrevolution());
			}
			
			/*if(pokedex.get(i).isTrue("learnsnormaltype") && pokedex.get(i).isTrue("learnsfiretype") 
					&& pokedex.get(i).isTrue("learnswatertype") && pokedex.get(i).isTrue("learnselectrictype") 
					&& pokedex.get(i).isTrue("learnsgrasstype") && pokedex.get(i).isTrue("learnsicetype") 
					&& pokedex.get(i).isTrue("learnsfightingtype") && pokedex.get(i).isTrue("learnspoisontype") 
					&& pokedex.get(i).isTrue("learnsgroundtype") && pokedex.get(i).isTrue("learnsflyingtype") 
					&& pokedex.get(i).isTrue("learnspsychictype") && pokedex.get(i).isTrue("learnsbugtype") 
					&& pokedex.get(i).isTrue("learnsrocktype") && pokedex.get(i).isTrue("learnsghosttype") 
					&& pokedex.get(i).isTrue("learnsdragontype") && pokedex.get(i).isTrue("learnsdarktype") 
					&& pokedex.get(i).isTrue("learnssteeltype") && pokedex.get(i).isTrue("learnsfairytype")) {
				pokedex.get(i).addTruth("learnsalltypes");
			}*/
			
			pokedex.get(i).confirmMoves();
		}
		
		learnIn.close();
		//IntString.commonMoves();
        Commands.commands();
	}
	
	public static String upToColon(String string) {
		return(string.trim().substring(0, string.indexOf(':')));
	}
	
	public static Pokemon getPokemon(String name) {
		if((name == null) || ("".equals(name))) {
			return(null);
		}
		
		for(int i = 0; i < pokedex.size(); i ++) {
			if(pokedex.get(i).toString().equals(name)) {
				return(pokedex.get(i));
			}
		}
		
		System.out.println(name + " is making me return the null pointer!");
		return(null);
	}

	public static ArrayList<String> toList(String string){
		ArrayList<String> out = new ArrayList<String>();
		
		while(string.indexOf(',') >= 0) {
			out.add(string.substring(0, string.indexOf(',')).replace(" ", ""));
			//System.out.println("possible out of bounds: " + string.indexOf(',' + 1));
			string = string.substring(string.indexOf(',') + 1);
		}
		
		out.add(string.replace(" ", ""));
		return(out);
	}
	
	public static int indexOf(String[] array, String string) {
		for(int i = 0; i < array.length; i ++) {
			/*String maybeSubstring = string;
			String maybeSubarray = array[i];
			
			/*if((array.length > 14) && (array.length < 20)) {
				maybeSubstring = string.substring(0, 3);
				maybeSubarray = array[i].substring(0, 3);
			}*/
			
			if(string.equals(array[i])) {
				return(i);
			}
		}
		
		System.out.println(string + " is making me return -1!");
		return(-1);
	}
	
	public static boolean contains(String[] array, String string) {
		for(int i = 0; i < array.length; i ++) {
			if(string.indexOf(array[i]) >= 0) {
				return(true);
			}
		}
		
		return(false);
	}
	
	public static ArrayList<Pokemon> search(ArrayList<String> strings){
		//System.out.println(strings.size());
		Search[] criteria = new Search[strings.size()];
		
		for(int i = 0; i < strings.size(); i ++) {
			criteria[i] = toSearch(strings.get(i));
		}
		
		//System.out.println(criteria[0]);
		ArrayList<Pokemon> out = new ArrayList<Pokemon>();
		
		for(int x = 0; x < pokedex.size(); x ++) {
			boolean found = true;
			//System.out.println(pokedex.get(x).isTrue("lc"));
			
			for(int y = 0; y < criteria.length; y ++) {
				//System.out.println(pokedex.get(x) + " " + criteria[y].find(pokedex.get(x)));
				
				if(!criteria[y].find(pokedex.get(x))) {
					//System.out.println(pokedex.get(x));
					found = false;
					break;
				}
			}
			
			if(found) {
				out.add(pokedex.get(x));
			}
		}
		
		return(out);
	}
	
	public static Search toSearch(String string) {
		Search out = StatSearch.isStatSearch(string);
		//System.out.println(out);
		
		if(out != null) {
			return(out);
		}
		
		return(new TruthSearch(string));
	}
	
	public static String findEntry(BufferedReader input, String name, String end, boolean hasQuote) throws IOException{
		while(true) {
			String line = input.readLine();
			
			if(line == null) {
				System.out.println(name + " wasn't found in a file!");
				return(null);
			}
			
			line = line.trim().toLowerCase();
			//System.out.println(line + " " + isQuote(hasQuote) + name + " " + (line.startsWith(isQuote(hasQuote) + name)));
			if(line.startsWith(isQuote(hasQuote) + name)) {
				String out = "";
				
				while(true) {
					line = input.readLine().trim();
					out = out + line + '\n';
					
					if(end.equals(line)) {
						return(out);
					}
					
					/*if(line.startsWith(quality)) {
						return(line.substring(quality.length(), line.length() - 2).trim().toLowerCase());
					}
					
					//System.out.println("reached2");*/
				}
				//break;
			}
			
			//System.out.println("reached3");
		}
		
		//return(null);
	}
	
	public static String findQuality(String entry, String quality) {
		while(true) {
			/*line = input.readLine().trim();
			out = out + line + '\n';*/
			
			if(!entry.contains("\n")) {
				return(null);
			}
			
		    if(entry.startsWith(quality)) {
				return(entry.substring(quality.length(), entry.indexOf('\n') - 2).trim().toLowerCase());
			}
			
			entry = entry.substring(entry.indexOf('\n')).trim();
		}
	}
	
	public static String isQuote(boolean hasQuote) {
		if(hasQuote) {
			return("\"");
		}
		
		return("");
	}
}
