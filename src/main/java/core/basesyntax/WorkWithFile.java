package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String SUPPLY = "supply";
    private static String BUY = "buy";
    private static int OPERATION_INDEX = 0;
    private static int AMOUNT_INDEX = 1;
    private static String CSV_SEPARATOR = ",";
    private static String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        recordDateInFile(toFileName, readDataFromFile(fromFileName));
    }

    public String readDataFromFile(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            int totalSupply = 0;
            int buyTotal = 0;
            while (value != null) {
                String[] temporary = value.split(CSV_SEPARATOR);
                if (temporary[OPERATION_INDEX].equals(SUPPLY)) {
                    totalSupply += Integer.parseInt(temporary[AMOUNT_INDEX]);
                } else if (temporary[OPERATION_INDEX].equals(BUY)) {
                    buyTotal += Integer.parseInt(temporary[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
            stringBuilder.append(SUPPLY).append(CSV_SEPARATOR).append(totalSupply)
                    .append(System.lineSeparator())
                    .append(BUY).append(CSV_SEPARATOR).append(buyTotal)
                    .append(System.lineSeparator())
                    .append(RESULT).append(CSV_SEPARATOR).append(totalSupply - buyTotal);
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
    }

    public void recordDateInFile(String toFileName, String data) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            System.out.println("Can`t record data in file ");
        }
    }
}
