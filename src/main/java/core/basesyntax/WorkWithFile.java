package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int amountOfSupply = 0;
    private int amountOfBuy = 0;
    private int resultValue;

    public void getStatistic(String fromFileName, String toFileName) {
        readFileWithData(fromFileName);
        getResultValue();
        writeProcessedData(toFileName);
    }

    private void readFileWithData(String fileWithData) {
        try (BufferedReader readerFromData = new BufferedReader(new FileReader(fileWithData))) {
            String lineWithOperation = readerFromData.readLine();
            while (lineWithOperation != null) {
                getAmount(lineWithOperation);
                lineWithOperation = readerFromData.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with data wasn't found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error of reading", e);
        }
    }

    private void getAmount(String operationLine) {
        int amountOnOperationLine = Integer.parseInt(operationLine
                .substring(operationLine.indexOf(",") + 1));
        switch (operationLine.substring(0, operationLine.indexOf(","))) {
            case "supply": {
                amountOfSupply += amountOnOperationLine;
                break;
            }
            case "buy": {
                amountOfBuy += amountOnOperationLine;
                break;
            }
            default: {

            }
        }
    }

    private void getResultValue() {
        resultValue = amountOfSupply - amountOfBuy;
    }

    private void writeProcessedData(String toFileName) {
        try (BufferedWriter writerProcessedData = new BufferedWriter(
                new FileWriter(new File(toFileName)))) {
            writerProcessedData.append("supply,").append(Integer.toString(amountOfSupply))
                    .append(System.lineSeparator())
                    .append("buy,").append(Integer.toString(amountOfBuy))
                    .append(System.lineSeparator())
                    .append("result,").append(Integer.toString(resultValue))
                    .append(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Writing error", e);
        }
    }
}
