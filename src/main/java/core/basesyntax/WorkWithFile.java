package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        int result = 0;
        StringBuilder stringBuilder = new StringBuilder();
        readToFile(fromFileName,stringBuilder);
        String[] strings = stringBuilder.toString().split(COMA);
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(SUPPLY)) {
                supplySum += Integer.parseInt(strings[i + 1]);
            }
            if (strings[i].equals(BUY)) {
                buySum += Integer.parseInt(strings[i + 1]);
            }
            result = supplySum - buySum;
        }
        write(supplySum,buySum,result,toFileName);
    }

    private String[] getReport(int supplySum, int buySum, int result) {
        String[] array = new String[3];
        array[0] = SUPPLY + COMA + supplySum;
        array[1] = BUY + COMA + buySum;
        array[2] = RESULT + COMA + result;
        return array;
    }

    private void write(int supplySum, int buySum, int result, String toFileName) {
        File fileTo = new File(toFileName);
        String[] array = getReport(supplySum, buySum, result);
        for (String text : array) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
                bufferedWriter.write(text + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write file", e);
            }
        }
    }

    private String readToFile(String fromFileName, StringBuilder builder) {
        File fileFrom = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            String string = reader.readLine();
            while (string != null) {
                builder.append(string).append(",");
                string = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return fromFileName;
    }
}
