package burai.atoms.reader;

import burai.atoms.model.Atom;
import burai.atoms.model.Cell;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class CIFReaderTest {

    @ParameterizedTest
    @CsvSource({
            "burai/atoms/reader/C_with_oxidation_level.cif",
            "burai/atoms/reader/C_without_oxidation_level.cif"
    })
    void cifFileWithOxidationLevel__readCell__correctlyFetchAtomName(String resourcePath) throws IOException {
        Path path = resourcePath(resourcePath);
        String filePath = path.toString();

        try (AtomsReader reader = AtomsReader.getInstance(filePath)) {
            Cell cell = reader.readCell();

            Atom[] atoms = cell.listAtoms();
            assertThat(atoms)
                    .extracting(Atom::getName)
                    .allMatch("C"::equals);
        }
    }

    public static Path resourcePath(String relativePath) {
        try {
            URL resource = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(relativePath);
            if (resource == null)
                throw new IllegalArgumentException("Resource not found: " + relativePath);
            return Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}