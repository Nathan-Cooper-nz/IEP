from server import *
from functionGen import *
from trigger import *
from spi import *
import math
import time
import queue
import threading
import sys

class MiddleWare:

    def __init__(self):
        self.points = 60
        self.living = True

        self.server = Server()
        self.spi = Spi(self.points)
        self.queueSize = 20
        self.proccessQueue = queue.Queue(self.queueSize)
        self.oscWindow_1 = []

        self.trigger = Trigger(1,-5)

        #Thread to handle reading from SPI then writing to Server
        spiThread = threading.Thread(target = self.spiRead)
        spiThread.name = "SPI_Thread"
        spiThread.deamon = True    #Kill off on its own
        spiThread.start()

        #Thread to handle reading from Server then writing to SPI
        serverThread = threading.Thread(target = self.serverRead)
        serverThread.name = "SERVER_Thread"
        serverThread.deamon = True
        serverThread.start()

        print(threading.active_count())
        for thrd in threading.enumerate():
            if(thrd.isDaemon):
                print(thrd)

    def spiRead(self):
        """ reads from the spi then proccess the data before passing on to server.py

        """
        count = 0;
        while(self.living):
            if(not self.proccessQueue.empty()):
                message = self.proccessQueue.get()
                if(self.server.receiver_found):
                    self.server.addToSend(message)

            else:
                data = self.spi.read()
                # if count % 10 == 0:
                #     print(data)
                # count += 1
                if(str(data) != "empty"):
                    self.proccess(str(data))
                hz = 40000
                time.sleep(1/hz)
                # time.sleep(.25)
            # print(self.spi.dataQueue.qsize())


    def proccess(self, data):
        """ Perfroms neccesary calculation upon data
            data: var
                The data to be proccessed
        """
        proccessedData = float(data)
        # print(proccessedData)
        if(self.proccessQueue.full()):
            self.proccessQueue.get()

        if(self.trigger.readyToSend):
            print("Send Data")
            trigger_window = list(self.trigger.window)
            tmp = []
            size = len(self.trigger.window)
            i = 0
            while i < size:
                tmp.append(str(trigger_window[i]))
                i = i + 1

            window = ', '.join(tmp)
            print(len(tmp))
            self.proccessQueue.put(window)

        if(float(proccessedData) >= self.trigger.level and not self.trigger.record):
            # print("HERE")
            self.trigger.beginWindow(float(proccessedData))
        # if(self.trigger.level>= 0  and self.trigger.level >=proccessedData):
        #     self.trigger.beginWindow(proccessedData)
        # elif(self.trigger.level< 0  and self.trigger.level <proccessedData):
        #     self.trigger.beginWindow(proccessedData)

        if(self.trigger.record):
            self.trigger.addToWindow(proccessedData)

            # self.trigger = []

        # if(len(self.oscWindow_1) < self.points-1):
        #     self.oscWindow_1.append(str(proccessedData))
        # else:
        #     tmp = list(self.oscWindow_1)
        #     window = ', '.join(tmp)
        #     self.proccessQueue.put(window)
        #     self.oscWindow_1 = []

    def serverRead(self):
        while(self.living):
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
            self.trigger.purge()
        elif(text == "GUI CLOSED"):
            print("QUITTING TIME")
            self.living = False
            self.server.active = False


# class Spi:
#
#     def __init__(self,points):
#         self.points =points
#         self.pos = 30
#         #FunctionGen(Type, Amp, Freq, Peri)
#         self.funcGen = FunctionGen("sin", 1, 1, 1)
#
#     def read(self):
#         amp  = self.funcGen.amplitude
#         freq = self.funcGen.frequency
#         angle = self.pos * math.pi/180
#         voltage = math.sin(freq*angle) * (amp)
#
#         # voltage = math.sin(self.pos*2*math.pi/self.points) * (amp)
#         voltage = round(voltage, 3)
#         string = str(voltage)
#         self.pos = self.pos + 1
#         # if(self.pos==360):
#         #     self.pos = 0;
#         return string


middleware = MiddleWare()
