import yaml
import serial

arduino_port = None
arduino_rate = None

with open(r'cfg/arduino_cfg.yaml') as yaml_file:

	arduino = yaml.load(yaml_file, Loader=yaml.FullLoader).get("arduino")

	arduino_port = arduino.get("port")
	arduino_rate = arduino.get("rate")


ser = serial.Serial(arduino_port, arduino_rate)
ser.flushInput()

while True:
    try:
        ser_bytes = ser.readline()
        decoded_bytes = str(ser_bytes[0:len(ser_bytes)-1].decode("utf-8"))
        print(decoded_bytes)

        #TODO write to the database.
        
    except:
        print("Keyboard Interrupt")
        break