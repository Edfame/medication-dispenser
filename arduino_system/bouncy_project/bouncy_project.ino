#include <Servo.h>

//PINS
#define SERVO_PIN 9
#define BUTTON_PIN 7
#define LED_PIN 13

//Consts
#define CLOSED_ANGLE 0
#define OPENED_ANGLE 90
#define OPENED_DELAY 1000
#define LOOP_DELAY 10

int button_status = -1;
int empty_status = -1;

Servo servo;

void setup() {

  //attach the servo to it's pin
  servo.attach(SERVO_PIN);

  //set the button to an input pin
  pinMode(BUTTON_PIN, INPUT);
  digitalWrite(BUTTON_PIN, HIGH);

  //set the led pin 
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);

  servo.write(CLOSED_ANGLE);
  delay(OPENED_DELAY);
  servo.detach();

  //begin the serial
  Serial.begin(9600);

}

void loop() {

  //getting reads from the pins
  button_status = digitalRead(BUTTON_PIN);

  //checks if the button was pressed
  if(button_status == LOW) {

    servo.attach(SERVO_PIN); 

    //if the button was pressed opens the hatch
    servo.write(OPENED_ANGLE);
    Serial.print("OPENED HATCH\n");

    //waits a bit before closing again
    delay(OPENED_DELAY);

    //closes the hatch
    servo.write(CLOSED_ANGLE);
    Serial.print("CLOSED HATCH\n");

    //waits a bit before closing again
    delay(OPENED_DELAY);
    
    servo.detach();

  }

  delay(LOOP_DELAY);
}
