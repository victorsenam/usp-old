run="java-algs4 $1"
post="| java-algs4 SortLines | java-algs4 CountLines | java-algs4 SortNumberLast"

javac-algs4 $1.java

time $run < ../../text/clear_1.txt $post > result_1.txt
diff result_1.txt ../../output/out_1.txt > diff_1.txt

time $run < ../../text/clear_2.txt $post > result_2.txt
diff result_2.txt ../../output/out_2.txt > diff_2.txt
