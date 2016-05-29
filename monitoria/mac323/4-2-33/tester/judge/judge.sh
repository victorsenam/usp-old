#!/bin/bash

# Parâmetros
# 1 - Pasta da solução a ser testada
# 2 (opcional) - Pasta contendo os testes a serem rodados
# 3 (opcional) - Pasta contendo uma solulção válida

# important vars
DIR="$( cd "$( dirname "$0" )" && cd ../../ && pwd )/"
LOG="judge.log"
RES="judge.out"
OUT="judge_output/"

# parameters
if [[ $# -eq 0 ]];
then
    echo "NO ARGUMENTS PROVIDED"
    exit 1
fi
folder=$1
cases=${DIR}${2-'tester/cases/'}
anwsers=${DIR}${3-'tester/solution/anwser/'}

# going to wanted folder
cd $folder

# resetting
echo "" > $LOG
echo "" > $RES
mkdir $OUT &>> $LOG
r_ac=0
r_tl=0
r_wa=0
r_re=0

# compiling
rm CorretorDoVictaum.java
make ${DIR}tester/checker/checker &>> $LOG
cp -n ${DIR}tester/standart/* . &>> $LOG
cp -n ${DIR}tester/judge/.gitignore . &>> $LOG
rm *.class
javac -cp .:algs4.jar:stdlib.jar *.java &>> $LOG

toexec=failed
if [ -a CorretorDoVictaum.class ];
then
    toexec=CorretorDoVictaum
fi

# second compile method
# if [ $toexec == failed ];
if [ "lalal" == "oasda" ];
then
    echo "Second Compile Method" &>> $LOG

    rm *.class
    echo -e "import edu.princeton.cs.algs4.*;\nimport java.util.*;\n$(cat WordDAG.java)" > WordDAG.java
    javac -cp .:algs4.jar:stdlib.jar *.java &>> $LOG
    
    if [ -a CorretorDoVictaum.class ];
    then
        toexec=CorretorDoVictaum
    fi
fi

if [ $toexec != failed ];
then
    for testpath in $(find $cases/*);
    do
        testcase=$(basename $testpath)
        testcase=${testcase%.*}
        
        echo "======= $testcase ============" >> $LOG
        echo "======= $testcase ============" >> $RES

        if [ -a saida.txt ];
        then
            rm saida.txt
        fi
        touch saida.txt

        (time java -cp .:algs4.jar:stdlib.jar $toexec < $testpath >> $RES 2>> $LOG) &>> $LOG
        run_status=$?
    done;
else
    echo "COMPILATION ERROR" >> $RES
fi
