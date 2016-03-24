# RandomWalker
file=${4-'RandomWalker'}
run=${5-"java-algs4 ${file}"}

javac-algs4 "${file}.java"

time $run 1000

# Distribution
file=${2-'Distribution'}
run=${3-"java-algs4 ${file}"}

javac-algs4 "${file}.java"

time $run 1000 1000 $1 
time $run 100000 1 $1 
time $run 10 100000 $1 
time $run 10 100 $1
