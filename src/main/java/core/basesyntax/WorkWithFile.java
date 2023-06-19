package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readCsvFile(fromFileName)));
    }

    public void writeToFile(String file, String data) {
        File uptputFile = new File(file);
        FileWriter fileWriter;
        try {
            Files.write(uptputFile.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String createReport(String data) {
        StringBuilder report = new StringBuilder();
        int supply = 0;
        int buy = 0;
        String[] record = data.split(System.lineSeparator());
        for (int i = 0; i < record.length; i++) {
            if (record[i].split(",")[0].equalsIgnoreCase("buy")) {
                buy = buy + Integer.parseInt(record[i].split(",")[1]);
            } else {
                supply = supply + Integer.parseInt(record[i].split(",")[1]);
            }
        }
        int result = supply - buy;
        report.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).append(System.lineSeparator());
        return report.toString();
    }

    public String readCsvFile(String file) {
        File inputFile = new File(file);
        StringBuilder csvFileContent = new StringBuilder();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String lineOfFile;
        try {
            fileReader = new FileReader(inputFile);
            bufferedReader = new BufferedReader(fileReader);
            lineOfFile = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Cant read file.", e);
        }
        while (lineOfFile != null) {
            csvFileContent.append(lineOfFile).append(System.lineSeparator());
            try {
                lineOfFile = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return csvFileContent.toString();
    }

}



