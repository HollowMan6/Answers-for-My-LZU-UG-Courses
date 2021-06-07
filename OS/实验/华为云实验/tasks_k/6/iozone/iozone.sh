#/bin/bash

wget http://www.iozone.org/src/current/iozone3_489.tar
tar -xvf iozone3_489.tar 
cd iozone3_489
cd src/current/
make linux-AMD64
./iozone -Raz -n 512m -g 8g -y 32k -i 0 -i 1 -b /home/iozone.xls
