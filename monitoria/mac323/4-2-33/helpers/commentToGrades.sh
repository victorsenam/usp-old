#!/bin/bash

initial=$(cd $(dirname $0) && cd .. && pwd)
cd $initial

mkdir grades
cd result

for aluno in $(find . -maxdepth 1 -mindepth 1 -type d);
do
    cat $aluno/judge.out $aluno/comment.txt $initial/grades/$(basename $aluno).txt
done
