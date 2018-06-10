package notDefault;

public class StatSearch implements Search{
    private int index;
    private int condition;
    private int number;
    public static final String[] allConditions = {"=", "<", ">", "!=", "<=", ">="};
    
    public StatSearch(String statistic, int inCondition, int inNumber) {
    	index = Main.indexOf(Main.statistics, statistic);
    	condition = inCondition;
    	number = inNumber;
    }
    
    public boolean find(Pokemon pokemon) {
    	//System.out.println("condition: " + condition);
    	
    	switch(condition) {
    	case 0:
    		return(pokemon.getStatistic(index) == number);
    	case 1:
    		return(pokemon.getStatistic(index) < number);
    	case 2:
    		return(pokemon.getStatistic(index) > number);
    	case 3:
    		return(pokemon.getStatistic(index) != number);
    	case 4:
    		return(pokemon.getStatistic(index) <= number);
    	case 5:
    		return(pokemon.getStatistic(index) >= number);
    	}
    	
    	return(false);
    }
    
    public static StatSearch isStatSearch(String string) {
    	if(Main.contains(allConditions, string)){
    		//System.out.println("returned true");
    		return(new StatSearch(string.substring(0, string.indexOf(getContained(allConditions, string))), 
    				Main.indexOf(allConditions, getContained(allConditions, string)), 
    				Integer.parseInt(string.substring(string.indexOf(getContained(allConditions, string))
    				+ getContained(allConditions, string).length()))));
    	}
    	
    	return(null);
    }
    
    public static String getContained(String[] array, String string) {
    	for(int i = array.length - 1; i >= 0; i --) {
    		if(string.indexOf(array[i]) > 0) {
    			return(array[i]);
    		}
    	}
    	
    	System.out.println(string + " is making me return the null pointer!");
    	return(null);
    }
}
