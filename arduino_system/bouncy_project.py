import yaml
import serial
import requests
from datetime import datetime

# Constants
closed_hatch = "CLOSED HATCH"


class Controller:

    def __init__(self, cereal, configs, identifications):
        self.cereal = cereal
        self.cfgs = configs
        self.api = configs.get("api")
        self.user = identifications.get("user")
        self.drugs = identifications.get("drugs")


# Load the configs from the file provided.
def load_configs():
    with open(r'cfg/configs.yaml') as yaml_file:
        configs = yaml.load(yaml_file, Loader=yaml.FullLoader).get("arduino")

    return configs


# Load user and drugs.
def load_identifications():
    with open(r'identifications.yaml') as yaml_file:
        identifications = yaml.load(yaml_file, Loader=yaml.FullLoader)

    return identifications


# Connect to the serial port provided.
def connect_to_serial(cfgs):
    ser = serial.Serial(cfgs.get("port"), cfgs.get("rate"))
    ser.flushInput()

    return ser


# Posts the new administration data to the API.
def write_new_data(timestamp, controller):
    obj = {
        "drugId": controller.drugs[0],
        "userId": controller.user.get("id"),
        "administrationTimestamp": timestamp
    }

    request = requests.post(controller.api, json=obj)
    print(request.json())


# Start the arduino listener.
def run(controller):
    while True:

        timestamp = datetime.now().strftime("%Y-%m-%dT%H:%M:%S")

        try:
            ser_bytes = controller.cereal.readline()
            decoded_bytes = str(ser_bytes[0:len(ser_bytes) - 1].decode("utf-8"))
            decoded_bytes = decoded_bytes.replace('\r', '')
            print(timestamp + " > " + decoded_bytes)

            if decoded_bytes == closed_hatch:
                write_new_data(timestamp, controller)

        except Exception as e:
            print(e)


if __name__ == "__main__":
    # puns intended
    cfgs = load_configs()
    identifications = load_identifications()
    cereal = connect_to_serial(cfgs)
    ctrl = Controller(cereal, cfgs, identifications)

    run(ctrl)
