package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] splitBuilder = builder.toString().split(" ");
        for (int i = 0; i < splitBuilder.length; i++) {
            String[] splitBuilderArray = splitBuilder[i].split(",");
            if (splitBuilderArray[0].equals("supply")) {
                supply = supply + Integer.parseInt(splitBuilderArray[1]);
            } else {
                buy = buy + Integer.parseInt(splitBuilderArray[1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        result = supply - buy;
        stringBuilder.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy)
                .append(System.lineSeparator()).append("result,").append(result);
        System.out.println(stringBuilder.toString());
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
