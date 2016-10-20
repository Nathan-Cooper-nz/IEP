import time

class Trigger:

    def __init__(self, channel ,level):
        self.level = level
        self.channel = channel
        self.window = []
        self.record = False
        self.readyToSend = False

        self.snapShot = True
        self.min = 1000000
        self.max = -1000000

        self.old_min = 1000000
        self.old_max = -1000000

        self.dev = 0.5

        if(self.snapShot):
            #Used for snapshotWindow
            self.recordTime = 1
            self.delay = 2
            self.delayStart = 0
            self.startTime = 0
        else:
            #Used for wavelenWindow
            self.fallen = False                 #Has the value dipped below level again
            self.watch = []                     #Last 4 values from hitting trigger again


    def beginWindow(self, data):
        if(data>=self.level):
            self.purge()
            if(time.time()-self.delayStart >= self.delay or self.delayStart==0):
                self.window.append(data)
                self.record = True
                print("Start Record")


    def addToWindow(self, data):
        if(self.record):
            self.window.append(data)
            self.min = min(self.min, data)
            self.max = max(self.max, data)

            if(self.snapShot):
                self.snapshotWindow(data)
            else:
                self.wavelenWindow(data)
        if(self.readyToSend):
            if(self.old_min != 1000000 and self.old_max != -1000000):
                self.sigChange()
            self.old_min = self.min
            self.old_max = self.max
            self.delayStart = time.time()
            print("Window Done")


    def wavelenWindow(self, data):
        """Creates a trigger window for approximatly one wavelength
        Measures the gradient when triggered and keeps recording
        until window is triggered again and gradient is going the
        same direction.
        """
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
                # print(self.record)
                self.readyToSend = True


    def snapshotWindow(self, data):
        """ A tirgger window of a fixed duration
        This window will record for self.recordTime seconds
        before marking the trigger window as reay to send
        and stop recording data
        """
        curTime = time.time()
        diff = curTime - self.startTime
        if(diff >= self.recordTime):
            self.readyToSend = True
            self.record = False

    def change(self, diff):
        """Used to indicate the gradient
        diff > 0 = postive gradient
        diff < 0 = neagitive gradient
        diff == 0, no gradient
        """
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

        self.min = 1000000
        self.max = -1000000

        if(self.snapShot):
            #Used for snapshotWindow
            self.startTime = time.time()
        else:
            #Used for wavelenWindow
            self.fallen = False
            self.watch = []

    def sigChange(self):
        minDiff = abs(self.min - self.old_min)
        maxDiff = abs(self.max - self.old_max)

        if(minDiff <= self.dev or maxDiff <= self.dev):
            self.readyToSend = False


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
