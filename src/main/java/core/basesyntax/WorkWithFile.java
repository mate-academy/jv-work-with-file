package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "[^A-Za-z0-9 ]+";
    private String tempString;
    private int supply = 0;
    private int buy = 0;
    private String result;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.read(fromFileName);
        workWithFile.calculate();
        workWithFile.write(toFileName);
    }

    public void read(String fromFileName) {
        File fileFrom = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            tempString = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        }
    }

    public void calculate() {
        String[] tempStringArray = tempString.split(REGEX);
        for (int i = 0; i < tempStringArray.length; i = i + 2) {
            tempString = tempStringArray[i];
            if (tempString.startsWith("s")) {
                supply = supply + Integer.parseInt(String.valueOf(tempStringArray[i + 1]));
            } else {
                buy = buy + Integer.parseInt(String.valueOf(tempStringArray[i + 1]));
            }
        }
        result = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator() + "result," + (supply - buy);
    }

    public void write(String toFileName) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}



