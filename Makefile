TARGET = EqBool
TEST = test.eq
METHODE = programme

all:
	java -jar ../antlr-4.9-complete.jar $(TARGET)Lexer.g4
	java -jar ../antlr-4.9-complete.jar $(TARGET)Parser.g4
	javac *.java

run:
	java org.antlr.v4.gui.TestRig $(TARGET) $(METHODE) $(TEST)

clean:
	rm -f *.class *.java *.tokens *.interp
	rm -rf circuits/*.class
