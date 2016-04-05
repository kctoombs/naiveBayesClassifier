public class Word{
    String value;
    int numOfOccurences;
    int numOfPositiveOccurences;
    double probability;
    double probabilityAppears;

    public Word(String word){
	value = word;
	numOfOccurences = 0;
	numOfPositiveOccurences = 0;
	probability = 0;
    }

    public String getValue(){
	return value;
    }

    public void setValue(String word){
	this.value = word;
    }

    public int getNumOccurences(){
	return numOfOccurences;
    }

    public void setNumOccurences(int value){
	this.numOfOccurences = value;
    }

    public int getNumPositiveOccurences(){
	return numOfPositiveOccurences;
    }

    public void setNumPositiveOccurences(int value){
	this.numOfPositiveOccurences = value;
    }

    public double getProbability(){
	return probability;
    }

    public void setProbability(float prob){
	this.probability = prob;
    }

    public double getProbabilityAppears(){
	return this.probabilityAppears;
    }

    public void setProbabilityAppears(double probabilityAppears){
	this.probabilityAppears = probabilityAppears;
    }

    public boolean equals(Object o){
	if(o == null)
	    return false;
	if(!(o instanceof Word)){
	    return false;
	}
	Word w = (Word) o;
	
	
	if(w.value.equals(this.value)){
	    return true;
	}
	else{
	    return false;
	}
    }

    public int hashCode(){
	int hash = 0;
	char[] val = this.value.toCharArray();
	for(int i = 0; i < this.value.length(); i++){
	    hash += val[i];
	}
	return hash;
    }

    
} //end class
