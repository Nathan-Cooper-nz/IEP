from socket import *
import threading
import queue
import subprocess

class Server:

    def __init__(self):
        self.active = True
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
        self.readThread = threading.Thread(target = self.read)
        self.readThread.name = "ServerRead"
        self.readThread.deamon = True    #Kill off on its own
        self.readThread.start()


        #Start up the java front end
        self.javaThread = threading.Thread(target = self.runJava)
        self.javaThread.name = "JAVA_THREAD"
        self.javaThread.deamon = True
        self.javaThread.start()


        #Wait until we have someone to write to
        while(not self.receiver_found):
            x = 0
        self.writeThread = threading.Thread(target = self.write)
        self.writeThread.name = "ServerWrite"
        self.writeThread.deamon = True   #Kill off on its own
        self.writeThread.start()


    def read(self):
        while (self.active):
            if(self.socket!=None):
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
                else:
                    #Do something if clientMSG has a value
                    if(data.decode() == "GUI CLOSED"):
                        #Stops this thread from stopping work
                        self.socket = None
                    if data:
                        print("Received: ", data.decode)
                        self.recQueue.put(data.decode())
    '''
    Writes to a particular socket
    '''
    def write(self):
        position = 0
        while self.active:
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


    def runJava(self):
        subprocess.call(['java', '-jar', 'front.jar'])

# py_server = Server()
# position = 0
# while True:
#     voltage = math.sin(position*2*math.pi/30) * (8)
#     voltage = round(voltage, 5)
#     string = str(voltage)
#     position = position + 1
#     py_server.addToSend(string)
