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
        self.queueSize = 20
        self.proccessQueue = queue.Queue(self.queueSize)


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
                time.sleep(1/9)


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

    def __init__(self):
        self.x =0
        self.pos = 0
    def read(self):
        voltage = math.sin(self.pos*2*math.pi/30) * (8)
        voltage = round(voltage, 5)
        string = str(voltage)
        self.pos = self.pos + 1
        return string


middleware = MiddleWare()
