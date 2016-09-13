class Trigger:

    def __init__(self, channel ,level):
        self.level = level
        self.channel = channel
        self.window = []
        self.record = False
        self.readyToSend = False


    def beginWindow(self, data):
        self.window = []
        self.window.append(data)
        self.record = True
        self.readyToSend = False

    def addToWindow(self, data):
        if(self.record):
            self.window.append(data)
            if(len(self.window) == 4):
                g1 = self.change(self.window[0] - self.window[1])
                g2 = self.change(self.window[1] - self.window[2])
                g3 = self.change(self.window[2] - self.window[3])
                self.gradient = (g1+g2+g3) / 3
            elif(len(self.window) > 4 and data>self.level):
                i = len(self.window)-4
                g1 = self.change(self.window[i] - self.window[i+1])
                g2 = self.change(self.window[i+1] - self.window[i+2])
                g3 = self.change(self.window[i+2] - self.window[i+3])
                self.record = (self.gradient)==((g1+g2+g3)/3)
        elif(not self.window.empty()):
            self.readyToSend = True

    def change(self, diff):
        if(diff > 0):
            return 1
        elif(diff == 0):
            return 0
        elif(diff < 0):
            return -1
