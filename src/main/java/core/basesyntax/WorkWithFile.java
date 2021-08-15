package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String REG_EX = "\\s";

    public void getStatistic(String fromFileName, String toFileName) {

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

    private void createReport(int[] buyArray, int[] supplyArray, String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create file " + filename, e);
            }
        }

        int totalBuy = 0;
        int totalSupply = 0;

        for (int buy : buyArray) {
            totalBuy += buy;
        }

        for (int supply : supplyArray) {
            totalSupply += supply;
        }

        int result = totalSupply - totalBuy;
        String report = "supply," + totalSupply + System.lineSeparator()
                + "buy," + totalBuy + System.lineSeparator()
                + "result," + result;

        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + filename, e);
        }

    }
}
