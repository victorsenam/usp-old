tempfile="tempfile.aux"

gen_densas="0genmatsim.c"
gen_exemplo="0exemplo.cpp"
gen_esparsas="spdmat.cpp"

to_densas="densas"
to_exemplo="exemplo"
to_esparsas="esparsas"

compile_file="generator.o"


echo "Gerando matrizes de exemplo do livro"
if [ -f $gen_exemplo ];
then
    echo "Compilando gerador"
    g++ $gen_exemplo -o $compile_file

    mkdir $to_exemplo

    array=(01 02 1)
    for i in "${array[@]}"
    do
        echo "500 0.$i" | ./$compile_file > $to_exemplo/$i.in
        echo ":norm GG
:d
:0
:norm p
:wq" | vim -E -s $to_exemplo/$i.in
    done
else
    echo "O arquivo para gerar matrizes de exemplo não existe ou não tem o nome $gen_exemplo".
fi

echo "Gerando matrizes densas"
if [ -f $gen_densas ];
then
    echo "Compilando gerador"
    g++ $gen_densas -o $compile_file

    mkdir $to_densas

    array=(100 500 700)
    for i in "${array[@]}"
    do
        echo "$i $to_densas/$i.in" | ./$compile_file
    done
else
    echo "O arquivo para gerar matrizes densas não existe ou não tem o nome $gen_densas".
fi

echo "Gerando matrizes esparsas"
if [ -f $gen_esparsas ];
then
    echo "Compilando gerador"
    g++ $gen_esparsas -o $compile_file

    mkdir $to_esparsas

    array=( "10_7" "100_10" "100000_200" "1000000_307" )
    for i in "${array[@]}"
    do
        echo "$i" | ./$compile_file > $to_esparsas/$i.in
    done
else
    echo "O arquivo para gerar matrizes esparsas não existe ou não tem o nome $gen_esparsas".
fi
