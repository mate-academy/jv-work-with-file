package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);

        String tempString;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));

            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            tempString = builder.toString();
            String[] tempStringArray;
            int supply = 0;
            int buy = 0;
            int result;

            tempStringArray = tempString.split("[^A-Za-z0-9 ]+");
            for (int i = 0; i < tempStringArray.length; i = i + 2) {
                tempString = tempStringArray[i];
                if (tempString.startsWith("s")) {
                    supply = supply + Integer.parseInt(String.valueOf(tempStringArray[i + 1]));
                } else {
                    buy = buy + Integer.parseInt(String.valueOf(tempStringArray[i + 1]));
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            result = supply - buy;
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result," + result);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
