package pl.sikora.pretiusApp;

import java.util.Optional;

public class ExtensionGetter {
    public static Optional<String> getExtensionByString(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
