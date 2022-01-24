package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String supply = "supply";
    private static final String buy = "buy";
    private static final String separator = ",";
    private int supplySum = 0;
    private int buySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report;
        for (String datum : data) {
            String[] note = datum.split(separator);
            supplySum += note[0].equals(supply) ? Integer.parseInt(note[1]) : 0;
            buySum += note[0].equals(buy) ? Integer.parseInt(note[1]) : 0;
        }
        report = (new StringBuilder(supply).append(separator).append(supplySum)
                .append(System.lineSeparator()).append(buy).append(separator)
                .append(buySum).append(System.lineSeparator()).append("result,")
                .append(supplySum - buySum)).toString();
        writeToFile(report, toFileName);
    }

    public String[] readFromFile(String fromFileName) {
        File fileOut = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileOut))) {
            String textLine;
            while ((textLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(textLine).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    public void writeToFile(String data, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
