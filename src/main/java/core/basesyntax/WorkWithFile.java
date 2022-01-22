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
        String[] boofData;
        boofData = getDataFromFile(fromFileName).split("\\W+");
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        int result;
        for (int i = 0; i < boofData.length - 1;i = i + 2) {
            supply += (boofData[i].equals("supply") ? Integer.parseInt(boofData[i + 1]) : 0);
            buy += (boofData[i].equals("buy") ? Integer.parseInt(boofData[i + 1]) : 0);
        }

        result = supply - buy;

        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).toString();
        writeDataToFile(builder.toString(),toFileName);
    }

    public void writeDataToFile(String dataForWrite,String toFileName) {
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(toFileName))) {
            dataWriter.write(dataForWrite);
        } catch (IOException e) {
            throw new WorkWithFileException("Can't write data to file");
        }
    }

    public String getDataFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder builder = new StringBuilder("");
        try (BufferedReader readerFrom = new BufferedReader(new FileReader(fileFrom))) {
            String fileString = readerFrom.readLine();
            while (fileString != null) {
                builder.append(fileString).append(System.lineSeparator());
                fileString = readerFrom.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new WorkWithFileException("File not found");
        } catch (IOException e) {
            throw new WorkWithFileException("Can't work with file");
        }
        return builder.toString();
    }
}
