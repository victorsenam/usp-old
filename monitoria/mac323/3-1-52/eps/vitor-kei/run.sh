run="java-algs4 readText" 

javac-algs4 WordFrequencies.java

time $run < ../../text/clear_1.txt | java-algs4 Quick3way | java-algs4 WordFrequencies | tee result_1.txt
diff -w result_1.txt ../../output/out_1.txt > diff_1.txt

time $run < ../../text/clear_2.txt | java-algs4 Quick3way | java-algs4 WordFrequencies | tee result_2.txt
diff -w result_2.txt ../../output/out_2.txt > diff_2.txt
