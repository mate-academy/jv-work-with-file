package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileData = readFile(fromFileName).split(NEW_LINE);
        String[] resultData = prepareStringToWrite(fileData);
        clearFile(toFileName);
        writeData(resultData, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
        return content.toString();
    }

    private String[] prepareStringToWrite(String[] notGroupedData) {
        int supplySum = 0;
        int buySum = 0;
        int addition = 0;
        for (int i = 0; i < notGroupedData.length; i++) {
            String[] splitArray = notGroupedData[i].split(COMMA);
            addition = Integer.parseInt(splitArray[1]);
            if (splitArray[0].equals(SUPPLY)) {
                supplySum += addition;
            }
            if (splitArray[0].equals(BUY)) {
                buySum += addition;
            }
        }
        return new String[]{SUPPLY + COMMA + supplySum,
                BUY + COMMA + buySum,
                RESULT + COMMA + (supplySum - buySum)};
    }

    private void clearFile(String fileName) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException("Cannot clear the file: " + fileName, e);
        }
    }

    private void writeData(String[] report, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String data : report) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("The data hasn't been  written to file: "
                    + fileName, e);
        }
    }
}
