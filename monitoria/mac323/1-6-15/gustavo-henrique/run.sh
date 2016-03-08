javac-algs4 $1.java 
time java-algs4 $1 $2
java-algs4 $1 $2 > out$3.txt
echo "------------" >> out$3.txt
java-algs4 $1 $2 |  java-algs4 Transition >> out$3.txt
echo "------------" >> out$3.txt
java-algs4 $1 $2 |  java-algs4 Transition | java-algs4 RandomSurfer 1000000  >> out$3.txt
