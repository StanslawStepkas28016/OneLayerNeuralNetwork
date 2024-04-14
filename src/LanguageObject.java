import java.util.Arrays;
import java.util.Collections;
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

    public void setDoublesForFiles(List<double[]> doublesForFiles) {
        this.doublesForFiles = doublesForFiles;
    }

    public List<String> getStringsFromFiles() {
        return stringsFromFiles;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return STR."LanguageObject {stringsFromFiles = \{stringsFromFiles} }, language = \{language}\n";
    }

    public String toStringDoubles() {
        final String doublesStrings = doublesForFiles.stream().map(Arrays::toString).collect(Collectors.joining(", "));
        return STR."{langauge = \{language}, doubles = \{doublesStrings}\n";
    }
}
