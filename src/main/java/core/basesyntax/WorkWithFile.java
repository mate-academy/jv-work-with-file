package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(stringDataReport(fromFileName)),
                new File(toFileName));
    }

    private String[] stringDataReport(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private StringBuilder createReport(String[] text) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] strings;
        for (String s : text) {
            strings = s.split(SEPARATOR);
            if (strings[0].equals(SUPPLY)) {
                supply += Integer.valueOf(strings[1]);
            } else if (strings[0].equals(BUY)) {
                buy += Integer.valueOf(strings[1]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        return stringBuilder;
    }

    private boolean createFile(File toFileName) {
        try {
            toFileName.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create this file" + toFileName, e);
        }
        return true;
    }

    private void fileExistCheck(File toFileName) {
        if (toFileName.exists()) {
            toFileName.delete();
        }
    }

    private void writeToFile(StringBuilder statistic, File toFileName) {
        String[] stats = statistic.toString().split(System.lineSeparator());
        fileExistCheck(toFileName);
        createFile(toFileName);
        for (String stat : stats) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(toFileName, true))) {
                bufferedWriter.write(stat + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write this file", e);
            }
        }
    }
}
