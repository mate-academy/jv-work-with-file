package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private final int[] result = new int[ReportColumn.values().length];

    public void getStatistic(String fromFileName, String toFileName) {
        calculation(read(fromFileName));
        File fileWrite = new File(toFileName);
        clearFile(toFileName);
        write(fileWrite);
    }

    private void clearFile(String path) {
        try (FileWriter clearFile = new FileWriter(path)) {
            clearFile.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void calculation(List<String> list) {
        for (String line : list) {
            String[] operation = line.split(",");
            if (operation[0].equals("supply")) {
                result[ReportColumn.SUPPLY.ordinal()] += Integer.parseInt(line.substring(7));
            } else {
                result[ReportColumn.BUY.ordinal()] += Integer.parseInt(line.substring(4));
            }
        }
        result[ReportColumn.RESULT.ordinal()] =
                result[ReportColumn.SUPPLY.ordinal()] - result[ReportColumn.BUY.ordinal()];
    }

    private void write(File file) {
        for (int i = 0; i < ReportColumn.values().length; i++) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(ReportColumn.values()[i].name().toLowerCase() + ","
                        + result[i] + System.lineSeparator());
                result[i] = 0;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<String> read(String fromFileName) {
        File fileRead = new File(fromFileName);
        List<String> listOfLines;
        try {
            listOfLines = Files.readAllLines(fileRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file ", e);
        }
        return listOfLines;
    }
}
