from Tkinter import *
import ttk

class Application(Frame):
    def __init__(self, master):
        frame = Frame(master)

        container = Tkinter.Frame(self)
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
        
        Label(text="Voltage", width=sectionWd, height=sectionHt).grid(row=curRow,column=1)##voltage slider
        Scale(from_=0, to=5, orient=HORIZONTAL).grid(row=curRow,column=2)
        curRow+=1

        Label(text="Frequency", width=sectionWd, height=sectionHt).grid(row=curRow,column=1)##freq slider
        Scale(from_=0, to=5, orient=HORIZONTAL).grid(row=curRow,column=2)
        curRow+=1        

        Label(text="Period", width=sectionWd, height=sectionHt).grid(row=curRow,column=1)##period slider
        Scale(from_=0, to=5, orient=HORIZONTAL).grid(row=curRow,column=2)
        curRow+=1

        Label(text="FG", relief=RIDGE, width=50, height=15).grid(row=curRow,column=1)##insert the funcgen window
        curRow+=1

        Label(text="Oscilloscope", relief=RIDGE,width=100,height=30).grid(row=0,column=0,rowspan=curRow)
        container.pack(side="top",fill="both",expand=True)
        
        Label(text="TABS", relief=RIDGE,width=100,height=10).grid(row=curRow,column=0)

        
root = Tk()                  #Create Application Window
#tabs = ttk.notebook(root,side=TOP)
app = Application(root)

root.mainloop()

