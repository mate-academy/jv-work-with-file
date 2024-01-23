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
    private static final String COMMA = ",";
    private static final String SEPARATOR = System.lineSeparator();
    private static final int FIRST_MEMBER = 0;
    private static final int SECOND_MEMBER = 1;

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
            throw new RuntimeException("Can`t read file: " + fileName, e);
        }
    }

    private String getFormatReport(String data) {
        int countSupply = 0;
        int countBuy = 0;

        String[] fields = data.split(" ");
        for (String field : fields) {
            String[] temporaryArray = field.split(COMMA);
            if (temporaryArray[FIRST_MEMBER].equals(SUPPLY)) {
                String number = temporaryArray[SECOND_MEMBER];
                countSupply = countSupply + Integer.parseInt(number);
            } else if (temporaryArray[FIRST_MEMBER].equals(BUY)) {
                String number = temporaryArray[SECOND_MEMBER];
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
        File fileTo = new File(fileName);
        try {
            fileTo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file: " + fileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, false))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Don`t write data to file: " + fileTo, e);
        }
    }
}
