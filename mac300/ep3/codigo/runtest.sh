FOLDER=$1
FILE=$2
AUX="tmp"

mkdir $AUX

make $FOLDER/gen
./$FOLDER/gen $AUX/base.out $AUX/points.out < $FOLDER/$FILE > $AUX/result.out

make main
./main < data.dat > $AUX/result.out

cat $AUX/base.out $AUX/result.out > $AUX/resplot.out

make helpers/plotter
./helpers/plotter < $AUX/resplot.out > $AUX/plot.dat

gnuplot "$AUX/points.out" with dots, "$AUX/plot.dat" with lines
