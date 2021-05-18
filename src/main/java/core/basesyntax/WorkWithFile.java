package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        final String supply = "supply";
        final String buy = "buy";
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put(supply, 0);
        resultMap.put(buy, 0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            while (reader.ready()) {
                String lineInFile = reader.readLine();
                String operation = lineInFile.substring(0, lineInFile.indexOf(','));
                String textValue = lineInFile.substring(lineInFile.indexOf(',') + 1);
                Integer valueOperation = Integer.parseInt(textValue);
                resultMap.put(operation, (resultMap.get(operation) + valueOperation));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error!!! Can not read file.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + resultMap.get(supply) + System.lineSeparator()
                    + "buy," + resultMap.get(buy) + System.lineSeparator()
                    + "result," + (resultMap.get(supply) - resultMap.get(buy)));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error!!! Can not write file.");
        }

    }
}
