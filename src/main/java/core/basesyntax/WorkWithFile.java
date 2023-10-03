package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        stringBuilder.setLength(0);
        readCalculateFile(fromFileName);
        writeFile(createFile(toFileName));
    }

    private void createReport(int supply,int buy) {
        int result = supply - buy;
        stringBuilder.append("supply,").append(supply)
                .append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
    }

    private File createFile(String toFileName) {
        try {
            File createFile = new File(toFileName);
            createFile.createNewFile();
            return createFile;
        } catch (Exception e) {
            throw new RuntimeException("can't create file" + e);
        }
    }

    private void writeFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            throw new RuntimeException("Can't write file" + e);
        }
    }

    private void readCalculateFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String fileLine;
            while ((fileLine = reader.readLine()) != null) {
                String[] splitFileLine = fileLine.split(",");
                if (splitFileLine[0].equals("buy")) {
                    buy += Integer.parseInt(splitFileLine[1]);
                } else {
                    supply += Integer.parseInt(splitFileLine[1]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("can't read file" + e);
        }
        createReport(supply, buy);
    }
}
