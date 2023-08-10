package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        BufferedWriter bufferedWriter = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;
            int result = 0;
            while (value != null) {
                String[] strings = value.split(",");
                switch (strings[0]) {
                    case "supply":
                        supply = supply + Integer.parseInt(strings[1]);
                        break;
                    default :
                        buy = buy + Integer.parseInt(strings[1]);
                        break;
                }
                stringBuilder.append(value);
                value = bufferedReader.readLine();
            }
            result = supply - buy;
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
    }
}
