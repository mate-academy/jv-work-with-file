package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String REG_EX = "\\s";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName);
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lineSplit;

        for (String line : lines) {
            lineSplit = line.split(",");

            if (lineSplit.length == 2) {

                switch (lineSplit[0]) {
                    case "buy":
                        totalBuy += Integer.parseInt(lineSplit[1]);
                        break;
                    case "supply":
                        totalSupply += Integer.parseInt(lineSplit[1]);
                        break;
                    default:
                        break;
                }
            }
        }

        createReport(totalBuy, totalSupply, toFileName);

    }

    private String[] readFile(String filename) {
        File file = new File(filename);
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int symbol = reader.read();

            while (symbol != -1) {
                result.appendCodePoint(symbol);
                symbol = reader.read();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return result.toString().split(REG_EX);
    }

    private void createReport(int buy, int supply, String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create file " + filename, e);
            }
        }

        int result = supply - buy;
        String report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;

        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + filename, e);
        }

    }
}
