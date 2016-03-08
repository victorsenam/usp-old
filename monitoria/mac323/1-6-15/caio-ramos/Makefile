all:
	javac-introcs Transition.java
	javac-introcs Generator.java
	javac-introcs RandomSurferHistogram.java
clean:
	rm *.class
run:
	java-introcs Generator 40 80 1 | java-introcs Transition | java-introcs RandomSurferHistogram 10000
