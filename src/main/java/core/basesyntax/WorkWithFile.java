package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "[^A-Za-z0-9 ]+";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        }
    }

    public String createReport(String dataFromFile) {
        String tempString = dataFromFile;
        StringBuilder report = new StringBuilder();
        int supply = 0;
        int buy = 0;
        String[] tempStringArray = tempString.split(REGEX);
        for (int i = 0; i < tempStringArray.length; i = i + 2) {
            tempString = tempStringArray[i];
            if (tempString.startsWith("s")) {
                supply = supply + Integer.parseInt(String.valueOf(tempStringArray[i + 1]));
            } else {
                buy = buy + Integer.parseInt(String.valueOf(tempStringArray[i + 1]));
            }
        }
        return report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                .append((supply - buy)).toString();
    }

    public void writeToFile(String toFileName, String report) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}



