# BURAI

A graphical user interface (GUI) system for [Quantum ESPRESSO](http://www.quantum-espresso.org), developed using JavaFX.

BURAI simplifies quantum mechanical calculations by providing an intuitive interface for computational materials science and first-principles simulations.

---
## Features
- **User-friendly GUI** for Quantum ESPRESSO
- **Visual project management** for computational materials science
- **Interactive visualization** of atomic structures and simulation results
- **Cross-platform** support (Windows, Linux, macOS)

---
## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.8+ (or use included Maven Wrapper)

### Build
On Linux or macOS:
```bash
./mvnw clean install
```
On Windows:
```shell
mvnw.cmd clean install
```

### Run
On Linux or macOS:
```bash
./mvnw run javafx:run
```
On Windows:
```shell
mvnw.cmd run javafx:run
```

### Build a distributed packet
For Linux:
```bash
./mvnw clean package -P dist-linux
```
For Windows:
```shell
mvnw.cmd clean package -P dist-win
```
For Intel Mac:
```shell
mvnw.cmd clean package -P dist-mac
```
For Apple Silicon Mac:
```shell
mvnw.cmd clean package -P dist-mac-aarch64
```
For all platforms:
```shell
./mvnw clean package -P dist-win,dist-linux,dist-mac,dist-mac-aarch64
```
or
```shell
./mvnw clean package
```

⚠️ **Note:** When using Maven Wrapper with `Git Bash` on Windows, ensure that the `/tmp` mount to folder with write permission.

---
## Development
### IDE Configuration
- Java 17
- Maven 3.8+
- Scene Builder 17

To debug with your IDE, configure the following VM options in your run configuration:
* --module-path
* --add-modules

To get the correct values, run Maven with debug output:
```shell
./mvnw javafx:run -X
```
Copy the `module-path` and `add-modules` values from the output.

## Documentation
### Prerequisites
* Python 3.x
* Pip (Python package manager)
* Sphinx: pip install sphinx
* (For PDF) LaTeX distribution (e.g., MiKTeX, TeX Live)
* (For PDF) Perl
* (For PDF) ImageMagick

### Build Documentation Instructions
1. Navigate to the docs directory:
```cd docs```
2. View available formats:
3. Build documentation:
```make html```
4. View documentation:
   * HTML documentation:
   ```make html```
   * ePub documentation:
   ```make epub```
   * PDF documentation:
   ```make latexpdf```

On Windows, use `make.bat` instead of `make`
```shell
make.bat html
make.bat epub
make.bat latexpdf
```
Documentation output will be in `docs/build/`

---
## Project History
Copyright (C) 2016-2020 BURAI-team <nisihara.burai@gmail.com>

### This Fork
This fork modernizes BURAI with:
* Support for Java 17+
* Maven 3.8+ build system
* Standard Maven project layout
* Maven Wrapper for build consistency

---
## Dependencies
### External Libraries
* exp4j - Apache License 2.0
* Gson - Apache License 2.0
* JCodec - FreeBSD License

### Resources
- SVG icons from [FLATICON](http://www.flaticon.com)

---
## License
BURAI is free software licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the LICENSE file or visit http://www.apache.org/licenses/LICENSE-2.0 for more details.

---
## Contributing
Contributions are welcome! Please feel free to submit issues and pull requests.

---
## Links
* [Quantum ESPRESSO](http://www.quantum-espresso.org)
* [Original BURAI Project](https://github.com/BURAI-team/burai)