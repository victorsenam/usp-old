tempfile="tempfile.aux"

gen_densas="genmatsim.c"
gen_exemplo="exemplo.cpp"

to_densas="densas"
to_exemplo="exemplo"

compile_file="generator.o"


echo "Gerando matries de exemplo do livro"
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

echo "Gerando matries densas"
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
