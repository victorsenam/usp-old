#!/bin/sh

cd ..
initial=$(pwd)

rm -r cases
mkdir cases

make generator/generator

echo "$BASH_VERSION"
for i in `seq 0 9`
do
    echo "Gera $i"
done;
