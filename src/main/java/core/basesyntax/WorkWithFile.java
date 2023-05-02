package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String CHAR_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String mgsFromFile = readFromFile(fromFileName);
        int supplySum = 0;
        int buySum = 0;
        String[] lines = mgsFromFile.split(System.lineSeparator());
        String[] tempStr;
        for (int i = 0; i < lines.length; i++) {
            tempStr = lines[i].split(CHAR_SEPARATOR);
            if (tempStr[0].equals(SUPPLY)) {
                supplySum += Integer.parseInt(tempStr[1]);
            } else if (tempStr[0].equals(BUY)) {
                buySum += Integer.parseInt(tempStr[1]);
            } else {
                throw new RuntimeException("Error in csv file");
            }
        }
        String reportMassage = createReport(supplySum, buySum);
        writeStatistic(toFileName, reportMassage);
    }

    public String readFromFile(String fromFileName) {
        File myFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(myFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String value = bufferedReader.readLine();
            if (value == null) {
                throw new RuntimeException("File is empty");
            }
            stringBuilder.append(value).append(System.lineSeparator());
            while (value != null) {
                value = bufferedReader.readLine();
                if (value != null) {
                    stringBuilder.append(value).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File does not exist", e);
        }
        return stringBuilder.toString();
    }

    public String createReport(int supplySum, int buySum) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(CHAR_SEPARATOR)
                .append(supplySum).append(System.lineSeparator())
                .append(BUY).append(CHAR_SEPARATOR).append(buySum)
                .append(System.lineSeparator()).append(RESULT)
                .append(CHAR_SEPARATOR).append(supplySum - buySum);
        return report.toString();
    }

    public void writeStatistic(String toFileName, String massage) {
        File myFile = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(myFile);
                BufferedWriter bufferedReader = new BufferedWriter(fileWriter)) {
            bufferedReader.write(massage);
        } catch (IOException e) {
            throw new RuntimeException("Error with writing file",e);
        }
    }
}
