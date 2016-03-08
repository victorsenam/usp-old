javac-algs4 $1.java
java-algs4 $1 10 20 2 2
echo "------------"
java-algs4 $1 10 20 2 2 |  java-algs4 Transition
echo "------------"
java-algs4 $1 10 20 2 2 |  java-algs4 Transition | java-algs4 RandomSurfer 1000000
