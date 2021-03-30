package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileTo = new File(fromFileName.substring(0, fromFileName.indexOf(".")) + "Result.csv");
        try {
            fileTo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("can't create new file.");
        }

        Path go = Paths.get(fromFileName);
        File to = go.toFile();
        int supply = 0;
        int buy = 0;
        int result = 0;

        try {

            List<String> list = Files.readAllLines(to.toPath());
            for (String str : list) {
                String[] args = str.split(",");
                if (args[0].equals("supply")) {
                    supply += Integer.parseInt(args[1]);
                } else {
                    buy += Integer.parseInt(args[1]);
                }
            }
            result = supply - buy;

        } catch (IOException e) {
            throw new RuntimeException("file not found.", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo, true))) {
            writer.write("supply" + "," + supply + System.lineSeparator());
            writer.write("buy" + "," + buy + System.lineSeparator());
            writer.write("result" + "," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("file not found.", e);
        }
    }
}
