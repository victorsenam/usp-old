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
rm -r $OUT &>> $LOG
mkdir $OUT &>> $LOG
r_ac=0
r_tl=0
r_wa=0
r_re=0
r_tm=0

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
    testpath="$cases/unique_test.in"
    
    if [ -a saida.txt ];
    then
        rm saida.txt
    fi
    touch saida.txt

    (time java -cp .:algs4.jar:stdlib.jar $toexec < $testpath 2>> $LOG) 2>> $LOG
    run_status=$?

    for casepath in $(find $OUT/*);
    do
        testcase=$(basename $casepath)
        testcase=${testcase%.*}
        veredict="F"
        
        echo "====== $testcase =========" >> $LOG
        
        if grep -q "TEMPO DE EXECUCAO EXCEDIDO" $casepath;
        then
            ((r_tl++))
            echo "Tempo Excedido" >> $LOG
            veredict="T"
        elif grep -q "TEMPO DE MONTAGEM EXCEDIDO" $casepath;
        then
            ((r_tm++))
            echo "Tempo de Montagem Excedido" >> $LOG
            veredict="M"
        elif grep -q "ERRO DE EXECUCAO" $casepath;
        then
            ((r_re++))
            echo "Erro de Execução" >> $LOG
            veredict="R"
        else
            sort ${OUT}${testcase}.out -o ${OUT}${testcase}.out
            checker_output=$( ${DIR}tester/checker/checker $testpath ${OUT}${testcase}.out ${DIR}tester/solution/answer/${testcase}.out 2>&1)
            echo $checker_output >> $LOG
            if [[ ${checker_output:0:1} == "o" ]]
            then
                ((r_ac++))
                echo "OK" >> $LOG
                veredict="."
            else
                ((r_wa++))
                echo "Resposta Errada" >> $LOG
                veredict="W"
            fi
        fi

        printf $veredict
        printf $veredict >> $RES
    done;
else
    echo "COMPILATION ERROR" >> $RES
fi

printf "\n"
printf "\n" >> $RES

echo "CORREÇÃO AUTOMÁTICA" >> $RES
echo "Quantidade de Testes | Veredito" >> $RES
echo "$r_ac | OK" >> $RES
echo "$r_tm | Tempo de Montagem Excedido (o grafo dessa query não pode ser montado)" >> $RES
echo "$r_tl | Tempo de Execução Excedido" >> $RES
echo "$r_wa | Resposta Errada" >> $RES
echo "$r_re | Erro de Execução" >> $RES
