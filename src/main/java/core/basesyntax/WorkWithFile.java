package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final int NAME = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = readFromFile(file);
        String report = createReport(stringBuilder);
        writeInfoToFile(toFileName, report);
    }

    private static void writeInfoToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write info to file " + e);
        }
    }

    private static String createReport(StringBuilder stringBuilder) {
        String[] infoFromFile = stringBuilder.toString().split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : infoFromFile) {
            String[] lineInfo = line.split(",");
            if (lineInfo[NAME].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(lineInfo[AMOUNT]);
            } else {
                totalBuy += Integer.parseInt(lineInfo[AMOUNT]);
            }
        }
        return new StringBuilder()
                .append("supply,")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(totalSupply - totalBuy).toString();

    }

    private static StringBuilder readFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find file to  read " + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + e);
        }
        return stringBuilder;
    }

}
