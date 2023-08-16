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
        makeWrittenFile(stringStat(calculateArrayOfStat(stringDataReport(fromFileName))),
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
            throw new RuntimeException("Can't read file", e);
        }
        String[] text = stringBuilder.toString().split(System.lineSeparator());
        return text;
    }

    private int[] calculateArrayOfStat(String[] text) {
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
        int[] arrayOfStat = new int[]{supply, buy, result};
        return arrayOfStat;
    }

    private StringBuilder stringStat(int[] arrayOfStat) {
        int supply = arrayOfStat[0];
        int buy = arrayOfStat[1];
        int result = arrayOfStat[2];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + result + System.lineSeparator());
        return stringBuilder;
    }

    private boolean createFile(File toFileName) {
        try {
            toFileName.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create this file", e);
        }
        return true;
    }

    private void fileExistCheck(File toFileName) {
        if (toFileName.exists()) {
            toFileName.delete();
        }
    }

    private void makeWrittenFile(StringBuilder stringBuilder, File toFileName) {
        String[] stats = stringBuilder.toString().split(System.lineSeparator());
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
