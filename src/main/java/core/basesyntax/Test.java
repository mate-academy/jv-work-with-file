package core.basesyntax;

public class Test {
    public static void main(String[] args) {
        System.out.println(extractFileName("0_file.txt.zip"));
    }

    public static String extractFileName(String dirtyFileName) {
        String[] str = dirtyFileName.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("string " + str[0] + " " + str[1]);
        if (str.length > 2) {
            System.out.println("mskmi  " + str.length);
            for (int a = 1; a < str.length; a++) {
                stringBuilder.append(str[a]).append("_");
            }
        } else {
            stringBuilder.append(str[1]);
        }

        String[] strWithDots = stringBuilder.toString().split("[.]");
        System.out.println("strWdots " + strWithDots[0]);
        for (String test : strWithDots) {
            System.out.println("test " + test);
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int k = 0; k < strWithDots.length - 1; k++) {
            stringBuilder1.append(strWithDots[k]).append(".");
        }
        System.out.println("String " + stringBuilder1);
        stringBuilder1.deleteCharAt(stringBuilder1.length() - 1);
        return stringBuilder1.toString();
    }
}
