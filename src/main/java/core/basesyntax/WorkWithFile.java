package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        createFile(toFileName);
        int supply = readFile(fromFileName, "supply");
        int buy = readFile(fromFileName, "buy");
        int result = supply - buy;
        writeInFile(toFileName, supply, buy, result);
    }

    private void createFile(String fileName) {
        File fileTo = new File(fileName);
        try {
            fileTo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a new file.");
        }
    }

    private int readFile(String fileName, String target) {
        Path go = Paths.get(fileName);
        File to = go.toFile();
        int result = 0;
        try {
            List<String> list = Files.readAllLines(to.toPath());
            for (String str : list) {
                String[] args = str.split(",");
                if (args[0].equals(target)) {
                    try {
                        result += Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Value is not a number exception");
                    }
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("file not found.", e);
        }
    }

    private void writeInFile(String fileName, int supply, int buy, int result) {
        Path path = Paths.get(fileName);
        File file = path.toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("supply" + "," + supply + System.lineSeparator());
            writer.write("buy" + "," + buy + System.lineSeparator());
            writer.write("result" + "," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("File not found.", e);
        }
    }
}
