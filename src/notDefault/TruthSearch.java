package notDefault;

public class TruthSearch implements Search{
    private String truth;
    private boolean notFalse;
    
    public TruthSearch(String string) {
    	truth = string;
    	notFalse = true;
    	
    	if(string.charAt(0) == '!') {
    		notFalse = false;
    		truth = string.substring(1);
    	}
    }
    
    public boolean find(Pokemon pokemon) {
    	return(pokemon.isTrue(truth) == notFalse);
    }
}
