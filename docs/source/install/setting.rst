Setting
=======

1. Unzip :doc:`the downloaded file <download>`

2. Copy it to any directory on your computer e.g.:

  - "C:\\BURAI1.3_Windows\\" for Windows
  - "/Applications/BURAI1.3.app" for Max OSX
  - "/usr/share/applications/BURAI1.3" for Ubuntu


3. Install `JRE17 or later version <https://java.com/download/>`_, if your computer does not have it.
   For Ubuntu, you can get libraries of Java as

  .. code-block:: bash

       > sudo apt-get install openjdk-17-jdk

4. If you want to use MPI for Mac OSX, install OpenMPI using Homebrew as

  .. code-block:: bash

       > brew install open-mpi

5. If you want to use precompiled executables of QE for Ubuntu,
   GFortran and OpenMPI have to be installed through apt-get as.

  .. code-block:: bash

       > sudo apt-get install gfortran
       > sudo apt-get install openmpi-bin libopenmpi-dev

6. For Ubuntu, executing burai-1.3.3/makeLauncher.sh yields the launcher BURAI.desktop .
