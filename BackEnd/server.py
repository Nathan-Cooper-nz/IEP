from socket import *
import math
import threading
import queue
import time

class Server(threading.Thread):

    def __init__(self):
        self.host = gethostname()
        self.port = 5843
        self.queueSize = 20
        self.address = (self.host, self.port)


        self.client = (0,0)     #Client addr and port that info is sent to
        self.sendQueue = queue.Queue(self.queueSize);

        self.recQueue = queue.Queue(self.queueSize);

        self.socket = socket(AF_INET, SOCK_DGRAM)
        self.socket.bind(self.address)

        print("Server Started");

        #Begin Listening Thread
        readThread = threading.Thread(target = self.read)
        readThread.deamon = True    #Kill off on its own
        readThread.start()


    def read(self):
        receiver_found = False;
        while True:
            data, client = self.socket.recvfrom(256)
            if(not receiver_found):
                print ("Client Port",client[1])
                print ("Data", data.decode())
                print ("Typeof data", type(data.decode()))
                if(data.decode().isdigit()):
                    self.client = (client[0],int(data.decode()))
                    print("Connected")
                    #self.addToSend("Connected")
                    receiver_found = True;

                    #Start Write Thread now that client is found
                    writeThread = threading.Thread(target = self.write)
                    writeThread.deamon = True   #Kill off on its own
                    writeThread.start()
            else:
                #Do something if clientMSG has a value
                if data:
                    print("Received: ", data)
                    self.recQueue.put(data)


    '''
    Writes to a particular socket
    '''
    def write(self):
        position = 0
        while True:
            if(not self.sendQueue.empty()):
                message = self.sendQueue.get()
                print("Sending Message:",message)
                encoded_msg = message.encode()
                print(self.client)
                self.socket.sendto(encoded_msg, self.client)
            else:
                voltage = math.sin(position*2*math.pi/30) * (8)
                voltage = round(voltage, 5)
                string = str(voltage)
                position = position + 1
                py_server.addToSend(string)
                time.sleep(1/3)


    '''
    Adds to queue of messages to send
    Use this method to tell the write thread what to send
    '''
    def addToSend(self, msg):
        print("Adding Message: ", msg)
        # if(not self.sendQueue.full()):
        self.sendQueue.put(msg)

    def recentMessage(self):
        if(not self.recQueue.empty()):
            return recQueue.get()

py_server = Server()
# position = 0
# while True:
#     voltage = math.sin(position*2*math.pi/30) * (8)
#     voltage = round(voltage, 5)
#     string = str(voltage)
#     position = position + 1
#     py_server.addToSend(string)