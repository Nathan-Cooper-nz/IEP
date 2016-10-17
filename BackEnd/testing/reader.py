import sys
from struct import *
import binascii
# import re

# numbers = re.compile('\d+(?:\.\d+)?')

for line in sys.stdin:

    cleanLine = line.rstrip()

    # vals = list(cleanLine)
    vals = cleanLine.split()
    print("\n\n")
    print (vals)

    binVals = []
    for val in vals:
        binVals.append((bin(int(val))[2:]).zfill(8)[::-1])

    adjustedVal = (int(binVals[1] + binVals[2], 2) - 512)
    if(adjustedVal < 0):
        voltage = ( 10000 / 512) * adjustedVal
    else:
        voltage = ( 10000 / 511) * adjustedVal



    print("flipped:")
    for val in binVals:
        print(val, end=", ")
    print("\ncombined", end=": ")
    print(str(int(binVals[0], 2)) + ", " + str(int(binVals[1] + binVals[2], 2)))
    print(str(voltage) +"mv")
