from Tkinter import *

class Application(Frame):
    def __init__(self, master):
        frame = Frame(master)
        frame.pack              #Make Visible

        sectionWd = 25
        sectionHt = 1
        buttonHt = 1
        curRow = 0

        selectedFormOne = "Sine"
        selectedFormTwo = "Sine"
        
        Label(text="Signal One", width=50,height=1).grid(row=curRow,column=1, columnspan=2)
        curRow+=1
        Label(text="Waveform", width=sectionWd, height=sectionHt).grid(row=curRow, column=1)
        curRow+=1
        
        Label(text="Voltage", width=sectionWd, height=sectionHt).grid(row=curRow,column=1)
        Scale(from_=0, to=5, orient=HORIZONTAL).grid(row=curRow,column=2)
        curRow+=1

        Label(text="Hertz", width=sectionWd, height=sectionHt).grid(row=curRow,column=1)
        Scale(from_=0, to=5, orient=HORIZONTAL).grid(row=curRow,column=2)
        curRow+=1        

        Label(text="Period", width=sectionWd, height=sectionHt).grid(row=curRow,column=1)
        Scale(from_=0, to=5, orient=HORIZONTAL).grid(row=curRow,column=2)
        curRow+=1

        Label(text="Osciloscope", relief=RIDGE,
              width=100,height=30).grid(row=0,column=0,rowspan=curRow)
        Label(text="FunctionGen", relief=RIDGE,
              width=100,height=10).grid(row=curRow,column=0)

        
root = Tk()                  #Create Applcation Window

app = Application(root)

root.mainloop()

