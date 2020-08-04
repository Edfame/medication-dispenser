import yaml
import serial
import requests
from datetime import datetime


# Load the configs from the file provided.
def load_configs():

    with open(r'cfg/configs.yaml') as yaml_file:
        configs = yaml.load(yaml_file, Loader=yaml.FullLoader).get("arduino")

    return configs


# Connect to the serial port provided.
def connect_to_serial(port, rate):

    ser = serial.Serial(port, rate)
    ser.flushInput()

    return ser


def write_new_data(message, api):

    request = requests.post(api+"/new_take", data=message)
    print(request.json())


# Start the arduino listener.
def run(ser, api):
    while True:

        instant = datetime.now().strftime("%d/%m/%Y-%H:%M:%S")

        try:
            ser_bytes = ser.readline()
            decoded_bytes = str(ser_bytes[0:len(ser_bytes) - 1].decode("utf-8"))
            print(instant + " > " + decoded_bytes)

            write_new_data(instant, api)

        except:
            print("Keyboard Interrupt")
            break


if __name__ == "__main__":

    # puns intended
    cfgs = load_configs()
    cereal = connect_to_serial(cfgs.get("port"), cfgs.get("rate"))
    run(cereal, cfgs.get("api"))
