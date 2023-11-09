package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int OPERATIONAL_INDEX = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String createReport(String[] readedLine) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        for (String s : readedLine) {
            String[] tempArray = s.split(",");
            if (tempArray[OPERATIONAL_INDEX].equals("supply")) {
                supply += Integer.parseInt(tempArray[VALUE_INDEX]);
            } else if (tempArray[OPERATIONAL_INDEX].equals("buy")) {
                buy += Integer.parseInt(tempArray[VALUE_INDEX]);
            }
        }

        result = supply - buy;
        return new StringBuilder(SUPPLY).append(SEPARATOR).append(supply)
                .append(System.lineSeparator()).append(BUY).append(SEPARATOR)
                .append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result)
                .append(System.lineSeparator()).toString();
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file" + file, e);
        }
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder tempString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                tempString.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file! " + fromFileName, e);
        }
        String[] data = tempString.toString().split(System.lineSeparator());
        return data;
    }
}
