all: compile

compile:
	jflex scanner.flex
	java -jar ../java-cup-11b.jar -interface -parser Parser parser.cup
	javac -cp ../java-cup-11b-runtime.jar *.java

execute:
	@java -cp ../java-cup-11b-runtime.jar:. Driver > Main.java
	@cat Main.java
	@javac Main.java
	@echo output:
	@java Main

clean:
	rm -f *.class *~ Main.java Parser.java Scanner.java sym.java
