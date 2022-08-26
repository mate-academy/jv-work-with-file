package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {

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
            String[] name = value.split(",", 0);
            if (name[0].equals("supply")) {
                supply += Integer.parseInt(name[1]);
            }
            if (name[0].equals("buy")) {
                buy += Integer.parseInt(name[1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + (supply - buy));
        return stringBuilder.toString();

    }

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> data = getDataFromFile(fromFileName);
        String report = createReport(data);
        writeData(toFileName,report);
    }
}
