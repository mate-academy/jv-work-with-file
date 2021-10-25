package core.basesyntax;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        int resultValue;
        try (BufferedReader readerFromFileWithData = new BufferedReader(new FileReader(fromFileName))) {
            String lineWithOperation = readerFromFileWithData.readLine();
            int amountOnOperationLine;
            while (lineWithOperation != null){
              amountOnOperationLine = Integer.parseInt(lineWithOperation.substring(lineWithOperation.indexOf(",")+1));
              switch (lineWithOperation.substring(0, lineWithOperation.indexOf(","))){
                case "supply":{
                    amountOfSupply += amountOnOperationLine;
                    break;
                }
                case "buy":{
                    amountOfBuy += amountOnOperationLine;
                    break;
                }
                default:{
                }
              }
              lineWithOperation = readerFromFileWithData.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with data wasn't found", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading error", e);
        }
        resultValue = amountOfSupply - amountOfBuy;
        writeResultToFile(toFileName, amountOfSupply, amountOfBuy, resultValue);

    }
    private void writeResultToFile(String toFileName, int amountOfSupply, int amountOfBuy, int resultValue) {
        File fileWithResult = new File(toFileName);
        try(BufferedWriter writerWithResult = new BufferedWriter(new FileWriter(fileWithResult))) {
            writerWithResult.append("supply,").append(Integer.toString(amountOfSupply))
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
