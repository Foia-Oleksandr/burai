package burai.ver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

import static burai.ver.Version.MAJOR;
import static burai.ver.Version.MINOR;
import static burai.ver.Version.PATCH;
import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    @Test
    @DisplayName("Should have correct version constants")
    void appVersion_shouldHaveWithPatchNumber() {
        var versionParts = Version.VERSION.split("\\.");
        if (versionParts.length < 3) {
            fail("version without patch number");
        }
    }

    @Test
    void lowerMajor_hasDifference_shouldReturnTrue() {
        var userVersion = String.join(".", Integer.toString(MAJOR - 1), Integer.toString(MINOR));
        assertTrue(Version.hasMajorMinorDifference(userVersion));
    }

    @Test
    void higherMajor_hasDifference_shouldReturnTrue() {
        var userVersion = String.join(".", Integer.toString(MAJOR + 1), Integer.toString(MINOR));
        assertTrue(Version.hasMajorMinorDifference(userVersion));
    }

    @Test
    @EnabledIf("minorAppVersionMoreThanZero")
    void lowerMinor_hasDifference_shouldReturnTrue() {
        var userVersion = String.join(".", Integer.toString(MAJOR), Integer.toString(MINOR - 1));
        assertTrue(Version.hasMajorMinorDifference(userVersion));
    }

    @Test
    void higherMinor_hasDifference_shouldReturnTrue() {
        var userVersion = String.join(".", Integer.toString(MAJOR), Integer.toString(MINOR + 1));
        assertTrue(Version.hasMajorMinorDifference(userVersion));
    }

    @Test
    void equalMajorAndMinor_hasDifference_shouldReturnFalse() {
        var userVersion = String.join(".", Integer.toString(MAJOR), Integer.toString(MINOR));
        assertFalse(Version.hasMajorMinorDifference(userVersion));
    }

    @Test
    void hasDifference_shouldIgnorePatch() {
        var userVersion = String.join(".", Integer.toString(MAJOR), Integer.toString(MINOR), Integer.toString(PATCH + 1));
        assertFalse(Version.hasMajorMinorDifference(userVersion));
    }

    private boolean minorAppVersionMoreThanZero() {
        return MINOR > 0;
    }

}