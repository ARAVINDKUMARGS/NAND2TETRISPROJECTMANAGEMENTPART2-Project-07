import java.io.*;

public class CodeWriter {

    private BufferedWriter bw;
    private String fileName;
    private static int labelCount = 0;

    public CodeWriter(String outputFile, String inputFile) throws Exception {
        bw = new BufferedWriter(new FileWriter(outputFile));
        fileName = inputFile.replace(".vm", "");
    }

    // ---------------- ARITHMETIC ----------------
    public void writeArithmetic(String command) throws Exception {

        if (command.equals("add") || command.equals("sub")
                || command.equals("and") || command.equals("or")) {

            popToD();
            bw.write("@SP\nAM=M-1\n");

            switch (command) {
                case "add": bw.write("M=M+D\n"); break;
                case "sub": bw.write("M=M-D\n"); break;
                case "and": bw.write("M=M&D\n"); break;
                case "or":  bw.write("M=M|D\n"); break;
            }

        } else if (command.equals("neg") || command.equals("not")) {
            bw.write("@SP\nA=M-1\n");
            bw.write(command.equals("neg") ? "M=-M\n" : "M=!M\n");

        } else { // eq, gt, lt
            String TRUE = "TRUE_" + labelCount;
            String END = "END_" + labelCount++;

            popToD();
            bw.write("@SP\nAM=M-1\nD=M-D\n@" + TRUE + "\n");

            if (command.equals("eq")) bw.write("D;JEQ\n");
            if (command.equals("gt")) bw.write("D;JGT\n");
            if (command.equals("lt")) bw.write("D;JLT\n");

            bw.write("@SP\nA=M\nM=0\n@" + END + "\n0;JMP\n");
            bw.write("(" + TRUE + ")\n@SP\nA=M\nM=-1\n");
            bw.write("(" + END + ")\n@SP\nM=M+1\n");
        }
    }

    // ---------------- PUSH / POP ----------------
    public void writePushPop(Parser.CommandType type, String segment, int index) throws Exception {

        if (type == Parser.CommandType.C_PUSH) {
            if (segment.equals("constant")) {
                bw.write("@" + index + "\nD=A\n");
            } else {
                writeSegment(segment, index);
                bw.write("D=M\n");
            }
            pushD();

        } else { // POP
            writeSegment(segment, index);
            bw.write("@R13\nM=D\n");
            popToD();
            bw.write("@R13\nA=M\nM=D\n");
        }
    }

    private void writeSegment(String seg, int idx) throws Exception {
        switch (seg) {
            case "local":    bw.write("@LCL\nD=M\n"); break;
            case "argument": bw.write("@ARG\nD=M\n"); break;
            case "this":     bw.write("@THIS\nD=M\n"); break;
            case "that":     bw.write("@THAT\nD=M\n"); break;
            case "temp":     bw.write("@5\nD=A\n"); break;
            case "pointer":
                bw.write(idx == 0 ? "@THIS\n" : "@THAT\n");
                bw.write("D=A\n");
                return;
            case "static":
                bw.write("@" + fileName + "." + idx + "\nD=A\n");
                return;
        }
        bw.write("@" + idx + "\nD=D+A\n");
    }

    // ---------------- STACK HELPERS ----------------
    private void pushD() throws Exception {
        bw.write("@SP\nA=M\nM=D\n@SP\nM=M+1\n");
    }

    private void popToD() throws Exception {
        bw.write("@SP\nAM=M-1\nD=M\n");
    }

    public void close() throws Exception {
        bw.close();
    }
}
