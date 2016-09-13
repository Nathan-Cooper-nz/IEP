class FunctionGen:

    def __init__(self, t, a, f, p):
        self.amplitude = a
        self.frequency = f
        self.period = p
        self.type = t

    def _setAmp(self, a):
        self.amplitude = a

    def _setFreq(self, f):
        self.frequency = f

    def _setPer(self, p):
        self.period = p

    def _setType(self, t):
        self.type = t

    def setValues(self, t, a, f, p):
        self.setAmp(a)
        self.setFreq(f)
        self.setPer(p)
        self.setType(t)
        self.update()


    def _update(self):
        print(self.type, self.amplitude, self.frequency, self.period)
        print("send to usb")
