package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
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
        final int operationTypeIndex = 0;
        final int amountIndex = 1;
        final String supplyString = "supply,";
        final String buyString = "buy,";
        final String resultString = "result,";
        int buyCounter = 0;
        int supplyCounter = 0;
        String[] arrayOfLines = fileInString.split(System.lineSeparator());
        for (String currentElement : arrayOfLines) {
            String[] buyAndSupplyArray = currentElement.split("\\W+");
            switch (buyAndSupplyArray[operationTypeIndex]) {
                case "supply":
                    supplyCounter += Integer.parseInt(buyAndSupplyArray[amountIndex]);
                    break;
                case "buy":
                default:
                    buyCounter += Integer.parseInt(buyAndSupplyArray[amountIndex]);
                    break;
            }
        }
        int result = supplyCounter - buyCounter;
        StringBuilder report = new StringBuilder().append(supplyString)
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append(buyString).append(buyCounter)
                .append(System.lineSeparator())
                .append(resultString).append(result);
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
