import java.util.List;

public class UserInterface {
    public static void main(String[] args) {
        final IOUtility ioUtility = new IOUtility("files");
        final List<String> strings = ioUtility.readDirectory();
        // System.out.println(strings);

        // parsowanie do wektorów
        // podanie danych do perceptronu
        // trenowanie
        // testowanie
    }
}