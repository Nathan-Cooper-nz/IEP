class Trigger:

    def __init__(self, channel ,level):
        self.level = level
        self.channel = channel
        self.window = []
        self.record = False
        self.readyToSend = False
        self.fallen = False                 #Has the value dipped below level again
        self.watch = []                     #Last 4 values from hitting trigger again


    def beginWindow(self, data):
        if(data>=self.level):
            self.window = []
            self.window.append(data)
            self.record = True
            self.readyToSend = False
            self.fallen = False
            self.watch = []
            print("Start Record")

    def addToWindow(self, data):
        if(self.record):
            self.window.append(data)
            if(len(self.window) == 4):
                g1 = self.change(self.window[1] - self.window[0])
                g2 = self.change(self.window[2] - self.window[1])
                g3 = self.change(self.window[3] - self.window[2])
                self.gradient = self.change(g1+g2+g3)
            #Check to see if we can start checking for the end
            elif(self.fallen and len(self.window) > 4 and data>=self.level):
                self.watch.append(data)
            #Keep adding to the array of last 4 values
            elif(len(self.watch)>0):
                self.watch.append(data)
            #Gone below level now allow the window to end
            elif(data < self.level):
                self.fallen = True

            if(len(self.watch)==4):
                # print(self.watch)
                g1 = self.change(self.watch[1] - self.watch[0])
                g2 = self.change(self.watch[2] - self.watch[1])
                g3 = self.change(self.watch[3] - self.watch[2])
                curGrad = self.change(g1+g2+g3)
                # print(str(g1)+", "+str(g2)+", "+str(g3))
                # print(curGrad)
                self.record = not (self.gradient)==(curGrad)

                #Remove first index
                if(self.record):
                    del self.watch[0]
                else:
                    # print(self.window)
                    self.window = self.window[0:len(self.window)-3]
                    print("Window Done")
                    # print(self.record)
                    self.readyToSend = True

                    # print(self.window)
            # if(len(self.Window) > 30):
            #     self.readyToSend = True
        # elif(len(self.window)>0):
        #     print("HI")
        #     self.readyToSend = True

    def change(self, diff):
        if(diff > 0):
            return 1
        elif(diff == 0):
            return 0
        elif(diff < 0):
            return -1

    def purge(self):
        self.window = []
        self.record = False
        self.readyToSend = False
        self.fallen = False
        self.watch = []


# trigger = Trigger(1,5)
# trigger.beginWindow(6)
# trigger.addToWindow(7)
# trigger.addToWindow(8)
# trigger.addToWindow(9)
# trigger.addToWindow(8)
# trigger.addToWindow(7)
# trigger.addToWindow(6)
# trigger.addToWindow(5)
# trigger.addToWindow(4)
##Start looking for the end now that we have gone below trigger level
#
# #Begin Check
# trigger.addToWindow(5)
# trigger.addToWindow(6)
# trigger.addToWindow(5)
