javac-algs4 $1.java 
time java-algs4 $1 $2 > main-out$3.txt
java-algs4 Transition < main-out$3.txt > trans-out$3.txt
java-algs4 RandomSurfer 10000 < trans-out$3.txt > surf-out$3.txt
