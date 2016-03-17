/* #########################################################################
   ############################   Header   #################################
   #########################################################################
   #                                                        			   # 
   #  MAC-0323                                              			   #
   #  Creative Exercise 3.1.52 (Word frequencies; IntroCS)  			   #
   #  Name: Leonardo Araujo Benicio dos Santos              			   #
   #  USP Number: 8536110                                   			   #
   #                                                        			   #
   #                                                        			   #
   #########################################################################
   ##########################   Debugging   ################################
   #########################################################################
   # Compiled: javac-algs4 WordFrequencies.java            	    		   #
   # Executed: java-algs WordFrequencies                     			   #
   # No known bugs                                          			   #
   #                                                        			   #
   #########################################################################

   Problem to be solved:
   http://paca.ime.usp.br/mod/assign/view.php?id=30685

   (3.1.52)
   Write a program (or several programs and use piping) that reads in a text file and 
   prints out a list of the words in decreasing order of frequency. Consider breaking 
   it up into 5 pieces and use piping: read in text and print the words one per line 
   in lowercase, sort to bring identical words together, remove duplicates and print 
   count, sort by count.

   Some Output Examples:
   (using exercise propose as text)
	5 - 0
	a - 3
	and - 5
	breaking - 0
	bring - 0
	by - 0
	consider - 0
	count - 2
	decreasing - 0
	duplicates - 0
	file - 0
	frequency - 0
	identical - 0
	in - 4
	into - 0
	it - 0
	line - 0
	list - 0
	lowercase - 0
	of - 2
	one - 0
	or - 0
	order - 0
	out - 0
	per - 0
	pieces - 0
	piping - 0
	piping: - 0
	print - 2
	prints - 0
	program - 0
	programs - 0
	read - 0
	reads - 0
	remove - 0
	several - 0
	sort - 2
	text - 2
	that - 0
	the - 2
	to - 0
	together - 0
	up - 0
	use - 2
	words - 3

*/
import edu.princeton.cs.algs4.*;
public class WordFrequencies{
	public static void main (String[] args){
		In in = new In();

		// read from standard input (press ctrl + d to stop reading)
		String[] input = in.readAllStrings();

		//remove ponctuation and change all text to lower case
		for (int i = 0; i < input.length; i++){
			input[i] = input[i].replaceAll("[.,!?\\-()]", "").toLowerCase();
		}

		// sort text
		Quick3string.sort(input);

		String aux = "";
		int count = 0;

		if (input.length == 1){
			StdOut.println(input + " - 1");
		}

		for (int i = 1; i < input.length; i++){
			if (input[i-1].compareTo(input[i]) == 0){
				if (count == 0){
					count = 2;
				}
				else {
					count++;
				}
			}
			else {
				StdOut.println(input[i-1] + " - " + count);
				count = 0;
			}
		}

		in.close();

	}
}
