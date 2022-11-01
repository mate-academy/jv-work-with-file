package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public static final String SUPPLY_IDENTIFIER = "supply";
    public static final String BUY_IDENTIFIER = "buy";
    public static final String RESULT_IDENTIFIER = "result";
    private final List<String> stringList = new ArrayList<>();
    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        calculateData();
        writeToFile(toFileName);
    }

    public void readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringList.add(value);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    public void calculateData() {
        String[] strArr;
        for (String value : stringList) {
            strArr = value.split("\\W+");
            for (String s : strArr) {
                if (s.equals(SUPPLY_IDENTIFIER)) {
                    supply += Integer.parseInt(strArr[strArr.length - 1]);
                    break;
                } else if (s.equals(BUY_IDENTIFIER)) {
                    buy += Integer.parseInt(strArr[strArr.length - 1]);
                    break;
                }
            }
        }
        result = supply - buy;
    }

    public void writeToFile(String s) {
        File file = new File(s);
        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("File was created.");
            }
        } catch (IOException e) {
            throw new RuntimeException("The file is already exist.", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(SUPPLY_IDENTIFIER + "," + supply + System.lineSeparator());
            bufferedWriter.write(BUY_IDENTIFIER + "," + buy + System.lineSeparator());
            bufferedWriter.write(RESULT_IDENTIFIER + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}
