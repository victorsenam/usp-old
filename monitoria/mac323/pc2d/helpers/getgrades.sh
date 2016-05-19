#!/bin/bash

## Isso descompacta .tar, .tar.gz e .zip
initial=$(cd $(dirname $0) && cd .. && pwd)
cd $initial

rm -r grades
mkdir grades

for aluno in $(ls result);
do
    cat result/$aluno/judge.out > grades/$aluno
done

for aluno in $(ls special);
do
    cat "CORRECAO MANUAL\n" special/$aluno/comment.txt special/$aluno/judge.out > grades/$aluno
done
