package core.basesyntax;

import java.io.File;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        FileReader reader = new FileReader();
        ContentConverter converter = new ContentConverter();
        String content = reader.getRawContent(fromFileName);
        String convertedContent = converter.getHandledFileContent(content);
        ContentWriter writer = new ContentWriter();
        writer.getNewStatisticsFile(convertedContent, new File(toFileName));
    }
}
