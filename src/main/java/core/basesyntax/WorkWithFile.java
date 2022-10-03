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
        int firstResult = 0;
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                        true));) {
            while (bufferedReader.ready()) {
                // Файл формата CSV - это файл с разделителями-запятыми, здесь он разделен запятой
                String[] rows = bufferedReader.readLine().split(",");
                list.add(rows);
            }
            for (String[] str : list) {
                if (!listOfPosition.contains(str[0])) {
                    listOfPosition.add(str[0]);
                }
                System.out.println("SET ADD " + str[0]);
                for (String sk : str) {
                }
            }
            Collections.sort(listOfPosition, Collections.reverseOrder());
            for (String lst : listOfPosition) {
                int num = 0;
                for (String[] str : list) {
                    if (str[0].equals(lst)) {
                        num += Integer.parseInt(str[1]);
                    }
                    System.out.println("set " + lst + " numn  " + num);
                }
                bufferedWriter.write(lst + "," + num + "\r\n");
                if (firstResult == 0) {
                    firstResult = num;
                } else {
                    int difference = firstResult - num;
                    bufferedWriter.write("result," + difference);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
