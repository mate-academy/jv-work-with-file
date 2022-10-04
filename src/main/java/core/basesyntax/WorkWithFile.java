package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File inputData = new File(fromFileName);
        File writeData = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeData, true))) {
            BufferedReader reader = new BufferedReader(new FileReader(inputData));
            StringBuilder stringBuilderInput = new StringBuilder();
            int value = reader.read();
            while (value != -1) {
                stringBuilderInput.append((char) value);
                value = reader.read();
            }
            String[] separatedInput = stringBuilderInput.toString().split(System.lineSeparator());
            int supply = 0;
            int buy = 0;
            int result = 0;
            for (String input : separatedInput) {
                String[] separateValue = input.split(",");
                if (separateValue[0].equals("supply")) {
                    supply += Integer.parseInt(separateValue[1]);
                }
                if (separateValue[0].equals("buy")) {
                    buy += Integer.parseInt(separateValue[1]);
                }
                result = supply - buy;
            }
            String[] dataToWrite = {"supply," + supply + System.lineSeparator(),
                    "buy," + buy + System.lineSeparator(),
                    "result," + result + System.lineSeparator()};
            for (String data : dataToWrite) {
                bufferedWriter.write(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't get data", e);
        }
    }
}
