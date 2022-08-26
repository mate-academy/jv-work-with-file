package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> data = getDataFromFile(fromFileName);
        String report = createReport(data);
        writeData(toFileName, report);
    }

    private ArrayList<String> getDataFromFile(String fromFileName) {
        ArrayList<String> dataFile = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                dataFile.add(value);
                value = bufferedReader.readLine();
            }
            return dataFile;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read line from the file ", e);
        }
    }

    private void writeData(String toFileName,String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private String createReport(ArrayList<String> file) {
        int supply = 0;
        int buy = 0;
        for (String value : file) {
            String[] name = value.split(",");
            if (name[OPERATION_INDEX].equals("supply")) {
                supply += Integer.parseInt(name[AMOUNT_INDEX]);
            }
            if (name[OPERATION_INDEX].equals("buy")) {
                buy += Integer.parseInt(name[AMOUNT_INDEX]);
            }
        }
        return new StringBuilder().append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + (supply - buy)).toString();
    }
}
