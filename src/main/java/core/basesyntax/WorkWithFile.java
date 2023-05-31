package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SEPARATED_VALUES = ",";
    private static final String OPERATION_NAME = "supply";

    private int [] readFile(String fromFileName) {
        int [] variables = new int [2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String [] separatedStr = value.split(SEPARATED_VALUES);
                if (separatedStr[OPERATION_INDEX].equals(OPERATION_NAME)) {
                    variables[OPERATION_INDEX] += Integer.parseInt(separatedStr[AMOUNT_INDEX]);
                } else {
                    variables[AMOUNT_INDEX] += Integer.parseInt(separatedStr[AMOUNT_INDEX]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return variables;
    }

    private String getReport(int [] variables) {
        int result = variables[OPERATION_INDEX] - variables[AMOUNT_INDEX];
        System.out.println("supply," + variables[OPERATION_INDEX] + System.lineSeparator()
                + "buy," + variables[AMOUNT_INDEX] + System.lineSeparator()
                + "result," + result);
        return "supply," + variables[OPERATION_INDEX] + System.lineSeparator()
                + "buy," + variables[AMOUNT_INDEX] + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeFile(String toFileName, String report) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] variables = readFile(fromFileName);
        String report = getReport(variables);
        writeFile(toFileName, report);
    }
}
