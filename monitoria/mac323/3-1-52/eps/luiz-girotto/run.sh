run="java-algs4 WordFrequencies"
post="| java-algs4 Quick | java-algs4 WordFrequencies2 | java-algs4 MyQuickSort "

javac-algs4 WordFrequencies.java

time $run < ../../text/clear_1.txt $post > result_1.txt
diff -w result_1.txt ../../output/out_1.txt > diff_1.txt

time $run < ../../text/clear_2.txt $post > result_2.txt
diff -w result_2.txt ../../output/out_2.txt > diff_2.txt
