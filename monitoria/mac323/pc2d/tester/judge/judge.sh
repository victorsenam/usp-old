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
cp -n ${DIR}tester/standart/* .
cp -n ${DIR}tester/judge/.gitignore .
javac -cp .:algs4.jar:stdlib.jar *.java &>> $LOG

toexec=failed
if [ -a PC2D.class ]
then
    toexec=PC2D
fi

if [ $toexec != failed ];
then
    for testpath in $(find $cases/*);
    do
        testcase=$(basename $testpath)
        testcase=${testcase%.*}
        
        echo "======= $testcase ============" >> $LOG

        (time timeout --kill-after=12s 10s java -cp .:algs4.jar:stdlib.jar $toexec < ${cases}/${testcase}.in > ${OUT}${testcase}.out 2>> $LOG) &>> $LOG
        run_status=$?

        if [ $run_status -eq 124 ]
        then
            ((r_tl++))
            echo "Tempo Excedido" >> $LOG
            printf "T"
        elif [ $run_status -eq 0 ]
        then
            checker_status=0
            if [ $checker_status -eq 0 ]
            then
                ((r_ac++))
                echo "OK" >> $LOG
                printf "."
            else
                ((r_wa++))
                echo "Resposta Errada" >> $LOG
                printf "W"
            fi
        else
            ((r_re++))
            echo "Erro de Execução" >> $LOG
            printf "R"
        fi
        
    done;
else
    echo "FAILED COMPILE" >> $LOG
fi
printf "\n"

echo "CORREÇÃO AUTOMÁTICA" > $RES
echo "Quantidade de Testes | Veredito" >> $RES
echo "$r_ac | OK" >> $RES
echo "$r_tl | Tempo de Execução Excedido" >> $RES
echo "$r_wa | Resposta Errada" >> $RES
echo "$r_re | Erro de Execução" >> $RES
