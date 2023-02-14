package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        String data = read(fromFileName);
        writeData(toFile, data);
    }
    
     private String read(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file", e);
        }
        return stringBuilder.toString();
    }

    private int getSum(String value, String data) {
        String[] array = data.split(System.lineSeparator());
        int amount = 0;
        for (String item : array) {
            String[] itemInfo = item.split(SEPARATOR);
            if (itemInfo[0].equals(value)) {
                amount += Integer.parseInt(itemInfo[1]);
            }
        }
        return amount;
    }

  private void writeData(File file, String data) {
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(SUPPLY + SEPARATOR + getSum(SUPPLY, data)
                    + System.lineSeparator());
            bufferedWriter.write(BUY + SEPARATOR + getSum(BUY, data)
                    + System.lineSeparator());
            bufferedWriter.write(RESULT + SEPARATOR + (getSum(SUPPLY, data) - getSum(BUY, data)));
        } catch (IOException e) {
            throw new RuntimeException("Cant write data", e);
        }
    }
}
