package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String fileLine = "";
        StringBuilder stringBuilder = new StringBuilder();
        File createFile = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            fileLine = reader.readLine();

            while (fileLine != null) {
                String[] splitFileLine = fileLine.split(",");

                if (splitFileLine[0].equals("buy")) {
                    buy += Integer.parseInt(splitFileLine[1]);
                } else {
                    supply += Integer.parseInt(splitFileLine[1]);
                }

                fileLine = reader.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException("can't read file" + e);
        }

        result = supply - buy;
        stringBuilder.append("supply,").append(supply)
                .append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try {
            createFile = new File(toFileName);
            createFile.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException("can't create file" + e);
        }

        try (FileWriter fileWriter = new FileWriter(createFile)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            throw new RuntimeException("Сan't write file" + e);
        }
    }
}
