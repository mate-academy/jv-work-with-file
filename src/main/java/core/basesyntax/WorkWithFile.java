package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(report, toFileName);
    }

    public String readFile(String fromFileName) {
        try {
            return Files.readString(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file" + fromFileName, e);
        }
    }

    public String createReport(String fileData) {
        int sumBuy = 0;
        int sumSupply = 0;
        String[] dataLines = fileData.split(System.lineSeparator());
        for (String line: dataLines) {
            String[] lineInfo = line.split(COMMA);
            if (lineInfo[OPERATION_INDEX].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(lineInfo[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(lineInfo[AMOUNT_INDEX]);
            }
        }
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(sumSupply - sumBuy).toString();
    }

    public void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(toFile)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant` write in file" + toFileName, e);
        }
    }
}
