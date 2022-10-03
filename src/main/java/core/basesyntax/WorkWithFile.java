package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        List<String[]> list = new ArrayList<>();
        List<String> listOfPosition = new ArrayList<>();
        int supplyResult = 0;
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                        true));) {
            while (bufferedReader.ready()) {
                String[] rows = bufferedReader.readLine().split(",");
                list.add(rows);
            }
            for (String[] str : list) {
                if (!listOfPosition.contains(str[0])) {
                    listOfPosition.add(str[0]);
                }
            }
            Collections.sort(listOfPosition, Collections.reverseOrder());
            for (String lst : listOfPosition) {
                int result = 0;
                for (String[] str : list) {
                    if (str[0].equals(lst)) {
                        result += Integer.parseInt(str[1]);
                    }
                }
                bufferedWriter.write(lst + "," + result + "\r\n");
                if (supplyResult == 0) {
                    supplyResult = result;
                } else {
                    int difference = supplyResult - result;
                    bufferedWriter.write("result," + difference);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
