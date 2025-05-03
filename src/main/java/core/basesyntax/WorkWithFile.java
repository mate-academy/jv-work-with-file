package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String TO_SUPPLY = "supply";
    private static final String TO_BUY = "buy";
    private static final String TO_RESULT = "result";
    private static final String TO_CONST = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] array = readFile(fromFileName).split(TO_CONST);
        writeFile(toFileName, array);
    }

    public String readFile(String fromFileName) {
        File firstFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(firstFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value == null) {
                    break;
                }
                stringBuilder.append(value).append(TO_CONST);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data in file " + e);
        }
        return stringBuilder.toString();
    }

    public void writeFile(String toFileName, String[] array) {
        int countSupply = 0;
        int countBuy = 0;
        File secondFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(secondFile))) {
            for (int i = 0; i < array.length; i++) {
                switch (array[i]) {
                    case TO_SUPPLY:
                        countSupply += Integer.parseInt(array[i + 1]);
                        break;
                    case TO_BUY:
                        countBuy += Integer.parseInt(array[i + 1]);
                        break;
                    default:
                        break;
                }
            }
            bufferedWriter.write(TO_SUPPLY + TO_CONST + countSupply);
            bufferedWriter.newLine();
            bufferedWriter.write(TO_BUY + TO_CONST + countBuy);
            bufferedWriter.newLine();
            bufferedWriter.write(TO_RESULT + TO_CONST + (countSupply - countBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data in file" + e);
        }
    }
}
