import sys
import time
from networktables import NetworkTables

from pynput import keyboard


# To see messages from networktables, you must setup logging
import logging



logging.basicConfig(level=logging.DEBUG)

if len(sys.argv) != 2:
    print("Error: specify an IP to connect to!")
    exit(0)

ip = sys.argv[1]

def on_press(key):
    pass


NetworkTables.initialize(server=ip)
inst = NetworkTables.getDefault()
table = inst.getTable("LEDs")

cubeConeLocal = False

def toggleCubecone(cubecone):
    inst = NetworkTables.getDefault()

    table = inst.getTable("LEDs")

    boolEntry = table.getEntry("cubeCone")

    boolEntry.setBoolean(cubecone)

def on_release(key):
    print('{0} released'.format(
        key))
    if key == keyboard.Key.f19:
        cubeConeLocal = not cubeConeLocal
        toggleCubecone(cubeConeLocal)
    if key == keyboard.Key.esc:
        # Stop listener
        return False


# ...or, in a non-blocking fashion:
listener = keyboard.Listener(
    on_press=on_press,
    on_release=on_release)
listener.start()


