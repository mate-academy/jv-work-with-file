package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        write(calculation(read(fromFileName)), toFileName);
    }

    private int[] calculation(List<String> list) {
        int[] result = new int[ReportColumn.values().length];
        for (String line : list) {
            String[] operation = line.split(COMMA);
            if (operation[0].equals(ReportColumn.values()[0].name().toLowerCase())) {
                result[ReportColumn.SUPPLY.ordinal()] += Integer.parseInt(operation[1]);
            } else {
                result[ReportColumn.BUY.ordinal()] += Integer.parseInt(operation[1]);
            }
        }
        result[ReportColumn.RESULT.ordinal()] =
                result[ReportColumn.SUPPLY.ordinal()] - result[ReportColumn.BUY.ordinal()];
        return result;
    }

    private void write(int[] info, String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ReportColumn.values().length; i++) {
            stringBuilder.append(ReportColumn.values()[i].name().toLowerCase())
                    .append(COMMA).append(info[i])
                    .append(System.lineSeparator());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> read(String fromFileName) {
        File fileRead = new File(fromFileName);
        try {
            return Files.readAllLines(fileRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file ", e);
        }
    }
}
