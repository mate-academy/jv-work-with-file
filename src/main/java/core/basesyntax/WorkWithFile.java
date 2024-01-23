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
    private static final char COMMA = ',';
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String formatReport = getFormatReport(data);
        writeToFile(toFileName, formatReport);
    }

    private String readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String field;
            while ((field = bufferedReader.readLine()) != null) {
                sb.append(field).append(" ");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t exist file", e);
        }
    }

    private String getFormatReport(String data) {
        int countSupply = 0;
        int countBuy = 0;

        String[] fields = data.split(" ");
        for (String field : fields) {
            String[] temporaryArray = field.split(",");
            if (temporaryArray[0].equals(SUPPLY)) {
                String number = temporaryArray[1];
                countSupply = countSupply + Integer.parseInt(number);
            } else if (temporaryArray[0].equals(BUY)) {
                String number = temporaryArray[1];
                countBuy = countBuy + Integer.parseInt(number);
            }
        }

        String supplyResult = SUPPLY + COMMA + countSupply;
        String buyResult = BUY + COMMA + countBuy;
        String lastResult = RESULT + COMMA + (countSupply - countBuy);
        return supplyResult + SEPARATOR
                + buyResult + SEPARATOR
                + lastResult + SEPARATOR;
    }

    private void writeToFile(String fileName, String result) {
        File fileTwo = new File(fileName);
        try {
            fileTwo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTwo, false))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Don`t write data to file", e);
        }
    }
}
