#!/bin/sh

DIR=`dirname $0`

cd ${DIR}

FILE=BURAI.desktop

echo "[Desktop Entry]"                          >  ${FILE}
echo "Type=Application"                         >> ${FILE}
echo "Terminal=false"                           >> ${FILE}
echo "Version=1.3"                              >> ${FILE}
echo "Name=BURAI"                               >> ${FILE}
echo "GenericName=GUI of Quantum ESPRESSO"      >> ${FILE}
echo "Comment=BURAI is GUI of Quantum ESPRESSO" >> ${FILE}
echo "Path=${PWD}/bin"                          >> ${FILE}
echo "Exec=${PWD}/bin/burai.sh %F"              >> ${FILE}
echo "Icon=${PWD}/bin/burai.png"                >> ${FILE}

chmod +x ${FILE}
