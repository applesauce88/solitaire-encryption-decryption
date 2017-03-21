package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		// COMPLETE THIS METHOD
		CardNode index = deckRear;
	    int counter =0;
	    while (index.next.cardValue!=27){
			index=index.next;
			counter++;
		}
	    CardNode a = index.next;
		CardNode b = index.next.next;
		CardNode c = index.next.next.next;
		
		index.next.next.next=a;
		index.next.next=c;
		index.next=b;
		
		while (counter<28){
			////Debugger  System.out.print("i:" + index.cardValue);
			index=index.next;
			counter++;
		}
		
		deckRear=index;
		//Debugger  System.out.println("\n"+"-ONE");
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    // COMPLETE THIS METHOD
		 //JokerB, 28 now 2
	    CardNode index = deckRear;
	    int counter =0;
		while (index.next.cardValue!=28){
			index=index.next;
			counter++;
		}
		////Debugger  System.out.println("*");
		CardNode a = index.next;
		CardNode b = index.next.next;
		//CardNode c = index.next.next.next;
		CardNode d = index.next.next.next.next;
		
		
		index.next.next.next.next=a;
		//doesnt change
		index.next.next=d;
		index.next=b;
		////Debugger  System.out.println("**");
		while (counter<28){
			index=index.next;
			counter++;
			////Debugger  System.out.print(counter+", ");
		}
		////Debugger  System.out.println("cleared");
		deckRear=index;
		//Debugger  System.out.println("\n"+"-TWO");
	}
		
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		// COMPLETE THIS METHOD
		 CardNode index = deckRear;
			
		 index=deckRear;
		 CardNode a = index.next;
		 index=index.next;
		 int b=0,c=0;
		 CardNode nb=a,nc=a;
		 for(int i=1;i<29;i++){
		    if (index.cardValue==27){
		    	b = i; nb=index; ////Debugger  System.out.println("b:"+b);
		    }
		    if (index.cardValue==28){
		    	c = i; nc=index; ////Debugger  System.out.println("c:"+c);
		    }
		    index=index.next;
		   	
		 }
		 index=deckRear;
		 //Debugger  System.out.println("THE VALUES OF B AND C ARE"+b+", "+c);
		 if (b<c){ // 27, 28
			 c++;
			 nc=nc.next;
		 } else { // 28, 27 
			 b++;
			 nb=nb.next;
		 }
		 //Debugger  System.out.println("("+b+", "+c +")");
		 if (b<c){
			 if (c==29&&b==1){
			    return;
			 } else if (c==29){ //works
			    while (index.next.next.cardValue!=27){
			    	index=index.next;
			    }
			    deckRear=index.next;
			    //Debugger  System.out.println("THEORY1");
			 } else if (b==1) {
			    while (index.next.cardValue!=28){
			    	index=index.next;
			    }
			    deckRear=index.next;
			    //Debugger  System.out.println("THEORY2");
			 } else {
			    while (index.cardValue!=28){
			    	index=index.next;
			    }
			    index.next=a;
			    index=deckRear;
			    while (index.next.cardValue!=27){
			    	index=index.next;
			    }
			    index.next=nc;
			    deckRear.next=nb;
			    deckRear=index;
			    //Debugger  System.out.println("THEORY3");
			 }
		 }else{ //case when order is 28 then 27; or c then b
			 if (c==1&&b==29){
				    return;
				 } else if (c==1){ //works
				    while (index.next.cardValue!=27){
				    	index=index.next;
				    }
				    deckRear=index.next;
				 } else if (b==29) {
				    while (index.next.cardValue!=28){
				    	index=index.next;
				    }
				    deckRear=index;
				 } else {
				    while (index.cardValue!=27){
				    	index=index.next;
				    }
				    index.next=a;
				    index=deckRear;
				    while (index.next.cardValue!=28){
				    	index=index.next;
				    }
				    index.next=nb;
				    deckRear.next=nc;
				    deckRear=index;
				 }
		 }
		 
		 index=deckRear;
		 //Debugger  System.out.println("\n"+"-THREE");
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		// COMPLETE THIS METHOD
		
		CardNode index=deckRear;
	    //print initial
	    index=index.next;
	    for(int i=1;i<29;i++){
	    	////Debugger  System.out.print(index.cardValue+",");
	    	index=index.next;
	    }
	    ////Debugger  System.out.println("--------------"+deckRear.cardValue);
	    
	    
	    index = deckRear;
		
	    int length = deckRear.cardValue;
	    int temp = length;
	    if (length==28){
	    	length--;
	    }
	    
	    
	    while (length>0){
	    	index=index.next;
	    	length--;
	    }
	    CardNode a = new CardNode();
	    a.cardValue=temp;
	    a.next=index.next;
	    index.next=a;
	    deckRear=a;
	    index=index.next;
	    while(index.next.cardValue!=temp){
	    	index=index.next;
	    }
	    index.next=index.next.next;
	    index=deckRear;
	    
	    //Debugger  System.out.println("\n"+"-FOUR");
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		// COMPLETE THIS METHOD
		CardNode index=deckRear;
		do {
			index=index.next;
			//Debugger  System.out.print(index.cardValue+",");
		} while (index.cardValue!=deckRear.cardValue);
		
		jokerA();
		index=deckRear;
		do {
			index=index.next;
			//Debugger  System.out.print(index.cardValue+",");
		} while (index.cardValue!=deckRear.cardValue);
		jokerB();
		index=deckRear;
		do {
			index=index.next;
			//Debugger  System.out.print(index.cardValue+",");
		} while (index.cardValue!=deckRear.cardValue);
		tripleCut();
		index=deckRear;
		do {
			index=index.next;
			//Debugger  System.out.print(index.cardValue+",");
		} while (index.cardValue!=deckRear.cardValue);
		countCut();
		index=deckRear;
		do {
			index=index.next;
			//Debugger  System.out.print(index.cardValue+",");
		} while (index.cardValue!=deckRear.cardValue);
		
		
		
		index = deckRear;
		index=index.next; 
		int laststep=index.cardValue;
		if(laststep==28){
			laststep--;
		}
		for(int i=0;i<laststep;i++){
			index=index.next;
		}// count that many cards down and look at the next 
		
		if (index.cardValue==27||index.cardValue==28){
			//Debugger  System.out.println("value was 27 or 28, recursion");
			if(index.cardValue==27){
				//Debugger  System.out.println("it was 27"+"\n"+"again!");
			} else {
				//Debugger  System.out.println("twas 28"+"\n"+"again!");
			}
			return getKey(); //GETKEY
		} else {
			//Debugger  System.out.println("\n"+"KEY:" + index.cardValue + (char)(index.cardValue+64));
			return index.cardValue;
		}
		
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return -1;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		//Debugger  System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			//Debugger  System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		//Debugger  System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		// COMPLETE THIS METHOD
		
		String temp="";
		for (int i=0;i<message.length();i++){
			if (Character.isLetter(message.charAt(i))==true){
				temp=temp+message.charAt(i);
			}
		}
		//Debugger  System.out.println(temp);
		message=temp;
		
		int[] data= new int[message.length()];
		int[] result= new int[message.length()];
		String encrypted="";
		char b;
		
		for (int i=0;i<message.length();i++){
			//letter in
			//Debugger  System.out.println("char: "+message.charAt(i)+" with value "+((int)message.charAt(i)-64));
			data[i]=message.charAt(i)-64;
			//Debugger  System.out.println("data at index "+ i+" , input letter is ith letter of alphabet: "+data[i]);
			result[i]=(data[i]-1+getKey())%26+1;
			//Debugger  System.out.println("result at i, ith letter of the following letter: "+result[i]);
			b=(char)(result[i]+64);
			//Debugger  System.out.println("next letter of encrypted word: "+b);
			encrypted = encrypted + b; 
			//Debugger  System.out.println("encrypted word: "+encrypted);
			//Debugger  System.out.println("------------------");
		}
		//Debugger  System.out.println("ENCRYPT");
		return encrypted;
		
		
		
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return null;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		// COMPLETE THIS METHOD
		
		int[] data= new int[message.length()];
		int[] result= new int[message.length()];
		String decrypted="";
		char b;
		
		for (int i=0;i<message.length();i++){
			//letter in
			//Debugger  System.out.println("char: "+message.charAt(i)+" with value "+((int)message.charAt(i)-64));
			data[i]=message.charAt(i)-64;
			//Debugger  System.out.println("data at index "+ i+" , input letter is ith letter of alphabet: "+data[i]);
			result[i]=(data[i]-1-getKey())%26; //KEY: in console
			if (result[i]<0){
				result[i]=result[i]+26;
			}
			result[i]++;
			//Debugger  System.out.println("result at i, ith letter of the following letter: "+result[i]);
			b=(char)(result[i]+64);
			//Debugger  System.out.println("next letter of encrypted word: "+b);
			decrypted = decrypted + b; 
			//Debugger  System.out.println("encrypted word: "+decrypted);
			//Debugger  System.out.println("------------------");
		}
		//Debugger  System.out.println("DECRYPT");
		return decrypted;
		
		
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return null;
	}
}
