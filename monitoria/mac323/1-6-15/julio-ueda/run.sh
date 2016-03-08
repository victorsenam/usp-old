ARGS="300 300 10 10"

javac-algs4 $1.java
java-algs4 $1 $ARGS
echo "------------"
java-algs4 $1 $ARGS |  java-algs4 Transition
echo "------------"
java-algs4 $1 $ARGS |  java-algs4 Transition | java-algs4 RandomSurfer 1000000
