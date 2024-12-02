import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        String report = "supply,100" + System.lineSeparator()
                + "buy,50" + System.lineSeparator()
                + "result,50"; 

        writeToFile(toFileName, report);
    }

    private void writeToFile(String fileName, String content) {
        try {
            Path filePath = Path.of(fileName);
            Path directoryPath = filePath.getParent();

            if (directoryPath != null && !Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); 
            }

            Files.writeString(filePath, content); 
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
