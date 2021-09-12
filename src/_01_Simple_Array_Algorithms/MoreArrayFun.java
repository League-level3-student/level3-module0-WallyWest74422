package _01_Simple_Array_Algorithms;

import java.util.Random;

public class MoreArrayFun {
    //1. Create a main method to test the other methods you write.
public static void main(String[] args) {
    int[] array = new int[6];
    
    array[0] = 1;
    array[1] = 2;
    array[2] = 3;
    array[3] = 4;
    array[4] = 5;
    array[5] = 6;
   printAll(array);
  printReverse(array);
    String[] array2 = new String[6] ;
    
    array2[0]= "One";
    array2[1]= "Two";
    array2[2]= "Three";
    array2[3]= "Four";
    array2[4]= "Five";
    array2[5]= "Six";
   printEveryOther(array2);
    printRandom(array2);
}


  //2. Write a method that takes an array of Strings and prints all the Strings in the array.
static void printAll(int[] array){
	for (int i = 0; i < array.length; i++) {
		System.out.println(array[i]);
	}
}


    //3. Write a method that takes an array of Strings and prints all the Strings in the array
    //   in reverse order.
static void printReverse(int[] array){
	for (int j = array.length-1; j < 0; j--) {
		System.out.println(array[j]);
	}
}


    //4. Write a method that takes an array of Strings and prints every other String in the array.
static void printEveryOther(String [] array) {
	for(int k = 0; k < array.length; k+=2) {
		System.out.println(array[k]);
	}
}

    //5. Write a method that takes an array of Strings and prints all the Strings in the array
    //   in a completely random order. Almost every run of the program should result in a different order.
static void printRandom (String [] array) {
	 String[] array2 = new String[array.length] ;
	 int Counter = 0;
	 Random ran = new Random();
while(Counter<array.length) {
	
	String Aname= array [ran.nextInt(array.length)];
	boolean Found = false;
	for(int i = 0; i<Counter; i++) {
		if(Aname.equals(array2[i])) {
			Found = true; 
			break;
		}
	} if(Found == false) {
		System.out.println(Aname);
		array2[Counter] = Aname;
		Counter = Counter + 1;
	}
}

}

}
