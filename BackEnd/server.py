from socket import *
import threading
import queue

class Server(threading.Thread):

    def __init__(self):
        self.host = "localhost"
        self.port = 5843
        self.queueSize = 20
        self.address = (self.host, self.port)
        self.receiver_found = False

        self.client = (0,0)     #Client addr and port that info is sent to
        self.sendQueue = queue.Queue(self.queueSize)

        self.recQueue = queue.Queue(self.queueSize)

        self.socket = socket(AF_INET, SOCK_DGRAM)
        self.socket.bind(self.address)

        print("Server Started")


        #Begin Listening Thread
        readThread = threading.Thread(target = self.read)
        readThread.deamon = True    #Kill off on its own
        readThread.start()


    def read(self):
        while True:
            data, client = self.socket.recvfrom(256)
            if(not self.receiver_found):
                print ("Client Port",client[1])
                print ("Data", data.decode())
                print ("Typeof data", type(data.decode()))
                if(data.decode().isdigit()):
                    self.client = (client[0],int(data.decode()))
                    print("Connected")
                    #self.addToSend("Connected")
                    self.receiver_found = True

                    #Start Write Thread now that client is found
                    writeThread = threading.Thread(target = self.write)
                    writeThread.deamon = True   #Kill off on its own
                    writeThread.start()
            else:
                #Do something if clientMSG has a value
                if data:
                    print("Received: ", data.decode)
                    self.recQueue.put(data.decode())


    '''
    Writes to a particular socket
    '''
    def write(self):
        position = 0
        while True:
            if(not self.sendQueue.empty()):
                message = self.sendQueue.get()
                encoded_msg = message.encode()
                self.socket.sendto(encoded_msg, self.client)


    '''
    Adds to queue of messages to send
    Use this method to tell the write thread what to send
    '''
    def addToSend(self, msg):
        # print("Adding Message: ", msg)
        # if(not self.sendQueue.full()):
        self.sendQueue.put(msg)

    def recentMessage(self):
        if(not self.recQueue.empty()):
            return self.recQueue.get()
        else:
            return "empty"

# py_server = Server()
# position = 0
# while True:
#     voltage = math.sin(position*2*math.pi/30) * (8)
#     voltage = round(voltage, 5)
#     string = str(voltage)
#     position = position + 1
#     py_server.addToSend(string)
