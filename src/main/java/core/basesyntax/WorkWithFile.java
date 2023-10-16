package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    static final String SEPARATOR = ",";
    static final int DESCRIPTION_OF_THE_LIST = 0;
    static final int VALUE_OF_THE_LIST = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(makeReport(readFromFile(fromFileName)),toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                list.add(value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file" + fromFileName, e);
        }
        return list.toArray(new String[0]);
    }

    private String makeReport(String[] arrayFromFile) {
        int supply = 0;
        int buy = 0;
        for (String s : arrayFromFile) {
            String[] split = s.split(SEPARATOR);
            if (split[DESCRIPTION_OF_THE_LIST].startsWith("s")) {
                supply += Integer.parseInt(split[VALUE_OF_THE_LIST]);
            } else {
                buy += Integer.parseInt(split[VALUE_OF_THE_LIST]);
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeToFile(String dataToWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + toFileName, e);
        }
    }
}
