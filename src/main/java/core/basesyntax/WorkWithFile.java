package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String INCOME = "supply";
    private static final String OUTCOME = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeResultTable(getResultValues(new File(fromFileName)), new File(toFileName));
    }

    private int[] getResultValues(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int income = 0;
            int outcome = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                if (splitLine[0].equals(INCOME)) {
                    income += Integer.parseInt(splitLine[1]);
                } else {
                    outcome += Integer.parseInt(splitLine[1]);
                }
                line = reader.readLine();
            }
            return new int[]{income, outcome};
        } catch (IOException e) {
            throw new RuntimeException("Input file cannot be read ", e);
        }
    }

    private void writeResultTable(int[] inOutValues, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(INCOME).append(",").append(String.valueOf(inOutValues[0]));
            writer.newLine();
            writer.append(OUTCOME).append(",").append(String.valueOf(inOutValues[1]));
            writer.newLine();
            writer.append(RESULT).append(",")
                    .append(String.valueOf(inOutValues[0] - inOutValues[1]));
        } catch (IOException e) {
            throw new RuntimeException("Output file cannot be written ", e);
        }
    }
}
