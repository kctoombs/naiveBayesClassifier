import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class NaiveBayesClassifier {
    public static void main(String[] args) {
	// Declare a HashMap, which maps integers to Words
	HashMap<Integer, Word> words = new HashMap<Integer, Word>();
	int keyValue = 0;
	int current = 0;
	int oneCount = 0;
	int zeroCount = 0;
	//File file = new File("trainingdemo.txt");

	Scanner sc = null;

	final long startTime = System.currentTimeMillis();
	
	try {
	    
	    sc = new Scanner(new File("training.txt"));
	 
	} catch(FileNotFoundException e){
	    e.printStackTrace();
	}
       
	while (sc.hasNextLine()) {
	    Scanner sc2 = new Scanner(sc.nextLine());
	    while(sc2.hasNext()){
		String s = sc2.next();
		Word word = new Word(s);
		keyValue = word.hashCode();

		if(s.equals("1")){
		    oneCount++;
		    current = 1;
		}

		else if(s.equals("0")){
		    current = 0;
		    zeroCount++;
		}
		
		else{
		    if(!words.containsKey(keyValue)){
			word.setNumPositiveOccurences(word.getNumPositiveOccurences() + current);
			word.setNumOccurences(word.getNumOccurences() + 1);
			//Stopwords
			if(!s.equalsIgnoreCase("the") && !s.equalsIgnoreCase("a") && !s.equalsIgnoreCase("is") && !s.equalsIgnoreCase("for")
			   && !s.equalsIgnoreCase("and") && !s.equalsIgnoreCase("but") && !s.equalsIgnoreCase("if") && !s.equalsIgnoreCase("then")
			   && !s.equalsIgnoreCase("that") && !s.equalsIgnoreCase("on") && !s.equalsIgnoreCase("in") && !s.equalsIgnoreCase("was")
			   && !s.equalsIgnoreCase("to") && !s.equalsIgnoreCase("with") && !s.equalsIgnoreCase("do") && !s.equalsIgnoreCase("did")
			   && !s.equalsIgnoreCase("this") && !s.equalsIgnoreCase("what") && !s.equalsIgnoreCase("does") && !s.equalsIgnoreCase("I")
			   && !s.equalsIgnoreCase("we")  && !s.equalsIgnoreCase("they") && !s.equalsIgnoreCase("be") && !s.equalsIgnoreCase("as")
			   && !s.equalsIgnoreCase("of")  && !s.equalsIgnoreCase("it") && !s.equalsIgnoreCase("its")  && !s.equalsIgnoreCase("or")
			   && !s.equalsIgnoreCase("are") && !s.equalsIgnoreCase("an") && !s.equalsIgnoreCase("/><br")
			   && !s.equalsIgnoreCase("/>")
			   && !s.equalsIgnoreCase("above") && !s.equalsIgnoreCase("after")
			   && !s.equalsIgnoreCase("am") && !s.equalsIgnoreCase("against") && !s.equalsIgnoreCase("all") && !s.equalsIgnoreCase("about")
			   && !s.equalsIgnoreCase("any") && !s.equalsIgnoreCase("by") && !s.equalsIgnoreCase("can") && !s.equalsIgnoreCase("could")
			   && !s.equalsIgnoreCase("else") && !s.equalsIgnoreCase("even") && !s.equalsIgnoreCase("ever") && !s.equalsIgnoreCase("every")
			   && !s.equalsIgnoreCase("get") && !s.equalsIgnoreCase("give") && !s.equalsIgnoreCase("go") && !s.equalsIgnoreCase("had")
			   && !s.equalsIgnoreCase("has")  && !s.equalsIgnoreCase("have") && !s.equalsIgnoreCase("he") && !s.equalsIgnoreCase("her")
			   && !s.equalsIgnoreCase("how")  && !s.equalsIgnoreCase("into") && !s.equalsIgnoreCase("me")  && !s.equalsIgnoreCase("may")
			   && !s.equalsIgnoreCase("might") && !s.equalsIgnoreCase("mine") && !s.equalsIgnoreCase("more")
			   && !s.equalsIgnoreCase("most")
			   && !s.equalsIgnoreCase("much") && !s.equalsIgnoreCase("must")
			   && !s.equalsIgnoreCase("my") && !s.equalsIgnoreCase("name") && !s.equalsIgnoreCase("never") && !s.equalsIgnoreCase("next")
			   && !s.equalsIgnoreCase("no") && !s.equalsIgnoreCase("nobody") && !s.equalsIgnoreCase("none") && !s.equalsIgnoreCase("not")
			   && !s.equalsIgnoreCase("nothing") && !s.equalsIgnoreCase("now") && !s.equalsIgnoreCase("off") && !s.equalsIgnoreCase("often")
			   && !s.equalsIgnoreCase("on") && !s.equalsIgnoreCase("once") && !s.equalsIgnoreCase("one") && !s.equalsIgnoreCase("only")
			   && !s.equalsIgnoreCase("other")  && !s.equalsIgnoreCase("our") && !s.equalsIgnoreCase("ours") && !s.equalsIgnoreCase("out")
			   && !s.equalsIgnoreCase("over")  && !s.equalsIgnoreCase("own") && !s.equalsIgnoreCase("part")  && !s.equalsIgnoreCase("put")
			   && !s.equalsIgnoreCase("same") && !s.equalsIgnoreCase("see") && !s.equalsIgnoreCase("seen")
			   && !s.equalsIgnoreCase("seem") && !s.equalsIgnoreCase("seems")
			   && !s.equalsIgnoreCase("seemed") && !s.equalsIgnoreCase("she")
			   && !s.equalsIgnoreCase("should") && !s.equalsIgnoreCase("show") && !s.equalsIgnoreCase("since") && !s.equalsIgnoreCase("so")
			   && !s.equalsIgnoreCase("some") && !s.equalsIgnoreCase("somehow") && !s.equalsIgnoreCase("someone") && !s.equalsIgnoreCase("something")
			   && !s.equalsIgnoreCase("sometime") && !s.equalsIgnoreCase("sometimes") && !s.equalsIgnoreCase("somewhere") && !s.equalsIgnoreCase("still")
			   && !s.equalsIgnoreCase("such") && !s.equalsIgnoreCase("take") && !s.equalsIgnoreCase("than") && !s.equalsIgnoreCase("that")
			   && !s.equalsIgnoreCase("their")  && !s.equalsIgnoreCase("there") && !s.equalsIgnoreCase("them") && !s.equalsIgnoreCase("then")
			   && !s.equalsIgnoreCase("therefore")  && !s.equalsIgnoreCase("these") && !s.equalsIgnoreCase("they")  && !s.equalsIgnoreCase("this")
			   && !s.equalsIgnoreCase("those") && !s.equalsIgnoreCase("though") && !s.equalsIgnoreCase("through")
			   && !s.equalsIgnoreCase("throughout")
			   && !s.equalsIgnoreCase("thus") && !s.equalsIgnoreCase("too")
			   && !s.equalsIgnoreCase("until") && !s.equalsIgnoreCase("up") && !s.equalsIgnoreCase("upon") && !s.equalsIgnoreCase("us")
			   && !s.equalsIgnoreCase("very") && !s.equalsIgnoreCase("was") && !s.equalsIgnoreCase("were") && !s.equalsIgnoreCase("well")
			   && !s.equalsIgnoreCase("what") && !s.equalsIgnoreCase("whatever") && !s.equalsIgnoreCase("when") && !s.equalsIgnoreCase("whenever")
			   && !s.equalsIgnoreCase("where") && !s.equalsIgnoreCase("wherever") && !s.equalsIgnoreCase("whether") && !s.equalsIgnoreCase("which")
			   && !s.equalsIgnoreCase("while")  && !s.equalsIgnoreCase("who") && !s.equalsIgnoreCase("whoever") && !s.equalsIgnoreCase("whole")
			   && !s.equalsIgnoreCase("whom")  && !s.equalsIgnoreCase("whose") && !s.equalsIgnoreCase("why")  && !s.equalsIgnoreCase("will")
			   && !s.equalsIgnoreCase("with") && !s.equalsIgnoreCase("within") && !s.equalsIgnoreCase("without")
			   && !s.equalsIgnoreCase("would") && !s.equalsIgnoreCase("yet") && !s.equalsIgnoreCase("you") && !s.equalsIgnoreCase("your")
			   && !s.equalsIgnoreCase("yours") && !s.equalsIgnoreCase("you're") && !s.equalsIgnoreCase("yourself")
			   && !s.equalsIgnoreCase("after")
			   && !s.equalsIgnoreCase("able") && !s.equalsIgnoreCase("above") && !s.equalsIgnoreCase("actually")
			   && !s.equalsIgnoreCase("again") && !s.equalsIgnoreCase("against") && !s.equalsIgnoreCase("almost") && !s.equalsIgnoreCase("along")
			   && !s.equalsIgnoreCase("already") && !s.equalsIgnoreCase("among") && !s.equalsIgnoreCase("became") && !s.equalsIgnoreCase("become")
			   && !s.equalsIgnoreCase("because") && !s.equalsIgnoreCase("before") && !s.equalsIgnoreCase("being") && !s.equalsIgnoreCase("believe")
			   && !s.equalsIgnoreCase("beside")  && !s.equalsIgnoreCase("besides") && !s.equalsIgnoreCase("between") && !s.equalsIgnoreCase("both")
			   && !s.equalsIgnoreCase("but")  && !s.equalsIgnoreCase("came") && !s.equalsIgnoreCase("can")  && !s.equalsIgnoreCase("can't")
			   && !s.equalsIgnoreCase("cause") && !s.equalsIgnoreCase("come") && !s.equalsIgnoreCase("definitely")
			   && !s.equalsIgnoreCase("doing")
			   && !s.equalsIgnoreCase("during") && !s.equalsIgnoreCase("each")
			   && !s.equalsIgnoreCase("enough") && !s.equalsIgnoreCase("everyone") && !s.equalsIgnoreCase("everybody") && !s.equalsIgnoreCase("exactly")
			   && !s.equalsIgnoreCase("everywhere") && !s.equalsIgnoreCase("furthermore") && !s.equalsIgnoreCase("gets") && !s.equalsIgnoreCase("given")
			   && !s.equalsIgnoreCase("gives") && !s.equalsIgnoreCase("goes") && !s.equalsIgnoreCase("going") && !s.equalsIgnoreCase("gotten")
			   && !s.equalsIgnoreCase("hadn't") && !s.equalsIgnoreCase("hasn't") && !s.equalsIgnoreCase("however") && !s.equalsIgnoreCase("I'm")
			   && !s.equalsIgnoreCase("I'll")  && !s.equalsIgnoreCase("I've") && !s.equalsIgnoreCase("I'd") && !s.equalsIgnoreCase("isn't")
			   && !s.equalsIgnoreCase("it'd")  && !s.equalsIgnoreCase("it'll") && !s.equalsIgnoreCase("just")  && !s.equalsIgnoreCase("keep")
			   && !s.equalsIgnoreCase("know") && !s.equalsIgnoreCase("knows") && !s.equalsIgnoreCase("known")
			   && !s.equalsIgnoreCase("it's")
			   && !s.equalsIgnoreCase("last") && !s.equalsIgnoreCase("let")
			   && !s.equalsIgnoreCase("let's") && !s.equalsIgnoreCase("look") && !s.equalsIgnoreCase("looks") && !s.equalsIgnoreCase("many")
			   && !s.equalsIgnoreCase("maybe") && !s.equalsIgnoreCase("mean") && !s.equalsIgnoreCase("more") && !s.equalsIgnoreCase("must")
			   && !s.equalsIgnoreCase("myself") && !s.equalsIgnoreCase("next") && !s.equalsIgnoreCase("nor") && !s.equalsIgnoreCase("nothing")
			   && !s.equalsIgnoreCase("oh") && !s.equalsIgnoreCase("ok") && !s.equalsIgnoreCase("okay") && !s.equalsIgnoreCase("really")
			   && !s.equalsIgnoreCase("quite")  && !s.equalsIgnoreCase("said") && !s.equalsIgnoreCase("same") && !s.equalsIgnoreCase("saw")
			   && !s.equalsIgnoreCase("say")  && !s.equalsIgnoreCase("see") && !s.equalsIgnoreCase("seeing")  && !s.equalsIgnoreCase("seemed")
			   && !s.equalsIgnoreCase("shouldn't") && !s.equalsIgnoreCase("still") && !s.equalsIgnoreCase("sure")
			   && !s.equalsIgnoreCase("thanks")
			   && !s.equalsIgnoreCase("than") && !s.equalsIgnoreCase("thats")
			   && !s.equalsIgnoreCase("that's") && !s.equalsIgnoreCase("they'll") && !s.equalsIgnoreCase("they're") && !s.equalsIgnoreCase("they'd")
			   && !s.equalsIgnoreCase("they've") && !s.equalsIgnoreCase("think") && !s.equalsIgnoreCase("toward") && !s.equalsIgnoreCase("towards")
			   && !s.equalsIgnoreCase("try") && !s.equalsIgnoreCase("trying") && !s.equalsIgnoreCase("us") && !s.equalsIgnoreCase("unto")
			   && !s.equalsIgnoreCase("use") && !s.equalsIgnoreCase("used") && !s.equalsIgnoreCase("using") && !s.equalsIgnoreCase("uses")
			   && !s.equalsIgnoreCase("via")  && !s.equalsIgnoreCase("want") && !s.equalsIgnoreCase("wants") && !s.equalsIgnoreCase("we'd")
			   && !s.equalsIgnoreCase("we'll")  && !s.equalsIgnoreCase("we're") && !s.equalsIgnoreCase("we've")  && !s.equalsIgnoreCase("welcome")
			   && !s.equalsIgnoreCase("weren't") && !s.equalsIgnoreCase("what's") && !s.equalsIgnoreCase("where's")
			   && !s.equalsIgnoreCase("won't")
			   && !s.equalsIgnoreCase("wonder") && !s.equalsIgnoreCase("yes")
			   && !s.equalsIgnoreCase("you'd") && !s.equalsIgnoreCase("you'll") && !s.equalsIgnoreCase("you've") && !s.equalsIgnoreCase("cannot")
			   && !s.equalsIgnoreCase("below") && !s.equalsIgnoreCase("few") && !s.equalsIgnoreCase("he") && !s.equalsIgnoreCase("he's")
			   && !s.equalsIgnoreCase("he'll") && !s.equalsIgnoreCase("her") && !s.equalsIgnoreCase("herself") && !s.equalsIgnoreCase("himself")
			   && !s.equalsIgnoreCase("mustn't") && !s.equalsIgnoreCase("ought") && !s.equalsIgnoreCase("she'd") && !s.equalsIgnoreCase("she's")
			   && !s.equalsIgnoreCase("she'll")  && !s.equalsIgnoreCase("one") && !s.equalsIgnoreCase("themselves")
			   && !s.equalsIgnoreCase("liked")  && !s.equalsIgnoreCase("likes")){
			    words.put(keyValue, word);
			}
			    
		    }//if

		    else{
			words.get(keyValue).setNumPositiveOccurences(words.get(keyValue).getNumPositiveOccurences() + current);
			words.get(keyValue).setNumOccurences(words.get(keyValue).getNumOccurences() + 1);
								     
		    }
		    
		
		    
		} //else
		
		
	    } //while(sc2.hasNext())
	    
	    
	} //while(sc.hasNextLine())

	
  
	for (Map.Entry<Integer, Word> entry : words.entrySet())
	    {
		entry.getValue().setProbability((entry.getValue().getNumPositiveOccurences()) / (float)(entry.getValue().getNumOccurences()));
		entry.getValue().setProbabilityAppears((entry.getValue().getNumOccurences()) / (float)(oneCount + zeroCount));
		//		if(entry.getValue().getValue().equalsIgnoreCase("all"))
		//  System.out.println(entry.getValue().getValue());
	    }

	final long endTime = System.currentTimeMillis();
	 
	sc.close();
	//end of training


	
	double numerator = 1;  //P(Wt|C)* ... *P(Wn|C)
	double denominator = 1; //P(Wt)* ... *P(Wn)
	double numerator1 = 1; //P(!Wt)* ... *P(!Wn)
	int ones = 0; //one count
	int zeros = 0; //zero count
	int currentNum = 0; //Current review we are scanning, 0 or 1
	int accurate = 0; //Number of accurate guesses
	double accuracy = 0.07;
	try {
	    
	    sc = new Scanner(new File(args[0]));
	    
	} catch(FileNotFoundException e){
	    e.printStackTrace();
	}
	
	final long firstTime = System.currentTimeMillis();
	    
	    while (sc.hasNextLine()) {
		Scanner sc2 = new Scanner(sc.nextLine());
		while(sc2.hasNext()){
		    String s = sc2.next();
		    Word word = new Word(s);
		    keyValue = word.hashCode();
		    
		    if(s.equals("1")){
			currentNum = 1;
			ones++;
			if((numerator/denominator) >= (numerator1/denominator)){  //if (Wt*...*Wn) >= (!Wt*...*!Wn)
			    int thisReview = 1;
			    if(thisReview == currentNum){
				accurate++;
			    }
			}
			numerator = 1;  //reset numerator at end
			denominator = 1;  //reset denominator at end
			numerator1 = 1; //reset other numerator
		    } //if s.equals("1")
		    
		    else if(s.equals("0")){
			currentNum = 0;
			zeros++;
			if((numerator/denominator) <= (numerator1/denominator)){
			    int thisReview = 0;
			    if(thisReview == currentNum){
				accurate++;
			    }
			}
			numerator = 1;  //reset numerator at end
			denominator = 1;  //reset denominator at end
			numerator1 = 1; //reset other numerator
		    } //else if(s.equals("0")
		    
		    else{
			if(words.containsKey(keyValue)){
			    numerator *= words.get(keyValue).getProbability();
			    numerator1 *= (1 - (words.get(keyValue).getProbability()));
			    denominator *= words.get(keyValue).getProbabilityAppears();
			}
		    }
		} //while(sc2.hasNext())
	    } //while(sc.hasNextLine())

	    accuracy += (accurate/(float)(ones + zeros)); //accuracy of classifications
	   
	    sc.close(); //end of training.txt classification




	    
	    double numerator2 = 1;  //P(Wt|C)* ... *P(Wn|C)
	    double denominator2 = 1; //P(Wt)* ... *P(Wn)
	    double numerator3 = 1; //P(!Wt)* ... *P(!Wn)
	    int onesCount = 0; //one count
	    int zerosCount = 0; //zero count
	    int currentNumber = 0; //Current review we are scanning, 0 or 1
	    int accurateCount = 0; //Number of accurate guesses
	    double accuracy1 = 0.07;
	    try {
		
		sc = new Scanner(new File(args[1]));
		
	    } catch(FileNotFoundException e){
		e.printStackTrace();
	    }
	    
	    while (sc.hasNextLine()) {
		Scanner sc2 = new Scanner(sc.nextLine());
		while(sc2.hasNext()){
		    String s = sc2.next();
		    Word word = new Word(s);
		    keyValue = word.hashCode();
		    
		    if(s.equals("1")){
			currentNumber = 1;
			onesCount++;
			if((numerator2/denominator2) >= (numerator3/denominator2)){  //if (Wt*...*Wn) >= (!Wt*...*!Wn)
			    System.out.println("1");
			    int thisReview = 1;
			    if(thisReview == currentNumber){
				accurateCount++;
			    }
			}
			
			else{
			    System.out.println("0");
			}
			
			numerator2 = 1;  //reset numerator at end
			denominator2 = 1;  //reset denominator at end
			numerator3 = 1; //reset other numerator
		    } //if s.equals("1")
		    
		    else if(s.equals("0")){
			currentNumber = 0;
			zerosCount++;
			if((numerator2/denominator2) <= (numerator3/denominator2)){
			    System.out.println("0");
			    int thisReview = 0;
			    if(thisReview == currentNum){
				accurateCount++;
			    }
			}

			else{
			    System.out.println("1");
			}
			
			numerator2 = 1;  //reset numerator at end
			denominator2 = 1;  //reset denominator at end
			numerator3 = 1; //reset other numerator
		    } //else if(s.equals("0")
		    
		    else{
			if(words.containsKey(keyValue)){
			    numerator2 *= words.get(keyValue).getProbability();
			    numerator3 *= (1 - (words.get(keyValue).getProbability()));
			    denominator2 *= words.get(keyValue).getProbabilityAppears();
			}
		    }
		} //while(sc2.hasNext())
	    } //while(sc.hasNextLine())

	    accuracy1 += (accurateCount/(float)(onesCount + zerosCount));	   

	    sc.close(); //end of classification of testing set
	
	    
	    final long lastTime = System.currentTimeMillis();

	    System.out.println(((endTime - startTime) / 100) + " seconds (training)"); //Print time it took to train
	    System.out.println(((lastTime - firstTime) / 100) + " seconds (labeling)"); //Print time it took to run classifier on training/testing
	    System.out.println(String.format("%.3f", accuracy) + " (training)"); //Print accuracy on training set
	    System.out.println(String.format("%.3f", accuracy1) + " (testing)"); //Print accuracy on testing set

	
    }//end main
    
}//end class

