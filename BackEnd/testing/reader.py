import sys
from struct import *
import binascii
# import re

# numbers = re.compile('\d+(?:\.\d+)?')

for line in sys.stdin:

    cleanLine = line.rstrip()

    vals = list(cleanLine)
    print("\n\n")
    print (vals)

    binVals = []
    for val in vals:
        binVals.append((bin(ord(val))[2:]).zfill(8))

    print("norm: ")
    print(binVals[0] + ", " + binVals[1] + ", " + binVals[2])
    print("flipped:")
    for val in binVals:
        print(val[::-1], end=", ")
    print("combined")
    print(str(int(binVals[0], 2)) + ", " + str(int(binVals[1] + binVals[2], 2)))
