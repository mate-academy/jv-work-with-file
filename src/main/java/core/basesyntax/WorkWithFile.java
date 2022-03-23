package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder lineRead = new StringBuilder();
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try {
            lineRead.append(Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read all line", e);
        }

        String[] arrayInput = lineRead.substring(1,lineRead.length() - 1).split(",");
        for (int i = 0, j = 1; i < arrayInput.length; i += 2, j += 2) {
            if (arrayInput[i].charAt(1) == 's' || arrayInput[i].equals("supply")) {
                supply += Integer.parseInt(arrayInput[j]);
            }
            if (arrayInput[i].charAt(1) == 'b' || arrayInput[i].equals("buy")) {
                buy += Integer.parseInt(arrayInput[j]);
            }
        }
        int result = supply - buy;
        String nextLine = System.lineSeparator();
        StringBuilder writeText = new StringBuilder();
        writeText.append("supply,")
                .append(supply)
                .append(nextLine)
                .append("buy,")
                .append(buy)
                .append(nextLine)
                .append("result,")
                .append(result);

        try {
            Files.write(Path.of(toFileName), Collections.singleton(writeText));
        } catch (IOException e) {
            throw new RuntimeException("Can't write text in file", e);
        }
    }
}
