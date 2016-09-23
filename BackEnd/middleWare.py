from server import *
from functionGen import *
import math
import time
import queue
import threading

class MiddleWare:

    def __init__(self):
        self.points = 90

        self.server = Server()
        self.spi = Spi(self.points)
        self.queueSize = 20
        self.proccessQueue = queue.Queue(self.queueSize)
        self.oscWindow_1 = []

        self.funcGen = FunctionGen("sin", 0, 0, 0)

        #Thread to handle reading from SPI then writing to Server
        spiThread = threading.Thread(target = self.spiRead)
        spiThread.deamon = True    #Kill off on its own
        spiThread.start()

        #Thread to handle reading from Server then writing to SPI
        serverThread = threading.Thread(target = self.serverRead)

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
                hz = 40000
                time.sleep(1/hz)


    def proccess(self, data):
        """ Perfroms neccesary calculation upon data
            data: var
                The data to be proccessed
        """
        proccessedData = data
        if(self.proccessQueue.full()):
            self.proccessQueue.get()
        if(len(self.oscWindow_1) < self.points):
            self.oscWindow_1.append(str(proccessedData))
        else:
            tmp = list(self.oscWindow_1)
            window = ', '.join(tmp)
            self.proccessQueue.put(window)
            self.oscWindow_1 = []

    def serverRead(self):
        while(True):
            userInput = self.server.read()
            if(userInput != "empty"):
                parseUser(userInput)

    def parseUser(self, text):
        splitText = text.split(",")
        if(splitText[0] == "fcnGen" and len(splitText) == 5):
            func = splitText[1]
            amp = splitText[2]
            freq = splitText[3]
            per = splitText[4]
            self.funcGen.setValues(func, amp, freq, per)

class Spi:

    def __init__(self,points):
        self.points =points
        self.pos = 0
    def read(self):
        voltage = math.sin(self.pos*2*math.pi/self.points) * (8)
        voltage = round(voltage, 5)
        string = str(voltage)
        self.pos = self.pos + 1
        return string


middleware = MiddleWare()
