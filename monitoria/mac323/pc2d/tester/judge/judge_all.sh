#!/bin/bash

# PARÂMETROS
# 1 - Pasta com as soluções a serem testadas
# 2 (opcional) - Regex para escolher as pastas a serem testadas

# important vars
DIR="$( cd "$( dirname "$0" )" && cd ../../ && pwd )/"
LOG="${DIR}judge.log"
RES="${DIR}judge.out"
RUNNER="${DIR}judge.sh"

# parameters
if [[ $# -eq 0 ]];
then
    echo "NO ARGUMENTS PROVIDED (judge_all)"
    exit 0
fi
folder=$1
pattern=${2-*/}

# going to wanted folder
cd $folder

# resetting
echo "" > $LOG
echo "" > $RES

for aluno in $pattern;
do
     echo "$aluno"

done
