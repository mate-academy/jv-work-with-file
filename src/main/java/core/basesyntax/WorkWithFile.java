package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        File myFile = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
        return parseFileData(builder.toString());
    }

    private String[] parseFileData(String FileData) {
        if (FileData.length() == 0) {
            return new String[0];
        }
        return FileData.split(" ");
    }

    private String createReport(String[] data) {
        StringBuilder builder = new StringBuilder();
        int supplyNumber = 0;
        int buyNumber = 0;

        for (String line : data) {
            String[] splitLine = line.split(",");
            if (splitLine[0].equals("supply")) {
                supplyNumber += Integer.parseInt(splitLine[1]);
            } else {
                buyNumber += Integer.parseInt(splitLine[1]);
            }
        }

        builder.append("supply,").append(supplyNumber).append(System.lineSeparator())
                .append("buy,").append(buyNumber).append(System.lineSeparator())
                .append("result,").append(supplyNumber - buyNumber);

        return builder.toString();
    }

    private void writeToFile(String fileName, String data) {
        File myFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(myFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file.", e);
        }
    }
}
