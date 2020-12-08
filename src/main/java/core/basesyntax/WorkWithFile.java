package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, readFileAndCalculate(fromFileName));
    }

    private String readFileAndCalculate(String fileName) {
        StringBuilder returnValue = new StringBuilder();
        String[] workList;
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            workList = stringBuilder.toString().split(",");
            for (int i = 0; i < workList.length; i++) {
                if (workList[i].equals(SUPPLY)) {
                    supply += Integer.parseInt(workList[i + 1]);
                } else if (workList[i].equals(BUY)) {
                    buy += Integer.parseInt(workList[i + 1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file.", e);
        }
        return returnValue.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY).append(",").append(buy)
                .append(System.lineSeparator()).append(RESULT).append(",")
                .append(supply - buy).toString();
    }

    private void writeFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data file", e);
        }
    }
}
