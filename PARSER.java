import java.io.*;

public class Parser {

    public enum CommandType {
        C_ARITHMETIC, C_PUSH, C_POP
    }

    private BufferedReader br;
    private String currentCommand;

    public Parser(File file) throws Exception {
        br = new BufferedReader(new FileReader(file));
    }

    public boolean hasMoreCommands() throws Exception {
        br.mark(1000);
        if (br.readLine() != null) {
            br.reset();
            return true;
        }
        return false;
    }

    public void advance() throws Exception {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.split("//")[0].trim();
            if (!line.isEmpty()) {
                currentCommand = line;
                return;
            }
        }
    }

    public CommandType commandType() {
        if (currentCommand.startsWith("push")) return CommandType.C_PUSH;
        if (currentCommand.startsWith("pop")) return CommandType.C_POP;
        return CommandType.C_ARITHMETIC;
    }

    public String arg1() {
        if (commandType() == CommandType.C_ARITHMETIC)
            return currentCommand;
        return currentCommand.split(" ")[1];
    }

    public int arg2() {
        return Integer.parseInt(currentCommand.split(" ")[2]);
    }

    public void close() throws Exception {
        br.close();
    }
}
