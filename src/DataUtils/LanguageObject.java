package DataUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageObject {
    private final List<String> stringsFromFiles;
    private List<double[]> doublesForFiles;
    private final String language;

    public LanguageObject(String language, List<String> stringsFromFiles) {
        this.language = language;
        this.stringsFromFiles = stringsFromFiles;
    }

    public static void assignDoubleRatiosForLanguages(List<LanguageObject> languageObjects) {
        for (LanguageObject languageObject : languageObjects) {
            final List<String> stringsFromFiles = languageObject.getStringsFromFiles();
            final List<double[]> doubles = DataParser.processDocs(stringsFromFiles);
            languageObject.setDoublesForFiles(doubles);
        }
    }

    public void setDoublesForFiles(List<double[]> doublesForFiles) {
        this.doublesForFiles = doublesForFiles;
    }

    public List<String> getStringsFromFiles() {
        return stringsFromFiles;
    }

    public String getLanguage() {
        return language;
    }

    public List<double[]> getDoublesForFiles() {
        return doublesForFiles;
    }

    public String doubleRatiosToString() {
        final String doublesStrings = doublesForFiles.stream().map(Arrays::toString).collect(Collectors.joining(", "));
        return STR."{langauge = \{language}, doubles = \{doublesStrings}\n";
    }

    @Override
    public String toString() {
        return STR."DataUtils.LanguageObject {stringsFromFiles = \{stringsFromFiles} }, language = \{language}\n";
    }
}
