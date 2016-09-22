from server import *
from functionGen import *
import math
import time
import queue
import threading

class MiddleWare:

    def __init__(self):
        self.server = Server()
        self.spi = Spi()
        self.queueSize = 200
        self.proccessQueue = queue.Queue(self.queueSize)



        #Thread to handle reading from SPI then writing to Server
        spiThread = threading.Thread(target = self.spiRead)
        spiThread.deamon = True    #Kill off on its own
        spiThread.start()

        #Thread to handle reading from Server then writing to SPI
        serverThread = threading.Thread(target = self.serverRead)
        serverThread.deamon = True
        serverThread.start()

    def spiRead(self):
        """ reads from the spi then proccess the data before passing on to server.py

        """
        while(True):
            if(not self.proccessQueue.empty()):
                message = self.proccessQueue.get()
                if(self.server.receiver_found):
                    self.server.addToSend(message)

            else:
                data = self.spi.read()
                self.proccess(str(data))
                time.sleep(1/27)


    def proccess(self, data):
        """ Perfroms neccesary calculation upon data
            data: var
                The data to be proccessed
        """
        proccessedData = data
        if(self.proccessQueue.full()):
            self.proccessQueue.get()
        self.proccessQueue.put(str(proccessedData))

    def serverRead(self):
        while(True):
            userInput = self.server.recentMessage()
            if(userInput != "empty"):
                self.parseUser(userInput)

    def parseUser(self, text):
        splitText = text.split(",")
        if(splitText[0] == "fcnGen" and len(splitText) == 5):
            func = splitText[1]
            amp = splitText[2]
            freq = splitText[3]
            per = splitText[4]
            self.spi.funcGen.setValues(func, amp, freq, per)

class Spi:
    def __init__(self):
        self.x =0
        self.pos = 0
        self.vis = False
        self.genSinTable()
        #FunctionGen(type,amp,freq,period)
        self.funcGen = FunctionGen("sine", 1, 1, 1)

    def genSinTable(self):
        points = 180
        self.table = [None]*points

        i=0
        while (i<points):
            self.table[i]=math.sin(i*math.pi/points)
            i = i + 1
    def read(self):
        # graphType = self.funcGen.type
        # graphAmp  = float(self.funcGen.amplitude)
        #
        # if (graphType == "sine"):
        #     voltage = graphAmp * math.sin(self.pos * math.pi/30)
        #     voltage = round(voltage, 5)
        #     string = str(voltage)
        #     self.pos = self.pos + 1
        #     return string
        funcGen = self.funcGen;
        graphAmp  = float(funcGen.amplitude)
        graphFreq = float(funcGen.frequency)
        voltage = math.sin(self.pos*graphFreq*math.pi/30) * (graphAmp)
        # voltage = graphAmp * self.table[self.pos]
        voltage = round(voltage, 5)
        string = str(voltage)
        self.pos = self.pos + 1
        # self.pos = self.pos == 179 if 0 else self.pos + 1
        if(voltage == 0):
            if(not self.vis):
                self.vis = True
            else:
                print (self.pos)
                self.vis = False
        return string


middleware = MiddleWare()
