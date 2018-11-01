package notDefault;

import java.util.ArrayList;

public class Coverage implements Comparable<Coverage>{
	public static String[] defenseNames = {"normal", "fire", "water", "electric", "grass", "ice", "fighting", "poison", "ground", 
			"flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy"};
	public static String[] attackNames = {"normal", "fire", "water", "electric", "grass", "ice", "fighting", "poison", "ground", 
			"flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy", "freeze-dry"};
	public static double[][] chart2 = new double[17][17];
	public static double[][] chart6 = new double[19][18];
	public static double[][] chart = chart6;
    private ArrayList<String> types;
    private int[] stats = new int[3];
    private ArrayList<String> notVery = new ArrayList<String>();
	private ArrayList<String> regular = new ArrayList<String>();
	private ArrayList<String> supe = new ArrayList<String>();
    
    public Coverage(ArrayList<String> input) {
    	/*for(int i = 0; i < input.length; i ++) {
    		System.out.println(input[i]);
    	}*/
    	
    	types = copyList(input);
    	//System.out.println(chart6[0].length + " " + chart6.length);
    	
    	for(int i = 0; i < chart[0].length; i ++) {
			for(int j = 0; j < chart[0].length; j ++) {
				if(i > j) {
					continue;
				}
				
				double maxEffectiveness = 0.0;
				String comboName = defenseNames[i] + " " + defenseNames[j];
				
				if(i == j){
					comboName = defenseNames[i];
					
					for(String type : types) {
						//System.out.println(type);
						if(indexOf(attackNames, type) < chart.length) {
							//System.out.println(type);
						    maxEffectiveness = Math.max(maxEffectiveness, chart[indexOf(attackNames, type)][i]);
						    
						    /*if("freeze-dry".equals(type)) {
						    	System.out.println(" " + chart[indexOf(attackNames, type)][i]);
						    }*/
						}
					}
				}else {
					for(String type : types) {
						//System.out.println(getIndex(attackNames, type) + " " + chart[0].length);
						if(indexOf(attackNames, type) < chart.length) {
						    maxEffectiveness = Math.max(maxEffectiveness, 
								chart[indexOf(attackNames, type)][i] * chart[indexOf(attackNames, type)][j]);
						}
					}
				}
				
				if(maxEffectiveness < 1.0) {
					notVery.add(comboName);
				}else if(maxEffectiveness == 1.0) {
					regular.add(comboName);
				}else{
					supe.add(comboName);
				}
			}
		}
    	
    	stats[0] = notVery.size();
    	stats[1] = regular.size();
    	stats[2] = supe.size();
    }
    
    public String bestAddition(int additions) {
    	ArrayList<Coverage> potentialCombos = new ArrayList<Coverage>();
    	ArrayList<String> potentialTypes = new ArrayList<String>();
    	
    	for(int i = 0; i < types.size(); i ++) {
    		potentialTypes.set(i, types.get(i));
    	}
    	
    	for(int x = 0; x < attackNames.length; x ++) {
    		potentialTypes = copyList(potentialTypes);
    		potentialTypes.set(types.size(), attackNames[x]);
    		potentialCombos.add(new Coverage(potentialTypes));
    		
    		/*for(int y = 0; y < additions; y ++) {
    			if(potentialCombo.compareTo(bestAdditions[y]) > 0) {
    			    shift(bestAdditions, potentialCombo, y);
    			    break;
    		    }
    		}*/
    	}
    	
    	ArrayList<ArrayList<Coverage>> bestAdditions = bestCombos(potentialCombos, additions);
    	String out = "";
    	
    	for(int x = 0; x < additions; x ++) {
    		for(int y = 0; y < bestAdditions.get(x).size(); y ++) {
    			out = out + differTypes(bestAdditions.get(x).get(y));
    		}
    		
    		out = out + '\n';
    	}
    	
    	return(out);
    }
    
    public static void shift(Object[] array, Object insert, int index) {
    	for(int i = array.length - 1; i > index; i --) {
    		array[i] = array[i - 1];
    	}
    	
    	array[index] = insert;
    }
    
    public static void shift(int[] array, int insert, int index) {
    	for(int i = array.length - 1; i > index; i --) {
    		array[i] = array[i - 1];
    	}
    	
    	array[index] = insert;
    }
    
    /*public String bestAddition2() {
    	int minNotVery = 170;
    	int minNotVery2 = 171;
    	int maxSuper = 1;
    	int maxSuper2 = 0;
    	String bestType = "normal";
    	String bestType2 = "fire";
    	String[] potentialTypes = new String[types.length + 1];
    	
    	for(int i = 0; i < types.length; i ++) {
    		potentialTypes[i] = types[i];
    	}
    	
    	for(String type : attackNames) {
    		potentialTypes[types.length] = type;
    		Coverage potentialCombo = new Coverage(potentialTypes);
    		
    		if(potentialCombo.effectivenesses[0] < minNotVery) {
    			bestType2 = bestType;
    			minNotVery2 = minNotVery;
    			maxSuper2 = maxSuper;
    			bestType = type;
    			minNotVery = potentialCombo.effectivenesses[0];
    			maxSuper = potentialCombo.effectivenesses[2];
    		}else if(potentialCombo.effectivenesses[0] == minNotVery && (potentialCombo.effectivenesses[2] > maxSuper)) {
    			bestType2 = bestType;
        		minNotVery2 = minNotVery;
        		maxSuper2 = maxSuper;
        		bestType = type;
        		minNotVery = potentialCombo.effectivenesses[0];
                maxSuper = potentialCombo.effectivenesses[2];
    		}else if(potentialCombo.effectivenesses[0] < minNotVery2) {
    			bestType2 = type;
    			minNotVery2 = potentialCombo.effectivenesses[0];
    			maxSuper2 = potentialCombo.effectivenesses[2];
    		}else if(potentialCombo.effectivenesses[0] == minNotVery2 && (potentialCombo.effectivenesses[2] > maxSuper2)) {
    			bestType2 = type;
    			minNotVery2 = potentialCombo.effectivenesses[0];
    			maxSuper2 = potentialCombo.effectivenesses[2];
    		}
    	}
    	
    	return(bestType + " " + bestType2);
    }*/
    
    public static void setGeneration(int generation) {
    	if((generation > 1) && (generation < 6)) {
    		chart = chart2;
    	}else {
    		chart = chart6;
    	}
    }
	
    @Override
    public String toString() {
    	return(toString(false, true, false));
    }
    
	public String toString(boolean getTypes, boolean getStats, boolean getEffectivenesses) {
		String out = "";
		
		if(getTypes) {
			out = out + getTypes() + '\n';
		}
		
		if(getStats) {
			out = out + getStats() + '\n';
		}
		
		if(getEffectivenesses) {
			out = out + getEffectivenesses() + '\n';
		}
		
		return(out);
	}

    public String getTypes() {
    	String out = "";
    	
    	for(int i = 0; i < types.size(); i ++) {
    		out = out + types.get(i) + " ";
    	}
    	
    	return(out);
    }
    
    public String getStats() {
    	return("Not very effective: " + stats[0] + " Regular effectiveness: " + stats[1] + " Super effective: " + stats[2]);
    }
    
    public String getEffectivenesses() {
    	return("Not very effective: " + notVery + " \nRegular effectiveness: " + regular + " \nSuper effective: " + supe);
    }
    
    /*public String toStringWithTypes() {
    	return(getTypes() + '\n' + toString());
    }*/
    
    public String differTypes(Coverage other) {
    	String[] typesCopy = new String[other.types.size()];
    	String out = "";
    	
    	for(int i = 0; i < typesCopy.length; i ++) {
    		//System.out.println(typesCopy.length + " " + other.types.length + " " + i);
    		typesCopy[i] = other.types.get(i);
    	}
    	
    	for(int x = 0; x < types.size(); x ++) {
    		for(int y = 0; y < typesCopy.length; y ++) {
    			if(types.get(x).equals(typesCopy[y])) {
    				typesCopy[y] = "";
    			}
    		}
    	}
    	
    	for(int i = 0; i < typesCopy.length; i ++) {
    		if(!typesCopy[i].isEmpty()) {
    		    out = out + typesCopy[i] + " ";
    		}
    	}
    	
    	return(out);
    }
    
	public static int indexOf(String[] array, String string) {
		for(int i = 0; i < array.length; i ++) {
			String maybeSubstring = string;
			String maybeSubarray = array[i];
			
			if((array.length > 14) && (array.length < 20)) {
				maybeSubstring = string.substring(0, 3);
				maybeSubarray = array[i].substring(0, 3);
			}
			
			if(maybeSubstring.equals(maybeSubarray)) {
				return(i);
			}
		}
		
		System.out.println(string + " is making me return -1!");
		return(-1);
	}
	
	public static void fillChart() {
		for(int i = 0; i < chart6.length; i ++) {
			for(int j = 0; j < chart6[i].length; j++) {
				chart6[i][j] = 1.0;
			}
		}
		
		for(int i = 0; i < chart2.length; i ++) {
			for(int j = 0; j < chart2[i].length; j++) {
				chart2[i][j] = 1.0;
			}
		}
		
		chart = chart2;
		
		a("nor", "gho");
		a("ele", "gro");
		a("fig", "gho");
		a("poi", "ste");
		a("gro", "fly");
		a("psy", "dar");
		a("gho", "nor");
		
		b("nor", "roc");
		b("nor", "ste");
		b("fir", "fir");
		b("fir", "wat");
		b("fir", "roc");
		b("fir", "dra");
		b("wat", "wat");
		b("wat", "gra");
		b("wat", "dra");
		b("ele", "ele");
		b("ele", "gra");
		b("ele", "dra");
		b("gra", "fir");
		b("gra", "gra");
		b("gra", "poi");
		b("gra", "fly");
		b("gra", "bug");
		b("gra", "dra");
		b("gra", "ste");
		b("ice", "fir");
		b("ice", "wat");
		b("ice", "ice");
		b("ice", "ste");
		b("fig", "poi");
		b("fig", "fly");
		b("fig", "psy");
		b("fig", "bug");
		b("poi", "poi");
		b("poi", "gro");
		b("poi", "roc");
		b("poi", "gho");
		b("gro", "gra");
		b("gro", "bug");
		b("fly", "ele");
		b("fly", "roc");
		b("fly", "ste");
		b("psy", "psy");
		b("psy", "ste");
		b("bug", "fir");
		b("bug", "fig");
		b("bug", "poi");
		b("bug", "fly");
		b("bug", "gho");
		b("bug", "ste");
		b("roc", "fig");
		b("roc", "gro");
		b("roc", "ste");
		b("gho", "dar");
		b("gho", "ste");
		b("dra", "ste");
		b("dar", "fig");
		b("dar", "dar");
		b("dar", "ste");
		b("ste", "fir");
		b("ste", "wat");
		b("ste", "ele");
		b("ste", "ste");
		
		c("fir", "gra");
		c("fir", "ice");
		c("fir", "bug");
		c("fir", "ste");
		c("wat", "fir");
		c("wat", "gro");
		c("wat", "roc");
		c("ele", "wat");
		c("ele", "fly");
		c("gra", "wat");
		c("gra", "gro");
		c("gra", "roc");
		c("ice", "gra");
		c("ice", "gro");
		c("ice", "fly");
		c("ice", "dra");
		c("fig", "nor");
		c("fig", "ice");
		c("fig", "roc");
		c("fig", "dar");
		c("fig", "ste");
		c("poi", "gra");
		c("gro", "fir");
		c("gro", "ele");
		c("gro", "poi");
		c("gro", "roc");
		c("gro", "ste");
		c("fly", "gra");
		c("fly", "fig");
		c("fly", "bug");
		c("psy", "fig");
		c("psy", "poi");
		c("bug", "gra");
		c("bug", "psy");
		c("bug", "roc");
		c("roc", "fir");
		c("roc", "ice");
		c("roc", "fly");
		c("roc", "bug");
		c("gho", "psy");
		c("gho", "gho");
		c("dra", "dra");
		c("dar", "psy");
		c("dar", "gho");
		c("ste", "ice");
		c("ste", "roc");
		
		chart = chart6;
		//System.out.println(chart[1][1]);
		
		for(int i = 0; i < chart2.length; i ++) {
			for(int j = 0; j < chart2[i].length; j++) {
				chart6[i][j] = chart2[i][j];
			}
		}
		
		n("gho", "ste");
		n("dar", "ste");
		a("dra", "fai");
		b("fig", "fai");
		b("bug", "fai");
		b("dar", "fai");
		c("poi", "fai");
		c("ste", "fai");
		b("fai", "fir");
		b("fai", "poi");
		b("fai", "ste");
		c("fai", "fig");
		c("fai", "dra");
		c("fai", "dar");
		b("fre", "fir");
		b("fre", "ice");
		b("fre", "ste");
		c("fre", "wat");
		c("fre", "gra");
		c("fre", "gro");
		c("fre", "fly");
		c("fre", "dra");
	}
	
	public static void a(String attack, String defense) {
		chart[indexOf(attackNames, attack)][indexOf(defenseNames, defense)] = 0.0;
	}
	
	public static void b(String attack, String defense) {
		chart[indexOf(attackNames, attack)][indexOf(defenseNames, defense)] = 0.5;
	}
	
	public static void c(String attack, String defense) {
		chart[indexOf(attackNames, attack)][indexOf(defenseNames, defense)] = 2.0;
	}
	
	public static void n(String attack, String defense) {
		chart[indexOf(attackNames, attack)][indexOf(defenseNames, defense)] = 1.0;
	}
	
	public static ArrayList<ArrayList<Coverage>> bestCombos(ArrayList<Coverage> combos, int outputSize){
		ArrayList<ArrayList<Coverage>> out = new ArrayList<ArrayList<Coverage>>();
		out.add(combos);
		
		while(out.size() < outputSize) {
			out.add(new ArrayList<Coverage>());
		}
		
		for(int x = 0; x < outputSize; x ++) {
			boolean different = true;
			ArrayList<Coverage> nextList = new ArrayList<Coverage>();
			
			try {
				nextList = out.get(x + 1);
			}catch(IndexOutOfBoundsException exception) {}
			
			while(different) {
				different = false;
				int ties = 1;
				//System.out.println("reached0");
				
				while(ties < out.get(x).size()) {
					if(out.get(x).get(0).compareTo(out.get(x).get(ties)) > 0){
						nextList.add(out.get(x).remove(ties));
						different = true;
					}else if(out.get(x).get(0).compareTo(out.get(x).get(ties)) < 0) {
						for(int y = 0; y < ties; y ++) {
							nextList.add(out.get(x).remove(0));
						}
						
						ties = 1;
						different = true;
					}else if(out.get(x).get(0).compareTo(out.get(x).get(ties)) == 0){
						ties ++;
					}else {
						System.out.println(out.get(x).get(0).toString(true, true, true) + " and " + out.get(x).get(ties).toString(true, true, true) + " were not better, worse, or equal!");
					}
				}
			}
		}
		
		return(out);
	}
	
	public static ArrayList<String> copyList(ArrayList<String> in) {
		ArrayList<String> out = new ArrayList<String>();
		
		for(int i = 0; i < in.size(); i ++) {
			out.set(i, in.get(i));
		}
		
		return(out);
	}
	
	public int compareTo(Coverage other) {
		if(other == null) {
			return(1);
		}
		
		if(stats[0] == other.stats[0]) {
			return(stats[2] - other.stats[2]);
		}
		
		return(other.stats[0] - stats[0]);
	}
	
	public boolean equals(Coverage other) {
		return(compareTo(other) == 0);
	}
}
