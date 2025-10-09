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

package burai.com.env;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Properties;

import burai.ver.Version;

public final class EnvProperties {

    private Properties propertiesSystem;
    private Properties propertiesUser;

    private String filePathUser;

     EnvProperties(String propName) {
        if (propName == null) {
            throw new IllegalArgumentException("propName is null.");
        }

        String propName2 = propName.trim();
        if (propName2.isEmpty()) {
            throw new IllegalArgumentException("propName is empty.");
        }

        this.createPropertiesSystem(propName2);
        this.propertiesUser = null;
        this.filePathUser = null;
    }

     synchronized void setUserFile(String filePath) {
        if (filePath == null) {
            return;
        }

        String filePath2 = filePath.trim();
        if (filePath2.isEmpty()) {
            return;
        }

        try {
            File file = new File(filePath2);
            if (!file.exists()) {
                this.clonePropertiesSystem(filePath2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        this.createPropertiesUser(filePath2);

        if (this.propertiesUser == null) {
            return;
        }

        // check version
        String userVersion = this.propertiesUser.getProperty("version");

        if (Version.hasMajorMinorDifference(userVersion)) {
            try {
                this.clonePropertiesSystem(filePath2);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            this.createPropertiesUser(filePath2);
        }
    }

     synchronized String getProperty(String key) {
        if (key == null) {
            return null;
        }

        if (this.propertiesUser != null) {
            String value = this.propertiesUser.getProperty(key);
            if (value != null) {
                return value;
            }
        }

        if (this.propertiesSystem != null) {
            return this.propertiesSystem.getProperty(key);
        }

        return null;
    }

     synchronized void setProperty(String key, String value) {
        if (key == null) {
            return;
        }

        if (this.propertiesUser != null) {
            if (value == null) {
                this.propertiesUser.remove(key);
            } else {
                this.propertiesUser.setProperty(key, value);
            }

            this.printPropertiesUser();
        }
    }

    private void createPropertiesSystem(String propName) {
        URL url = propName == null ? null : EnvProperties.class.getResource(propName);
        if (url == null) {
            return;
        }

        this.propertiesSystem = null;

        try {
            this.loadPropertiesSystem(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPropertiesUser(String filePath) {
        if (filePath == null) {
            return;
        }

        this.propertiesUser = null;
        this.filePathUser = filePath;

        try {
            this.loadPropertiesUser(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPropertiesUser() {
        if (this.filePathUser == null) {
            return;
        }

        try {
            this.storePropertiesUser(this.filePathUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPropertiesSystem(URL url) throws IOException {
        if (url == null) {
            return;
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(url.openStream())) {
            this.propertiesSystem = new Properties();
            this.propertiesSystem.load(inputStream);
        }
    }

    private void loadPropertiesUser(String filePath) throws IOException {
        if (filePath == null) {
            return;
        }

        try (Reader reader = new BufferedReader(new FileReader(filePath.trim()))) {
            this.propertiesUser = new Properties();
            this.propertiesUser.load(reader);
        }
    }

    private void storePropertiesUser(String filePath) throws IOException {
        if (filePath == null || this.propertiesUser == null) {
            return;
        }

        this.writeProperties(filePath, this.propertiesUser);
    }

    private void clonePropertiesSystem(String filePath) throws IOException {
        if (filePath == null || this.propertiesSystem == null) {
            return;
        }

        this.writeProperties(filePath, this.propertiesSystem);
    }

    private void writeProperties(String filePath, Properties properties) throws IOException {
        if (filePath == null || properties == null) {
            return;
        }

        try (Writer writer = new BufferedWriter(new FileWriter(filePath.trim()))) {
            properties.store(writer, "These are properties of BURAI");
        }
    }
}
