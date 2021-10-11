import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;

public class LCW {
	 public static void main(String[] args) throws FileNotFoundException,IOException {
		 
long startTime = System.currentTimeMillis();   	
		    Scanner inFile = new Scanner(new File("C:\\Users\\LENOVO\\Input_02.txt")).useDelimiter(",\\s*");
		     ArrayList<String> temps = new ArrayList<String>();

		    while (inFile.hasNextLine()) {
			      temps.add(inFile.nextLine());
		    }
		    inFile.close();
		
		    String words[]=new String[temps.size()];
		    for(int i=0;i<temps.size();i++) {
		    	words[i]= temps.get(i);
		    }
		    
 long stopTime = System.currentTimeMillis();
		      long elapsedTime = stopTime - startTime;
		      System.out.println("Time Taken to process input: "+elapsedTime+" milliseconds");
		    	
			System.out.println("Longest Compound word : " + longestCompundWord(words));
			
	}	 
	private static String longestCompundWord(String[] words) {
		ArrayList<String> dictionary = new ArrayList<String>();
		TreeMap<String, Integer> wordTree = new TreeMap<String, Integer>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		for (int i = 0; i < words.length; i++) {
			wordTree.put(words[i], i);
			dictionary.add(words[i]);
		}
		
		return findTheLongest(wordTree, dictionary);
		
	}
		
    public static boolean CompoundWordOrNot(String word, ArrayList<String> dictionary) {
		if (dictionary.contains(word))
			return true;
		for (int i = 1; i < word.length(); i++) {
			String prefix = word.substring(0, i);
			if (CompoundWordOrNot(prefix, dictionary)) {
				String remainder = word.substring(i, word.length());
				if (remainder.length() == 0)
					return true;
				return CompoundWordOrNot(remainder, dictionary);
			}
		}
		return false;
	}
	public static String findTheLongest(TreeMap<String, Integer> wordTree, ArrayList<String> dictionary) {
		while (wordTree.size() > 0) {
			String word = wordTree.lastKey();
			wordTree.remove(word);
			dictionary.remove(word);
			if (CompoundWordOrNot(word, dictionary)) {
				return word;	
			}			
		}
	
		return "";  	
	}
	
} 






