package core.basesyntax;

// import java.io.;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte OPERATION_TYPE = 0, AMMOUNT = 1;
    private StringBuilder resultString = new StringBuilder();
    private int supply, buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
    }

    private void readFile(String fileName) {
        String readString;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            readString = bufferedReader.readLine();
            while (readString != null) {
                dataProcessing(readString);
                readString = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file" + fileName, e);
        }
    }

    private void writeFile(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(resultString.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + fileName + e);
        }
    }

    private void dataProcessing(String data) {
        String[] processingData = data.split(",");
        switch (processingData[OPERATION_TYPE]) {
            case ("supply"):
                supply = supply + Integer.parseInt(processingData[AMMOUNT]);
                break;
            case ("buy"):
            default:
                buy = buy + Integer.parseInt(processingData[AMMOUNT]);
                break;
        }
        resultString.setLength(0);
        resultString.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).
                append("result,").append(supply - buy).append(System.lineSeparator());
    }
}
