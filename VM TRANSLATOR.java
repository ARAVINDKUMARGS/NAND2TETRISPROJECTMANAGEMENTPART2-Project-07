import java.io.*;

public class VMTranslator {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: VMTranslator file.vm");
            return;
        }

        File inputFile = new File(args[0]);
        String outputFile = inputFile.getAbsolutePath().replace(".vm", ".asm");

        Parser parser = new Parser(inputFile);
        CodeWriter codeWriter = new CodeWriter(outputFile, inputFile.getName());

        while (parser.hasMoreCommands()) {
            parser.advance();
            switch (parser.commandType()) {
                case C_ARITHMETIC:
                    codeWriter.writeArithmetic(parser.arg1());
                    break;
                case C_PUSH:
                case C_POP:
                    codeWriter.writePushPop(
                        parser.commandType(),
                        parser.arg1(),
                        parser.arg2()
                    );
                    break;
            }
        }

        codeWriter.close();
        parser.close();
    }
}
