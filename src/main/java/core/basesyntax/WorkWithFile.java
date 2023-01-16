package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String inputFile = fileReader(fromFileName);
        int[] resultString = productCount(inputFile);
        fileWriter(toFileName, resultString);
    }

    private String fileReader(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int[] productCount(String file) {
        int supply = 0;
        int buy = 0;
        for (String item : file.split(" ")) {
            String[] itemArray = item.split(",");
            if (itemArray[0].equals("supply")) {
                supply += Integer.parseInt(itemArray[1]);
            } else {
                buy += Integer.parseInt(itemArray[1]);
            }
        }
        return new int[] {supply, buy};
    }

    private void fileWriter(String fileName, int[] resultString) {
        File outputFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write("supply," + resultString[0]);
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("buy," + resultString[1]);
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("result," + (resultString[0] - resultString[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
