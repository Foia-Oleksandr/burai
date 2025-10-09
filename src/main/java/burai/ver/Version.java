/*
 * Copyright (C) 2018 Satomichi Nishihara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package burai.ver;

public interface Version {

    int MAJOR = 1;
    int MINOR = 3;
    int PATCH = 3;
    String VERSION = MAJOR + "." + MINOR + "." + PATCH;

    /**
     * Compares this version with another version considering only major and minor parts.
     * @param otherMajor the major version to compare with
     * @param otherMinor the minor version to compare with
     * @return negative if this version is lower, <code>0</code> if equal (ignoring a patch), positive if higher
     */
    static int compareIgnoringPatch(int otherMajor, int otherMinor) {
        if (MAJOR != otherMajor) {
            return MAJOR - otherMajor;
        }
        return MINOR - otherMinor;
    }

    /**
     * Checks if this version differs from another version in major or minor part.
     * @param otherMajor the major version to compare with
     * @param otherMinor the minor version to compare with
     * @return true if major or minor versions differ
     */
    static boolean hasMajorMinorDifference(int otherMajor, int otherMinor) {
        return compareIgnoringPatch(otherMajor, otherMinor) != 0;
    }

    /**
     * Parses a version string and checks if it differs in major or minor part.
     * @param versionString the version string to compare (e.g., "1.3.3")
     * @return true if major or minor versions differ
     */
    static boolean hasMajorMinorDifference(String versionString) {
        if (versionString == null || versionString.isEmpty()) {
            return true;
        }
        String[] parts = versionString.split("\\.");
        if (parts.length < 2) {
            return true;
        }
        try {
            int otherMajor = Integer.parseInt(parts[0]);
            int otherMinor = Integer.parseInt(parts[1]);
            return hasMajorMinorDifference(otherMajor, otherMinor);
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
