package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private File fileRead;
    private File fileWrite;

    public void getStatistic(String fromFileName, String toFileName) {
        String [] dataFromFile = read(fromFileName);
        String report = createReport(dataFromFile);
        write(report, toFileName);
    }

    public String [] read(String string) {
        fileRead = new File(string);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRead));
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data file" + fileRead, e);
        }
        String readFile = builder.toString();
        String[] data = readFile.split(System.lineSeparator());
        return data;
    }

    public String createReport(String [] data) {
        int supply = 0;
        int buy = 0;
        for (String record : data) {
            String[] splittedRecord = record.split(",");
            if (splittedRecord[0].equals("supply")) {
                supply = supply + Integer.parseInt(splittedRecord[1]);
            } else if (splittedRecord[0].equals("buy")) {
                buy = buy + Integer.parseInt(splittedRecord[1]);
            }
        }
        int result = supply - buy;
        String supply1 = "supply," + supply;
        String buy1 = "buy," + buy;
        String result1 = "result," + result;
        String dataFromFile = supply1 + System.lineSeparator() + buy1 + System.lineSeparator()
                + result1 + System.lineSeparator();;
        return dataFromFile;
    }

    public void write(String report, String fromFile) {
        fileWrite = new File(fromFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite,true))) {
            writer.write(" ");
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write");
        }
    }
}
