// connect motor controller pins to Arduino digital pins
#include <SoftwareSerial.h>   // librairie pour creer une nouvelle connexion serie max 9600 baud

SoftwareSerial BTSerial(10, 11); // RX | TX  = > BT-TX=10 BT-RX=11
// motor one
int in1 = 8;
int in2 = 9;
// motor two
int in3 = 7;
int in4 = 6;
void setup()
{
  // set all the motor control pins to outputs
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
  pinMode(13, OUTPUT);
  digitalWrite(13, HIGH);
  
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
  Serial.begin(9600);
  Serial.println("Enter a command:");
  BTSerial.begin(9600);  // HC-05 9600 baud
  delay(5000);
}

void forward(){
  digitalWrite(in1, HIGH);
  digitalWrite(in3, HIGH);

  delay(2000);
  
  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);
}

void backward(){
  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  delay(2000);

  digitalWrite(in2, LOW);
  digitalWrite(in4, LOW);
}

void turnRight(){
  digitalWrite(in2, HIGH);
  digitalWrite(in3, HIGH);

  delay(900);

  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
}

void turnDemiRight(){
  digitalWrite(in2, HIGH);
  digitalWrite(in3, HIGH);

  delay(450);

  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
}

void turnLeft(){
  digitalWrite(in1, HIGH);
  digitalWrite(in4, HIGH);

  delay(900);

  digitalWrite(in1, LOW);
  digitalWrite(in4, LOW);
}

void turnDemiLeft(){
  digitalWrite(in1, HIGH);
  digitalWrite(in4, HIGH);

  delay(450);

  digitalWrite(in1, LOW);
  digitalWrite(in4, LOW);
}



void loop()
{
 String message;
  // Boucle de lecture sur le BT
  // Reading BT
  while (BTSerial.available()) {
    // Lecture du message envoyé par le BT
    // Read message send by BT
    message = BTSerial.readString();
    // Ecriture du message dans le serial usb
    // write in serial usb
    Serial.println(message);
  }
  // Boucle de lecture sur le serial usb
  // Reading serial usb
  while (Serial.available()) {
    // Lecture du message envoyé par le serial usb
    // Read message send by serial usb
    message = Serial.read();
    // Ecriture du message dans le BT
    // write in BT
    BTSerial.println(message);
  }

  // si mon message est egal a "on"  ( + retour chariot et nouvelle ligne )
  // if message equal to "on" (+ carriage return and newline )
  if (message == "f") {
    forward(); // led on
  }// else if message off
  else if (message == "b") {
    backward();
  }
   else if (message == "l") {
    turnLeft();
  }
   else if (message == "r") {
    turnRight();
  }
  

}
