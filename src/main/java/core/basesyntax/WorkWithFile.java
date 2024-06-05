package core.basesyntax;

import java.util.Map;

public class WorkWithFile {
    private final FileParser fileParser = new FileParser();
    private final WriterToFile writerToFile = new WriterToFile();

    public void getStatistic(String fromFileName, String toFileName) {

        Map<String, Integer> contentMap = fileParser.parseFileContent(fromFileName);
        writerToFile.writeToFile(contentMap, toFileName);
    }

}
