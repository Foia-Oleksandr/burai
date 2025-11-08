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

package burai.atoms.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import burai.atoms.model.Cell;

public abstract class AtomsReader implements AutoCloseable {

    private static final int FILE_TYPE_NULL = 0;
    private static final int FILE_TYPE_QE = 1;
    private static final int FILE_TYPE_XYZ = 2;
    private static final int FILE_TYPE_CIF = 3;
    private static final int FILE_TYPE_CUBE = 4;
    private static final int FILE_TYPE_XSF = 5;
    private static final int FILE_TYPE_AXSF = 6;
    private static final int FILE_TYPE_VASP = 7;

    private static final String VASP_NAME_POSCAR = File.separator + "POSCAR";
    private static final String VASP_NAME_CONTCAR = File.separator + "CONTCAR";

    private static int getFileType(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            return FILE_TYPE_NULL;
        }

        String trimmed = filePath.trim();

        if (trimmed.endsWith(VASP_NAME_POSCAR) || trimmed.endsWith(VASP_NAME_CONTCAR)) {
            return FILE_TYPE_VASP;
        }

        String ext = getExtension(trimmed);
        if (ext == null) {
            return FILE_TYPE_NULL;
        }

        return switch (ext.toLowerCase(Locale.ROOT)) {
            case "in" -> FILE_TYPE_QE;
            case "xyz" -> FILE_TYPE_XYZ;
            case "cif" -> FILE_TYPE_CIF;
            case "cube", "cub" -> FILE_TYPE_CUBE;
            case "xsf" -> FILE_TYPE_XSF;
            case "axsf" -> FILE_TYPE_NULL; // disabled until supported
            default -> FILE_TYPE_NULL;
        };
    }

    private static String getExtension(String path) {
        int lastDot = path.lastIndexOf('.');
        if (lastDot <= 0 || lastDot == path.length() - 1) {
            return null;
        }
        return path.substring(lastDot + 1);
    }

    public static boolean isToBeInstance(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }

        return getFileType(filePath) != FILE_TYPE_NULL;
    }

    public static AtomsReader getInstance(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("file path is empty.");
        }

        AtomsReader atomsReader;
        int fileType = getFileType(filePath);

        atomsReader = switch (fileType) {
            case FILE_TYPE_QE -> new QEReader(filePath);
            case FILE_TYPE_XYZ -> new XYZReader(filePath);
            case FILE_TYPE_CIF -> new CIFReader(filePath);
            case FILE_TYPE_CUBE -> new CubeReader(filePath);
            case FILE_TYPE_XSF -> new XSFReader(filePath, false);
            case FILE_TYPE_AXSF -> new XSFReader(filePath, true);
            case FILE_TYPE_VASP -> new VASPReader(filePath);
            default -> throw new IOException("cannot read a file: " + filePath);
        };

        return atomsReader;
    }

    protected BufferedReader reader;

    protected AtomsReader() {
        this.reader = null;
    }

    protected AtomsReader(String filePath) throws FileNotFoundException {
        this(filePath == null || filePath.isEmpty() ? null : new File(filePath));
    }

    protected AtomsReader(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file is null.");
        }

        this.reader = new BufferedReader(new FileReader(file));
    }

    public abstract Cell readCell() throws IOException;

    public void close() throws IOException {
        if (this.reader != null) {
            this.reader.close();
        }
    }
}
