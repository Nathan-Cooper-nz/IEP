import sys
from struct import *
import binascii
from functionGen import *
import queue
import threading

class Spi:

    def __init__(self,points):
        self.points =points
        self.pos = 30
        #FunctionGen(Type, Amp, Freq, Peri)
        self.funcGen = FunctionGen("sin", 1, 1, 1)
        self.dataQueue = queue.Queue(2000)


        hardwareThread = threading.Thread(target = self.hardwareRead)
        hardwareThread.deamon = True    #Kill off on its own
        hardwareThread.start()




    def hardwareRead(self):
        for line in sys.stdin:

            cleanLine = line.rstrip()

            # vals = list(cleanLine)
            vals = cleanLine.split()
            # print("\n\n")
            # print (vals)

            binVals = []
            for val in vals:
                binVals.append((bin(int(val))[2:]).zfill(8)[::-1])

            adjustedVal = (int(binVals[1] + binVals[0], 2) - 512)
            if(adjustedVal < 0):
                voltage = ( 10000 / 512) * adjustedVal
            else:
                voltage = ( 10000 / 511) * adjustedVal
            self.dataQueue.put(voltage/1000.0)

    def read(self):
        if(not self.dataQueue.empty()):
            return str(self.dataQueue.get())
        else:
            return "empty"
