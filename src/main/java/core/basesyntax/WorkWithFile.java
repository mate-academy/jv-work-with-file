package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_STRING = "supply,";
    private static final String BUY_STRING = "buy,";
    private static final String RESULT_STRING = "result,";
    private static final String SPLIT_BY_THIS_STRING = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileInfoInString = readInformationFromFile(fromFileName);
        String report = createReport(fileInfoInString);
        writeInformationToFile(toFileName, report);
    }

    private String readInformationFromFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentStringInFile = bufferedReader.readLine();
            while (currentStringInFile != null) {
                fileContent.append(currentStringInFile).append(System.lineSeparator());
                currentStringInFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
        return fileContent.toString();
    }

    private String createReport(String fileInString) {
        int buyCounter = 0;
        int supplyCounter = 0;
        String[] arrayOfLines = fileInString.split(System.lineSeparator());
        for (String line : arrayOfLines) {
            String[] buyAndSupplyArray = line.split(SPLIT_BY_THIS_STRING);
            switch (buyAndSupplyArray[OPERATION_TYPE_INDEX]) {
                case "supply":
                    supplyCounter += Integer.parseInt(buyAndSupplyArray[AMOUNT_INDEX]);
                    break;
                case "buy":
                default:
                    buyCounter += Integer.parseInt(buyAndSupplyArray[AMOUNT_INDEX]);
                    break;
            }
        }
        int result = supplyCounter - buyCounter;
        StringBuilder report = new StringBuilder().append(SUPPLY_STRING)
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append(BUY_STRING).append(buyCounter)
                .append(System.lineSeparator())
                .append(RESULT_STRING).append(result);
        return report.toString();
    }

    private void writeInformationToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}
