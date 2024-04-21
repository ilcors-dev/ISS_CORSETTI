# File: sonar.py
import time
import sys
import random
#print ('Waiting a few seconds for the sensor to settle')
time.sleep(2)

while True:
   print ( random.randint(0, 300) )
   sys.stdout.flush()
   time.sleep(0.25)


#GPIO.cleanup()
