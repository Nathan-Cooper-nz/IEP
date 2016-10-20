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
        hardwareThread.name = "HARDWARE THREAD"
        hardwareThread.deamon = True    #Kill off on its own
        hardwareThread.start()

        self.stop = False
        self.min = 100000000000000
        self.max =-1000000000000000


    def hardwareRead(self):
        for line in sys.stdin:

            cleanLine = line.rstrip()

            # vals = list(cleanLine)
            vals = cleanLine.split()
            # print("\n\n")
            # print (vals)

            binVals = []
            sys.stdout.write(str(vals) + "\n")
            for val in vals:
                binVals.append((bin(int(val))[2:]).zfill(8)[::-1])

            # sys.stdout.write(str(binVals)+"\n")
            # hexVal = hex(str(binVals[1] + binVals[0]))
            # sys.stdout.write("Hex:\t"+str(hexVal)+"\n")
            # sys.stdout.write("Bin:\t"+str(binVals[1] + binVals[0])+"\n")


            adjustedVal = (int(binVals[1] + binVals[0], 2))
            sys.stdout.write("Adj Val:\t"+str(adjustedVal)+"\n")
            sys.stdout.write("\t\t"+str(binVals[1])+" "+str(binVals[0])+"\n")
            sys.stdout.write("\t\t"+str(binVals[1]+binVals[0])+"\n")
            sys.stdout.write("One:\t\t"+str(int(binVals[1],2))+"\n")
            sys.stdout.write("Zero:\t\t"+str(int(binVals[0],2))+"\n\n")

            # self.min = min(self.min,adjustedVal)
            # self.max = max(self.max,adjustedVal)
            # sys.stdout.write("Min: \t"+str(self.min)+"\n")
            # sys.stdout.write("Max: \t"+str(self.max)+"\n")

            voltage = (20000 / 1023 )* adjustedVal - 10000
            # if(adjustedVal < 0):
            #     voltage = ( 10000 / 512) * adjustedVal
            # else:
            #     voltage = ( 10000 / 511) * adjustedVal
            self.dataQueue.put(voltage/1000.0)

            if(self.stop):
                break

    def read(self):
        if(not self.dataQueue.empty()):
            return str(self.dataQueue.get())
        else:
            return "empty"
