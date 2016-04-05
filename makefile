JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Word.java \
        NaiveBayesClassifier.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
