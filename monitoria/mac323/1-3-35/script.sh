#!/bin/bash
initial=$(pwd)

cd batch
for file in *.tar;
do
    name=${file// /-}
    name=${name%.*}
    mkdir "$initial/result/$name"
    mv "$file" "$initial/result/$name/"
done
