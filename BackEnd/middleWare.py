from server import *;
import math
import time
import queue;
import threading

class MiddleWare:

    def __init__(self):
        self.server = Server()
        self.spi = Spi()
        self.queueSize = 20
        self.proccessQueue = queue.Queue(self.queueSize);

        #Thread to handle reading from SPI then writing to Server
        spiThread = threading.Thread(target = self.spiRead)
        spiThread.deamon = True    #Kill off on its own
        spiThread.start()

        #Thread to handle reading from Server then writing to SPI


    def spiRead(self):
        while(True):
            if(not self.proccessQueue.empty()):
                message = self.proccessQueue.get();
                if(self.server.receiver_found):
                    self.server.addToSend(message);

            else:
                data = self.spi.read();
                self.proccess(str(data));
                time.sleep(1/10)


    def proccess(self, data):
        proccessedData = data;
        if(self.proccessQueue.full()):
            self.proccessQueue.get();
        self.proccessQueue.put(str(proccessedData));

class Spi:

    def __init__(self):
        self.x =0;
        self.pos = 0;
    def read(self):
        voltage = math.sin(self.pos*2*math.pi/30) * (8)
        voltage = round(voltage, 5)
        string = str(voltage)
        self.pos = self.pos + 1
        return string;


middleware = MiddleWare()
