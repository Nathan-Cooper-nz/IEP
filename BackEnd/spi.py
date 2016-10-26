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
            self.dataQueue.put(float(line)/1000.0)

            if(self.stop):
                break

    def read(self):
        if(not self.dataQueue.empty()):
            return str(self.dataQueue.get())
        else:
            return "empty"
