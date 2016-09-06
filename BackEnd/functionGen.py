class functionGen:

    def __init__(self, a, f, p):
        self.amplitude = a;
        self.frequency = f;
        self.period = p;

    def setAmp(self, a):
        self.amplitude = a;
        self.update();

    def setFreq(self, f):
        self.frequency = f;
        self.update();

    def setPer(self, p):
        self.period = p;
        self.update();

    def setValues(self, a, f, p):
        self.setAmp(a);
        self.setFreq(f);
        self.setPer(p);
    
    def _update(self):
        print("send to usb");
