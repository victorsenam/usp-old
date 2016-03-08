javac-algs4 $1.java 
time timeout 5s java-algs4 $1 $2 > out$3.txt
java-algs4 Transition < out$3.txt > temp_lala.txt
echo "------------" >> out$3.txt
echo "1"
cat temp_lala.txt >> out$3.txt
echo "------------" >> out$3.txt
java-algs4 RandomSurfer 100 >> out$3.txt
echo "2"
