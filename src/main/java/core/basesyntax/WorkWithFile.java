package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String STRING_BUY = "buy";
    public static final String STRING_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String allText = readFromFile(fromFileName);
        String resultData = parseAndCalculateData(allText);
        writeToFile(toFileName, resultData);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder allText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allText.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file with name " + fromFile, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read information!", e);
        }
        return allText.toString();
    }

    private String parseAndCalculateData(String allText) {
        int buy = 0;
        int supply = 0;
        for (String line : allText.split(System.lineSeparator())) {
            String[] nameAndValue = line.split(",");
            if (nameAndValue[0].equals(STRING_BUY)) {
                buy += Integer.parseInt(nameAndValue[1]);
            }
            if (nameAndValue[0].equals(STRING_SUPPLY)) {
                supply += Integer.parseInt(nameAndValue[1]);
            }
        }
        return new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(supply - buy).toString();
    }

    private void writeToFile(String toFileName, String resultData) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write information to file " + toFileName, e);
        }
    }
}
