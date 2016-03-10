#!/bin/bash

java-algs4 MyGraph $1 $2 $3 > resultado.txt

java-algs4 Transition < resultado.txt | java-algs4 RandomSurfer 1000000 | sort

#$1 := num de paginas
#$2 := num de hubs
#$1 := num de authorities
