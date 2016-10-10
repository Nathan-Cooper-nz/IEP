import sys
from struct import *
import re

numbers = re.compile('\d+(?:\.\d+)?')

for line in sys.stdin:
    vals = line.split()
    index = 0
    for val in vals:
        # bindata = bin(int.from_bytes(val.encode(), 'big'))
        bindata = numbers.findall(str(val.encode()))
        print(str(index) + ": " + bindata[0], end=" ")
        index += 1
    print()
