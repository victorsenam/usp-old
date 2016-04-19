#!/bin/bash

cd ..
initial=$(pwd)

rm -fr notas
mkdir "notas"

folder=${1-"result"}

cd $folder

for aluno in */;
do
    cd "$initial/$folder/"
    cd "$aluno"

    r_ac=0
    r_tl=0
    r_wa=0
    r_re=0

    echo $aluno

    cp -n $initial/standart/* .

    rm -r output || true
    mkdir output
    javac -cp .:algs4.jar:stdlib.jar *.java &> verbose

    if [ $? -eq 0 ];
    then
        for testcase in $(find $initial/tester/input/in*);
        do
            number=${testcase:(-3)}

            echo "===================" >> verbose
            echo "TESTE $number" >> verbose

            (time timeout --kill-after=7s 5s java -cp .:algs4.jar:stdlib.jar LevelTraversal < $initial/tester/input/in$number > output/out$number 2>> verbose) &>> verbose
            exitstatus=$?

            if [ $exitstatus -eq 124 ]
            then
                ((r_tl++))
                echo "Tempo Excedido" >> verbose
            else 
                if [ $exitstatus -eq 0 ]
                then
                    diff output/out$number $initial/tester/output/out$number -bEB >> verbose
                    if [ $? -eq 0 ]
                    then
                        ((r_ac++))
                        echo "OK" >> verbose
                    else
                        ((r_wa++))
                        echo "Resposta Errada" >> verbose
                    fi
                else
                    ((r_re++))
                    echo "Erro de Execução" >> verbose
                fi
            fi
        done;
    else
        echo "failed compile"
    fi

    echo "Quantidade de Testes | Veredito" > $initial/notas/${aluno%/}.txt
    echo "$r_ac | OK" >> $initial/notas/${aluno%/}.txt
    echo "$r_tl | Tempo de Execução Excedido" >> $initial/notas/${aluno%/}.txt
    echo "$r_wa | Resposta Errada" >> $initial/notas/${aluno%/}.txt
    echo "$r_re | Erro de Execução" >> $initial/notas/${aluno%/}.txt

done
