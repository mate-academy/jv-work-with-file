package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private int supply;
    private int buy;
    private int result;
    private StringBuilder stringBuilder = new StringBuilder();
    private String fileLine = "";
    private String[] splitFileLine;

    public void getStatistic(String fromFileName, String toFileName) {
        supply = 0;
        buy = 0;
        stringBuilder.setLength(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            fileLine = reader.readLine();
            while (fileLine != null) {
                splitFileLine = fileLine.split(",");
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
        createReport();
        File createFile = createFile(toFileName);
        writeFile(createFile);
    }

    private void createReport() {
        result = supply - buy;
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

}
