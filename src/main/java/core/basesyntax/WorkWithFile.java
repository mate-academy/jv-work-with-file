package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] boofData;
        boofData = getDataFromFile(fromFileName).split("\\W+");
        int buy = 0;
        int supply = 0;
        int result;
        for (int i = 0; i < boofData.length - 1; i = i + 2) {
            supply += (boofData[i].equals(SUPPLY) ? Integer.parseInt(boofData[i + 1]) : 0);
            buy += (boofData[i].equals(BUY) ? Integer.parseInt(boofData[i + 1]) : 0);
        }
        result = supply - buy;
        writeDataToFile(getStringStatistic(supply,buy,result),toFileName);
    }

    private String getStringStatistic(int supply,int buy,int result) {
        return new StringBuilder().append(SUPPLY).append(COMMA)
                .append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeDataToFile(String dataForWrite,String toFileName) {
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(toFileName))) {
            dataWriter.write(dataForWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }

    private String getDataFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader readerFrom = new BufferedReader(new FileReader(fileFrom))) {
            String fileString;
            while ((fileString = readerFrom.readLine()) != null) {
                builder.append(fileString).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        } catch (IOException e) {
            throw new RuntimeException("Can't work with file");
        }
        return builder.toString().trim();
    }
}
