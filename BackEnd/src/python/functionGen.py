class FunctionGen:

    def __init__(self, t, a, f, p):
        self.amplitude = a
        self.frequency = f
        self.period = p
        self.type = t

    def setAmp(self, a):
        self.amplitude = a

    def setFreq(self, f):
        self.frequency = f

    def setPer(self, p):
        self.period = p

    def setType(self, t):
        self.type = t

    def setValues(self, t, a, f, p):
        self.setAmp(int(a))
        self.setFreq(int(f))
        self.setPer(int(p))
        self.setType(t)
        self.update()


    def update(self):
        print(self.type, self.amplitude, self.frequency, self.period)
        print("send to usb")
