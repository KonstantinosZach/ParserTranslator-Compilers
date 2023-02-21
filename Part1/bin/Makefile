JFLAGS = -g	-Werror
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		Main.java \
		ParseError.java\
		Calculator.java\

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) Main.class
	$(RM) ParseError.class
	$(RM) Calculator.class

run:
	java Main