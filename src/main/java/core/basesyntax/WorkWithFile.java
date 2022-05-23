package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        FileReader reader = new FileReader();
        ContentConverter converter = new ContentConverter();
        String content = reader.getFileContent(fromFileName);
        String convertedContent = converter.getFileContent(content);
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(convertedContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
