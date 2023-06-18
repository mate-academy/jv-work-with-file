package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File uptputFile = new File(toFileName);
        writeToFile(uptputFile, createReport(readCsvFile(inputFile)));
    }

    public void writeToFile(File file, String data) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Buffered writer cant access Writer", e);
        }

    }

    public String createReport(String[][] data) {
        StringBuilder report = new StringBuilder();
        int i = 0;
        int supply = 0;
        int buy = 0;
        int result = 0;
        while (data[i][0] != null) {
            if (data[i][0].equalsIgnoreCase("buy")) {
                buy = buy + Integer.parseInt(data[i][1]);
            } else {
                supply = supply + Integer.parseInt(data[i][1]);
            }
            result = supply - buy;
            i++;
        }
        report.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).append(System.lineSeparator());
        return report.toString();
    }

    public String[][] readCsvFile(File file) {
        String[][] csvFileContent = new String[20][2];
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
        String lineOfFile;
        try {
            bufferedReader = new BufferedReader(fileReader);
            lineOfFile = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Cant read file.", e);
        }
        int counter = 0;
        while (lineOfFile != null) {
            csvFileContent[counter] = lineOfFile.split(",");
            counter++;
            try {
                lineOfFile = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return csvFileContent;
    }

}



