package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetInformationFromFile {
    public String getInformation(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String index = bufferedReader.readLine();
            while (index != null) {
                builder.append(index).append(" ");
                index = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
