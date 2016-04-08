#!/bin/bash

### REVISAR ANTES DE USAR ###

cd ..
initial=$(pwd)

rm -r notas
mkdir "notas"

cd result
for aluno in */;
do
    cd "$initial/result/"
    cd $aluno

    echo $aluno
    
    cp -r "$initial/tester/Tester.java" .
    javac-algs4 Tester.java
    java-algs4 Tester > "$initial/notas/${aluno%/}.txt"

    exit
done
