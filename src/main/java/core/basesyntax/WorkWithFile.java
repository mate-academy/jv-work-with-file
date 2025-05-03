package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName).split(" ");
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public String createReport(String[] dataFromFile) {
        int buy = 0;
        int supply = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            String[] value = dataFromFile[i].split(",");
            if ("supply".equals(value[OPERATION_NAME_INDEX])) {
                supply += Integer.parseInt(value[VALUE_INDEX]);
            } else {
                buy += Integer.parseInt(value[VALUE_INDEX]);
            }
        }

        return calculateResult(supply, buy);
    }

    public String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
        return stringBuilder.toString();
    }

    public void writeToFile(String fileName, String data) {
        File file = new File(fileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }

    public String calculateResult(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        int result = supply >= buy ? supply - buy : buy - supply;
        return stringBuilder.append("supply").append(",").append(supply)
                .append(System.lineSeparator()).append("buy").append(",")
                .append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }
}
