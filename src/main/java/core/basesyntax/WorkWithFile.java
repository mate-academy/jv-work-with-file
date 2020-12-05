package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] informationFromFile = readFromFile(fromFileName).split(System.lineSeparator());
        String informationForWriteToFile =
                prepareInformationForWriteToFile(informationFromFile, fromFileName);
        writeToFile(informationForWriteToFile, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder information = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                information.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Data couldn't read from file " + fromFileName, e);
        }
        return information.toString();
    }

    private String prepareInformationForWriteToFile(String[] informationFromFile,
                                                    String fromFileName) {
        StringBuilder result = new StringBuilder();
        int sumSupply = 0;
        int sumBuy = 0;
        for (String type : informationFromFile) {
            String[] values = type.split(DELIMITER);
            if (values.length != 2) {
                throw new RuntimeException("We got not valid data from file " + fromFileName);
            }
            if (values[0].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(values[1]);
            } else if (values[0].equals(BUY)) {
                sumBuy += Integer.parseInt(values[1]);
            }
        }
        return String.valueOf(result.append(SUPPLY).append(DELIMITER).append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(sumSupply - sumBuy)
                .append(System.lineSeparator()));
    }

    private void writeToFile(String informationForWriteToFile, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(informationForWriteToFile);
        } catch (IOException e) {
            throw new RuntimeException("Data couldn't be write to File " + toFileName, e);
        }
    }
}
