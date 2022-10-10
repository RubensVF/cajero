import datetime
import re
from unicodedata import name

saldo = 1000.00 #Saldo inicial
pin = 12345 #Pin de acceso
intentos = 3 # Intentos para ingresar el pin
historial = [] #Lista para guardar el historial de movimientos
nombre = "Ruben" #Nombre del usuario

class Movimiento:
    def __init__(self,monto,saldo_inicial):
        self.time = datetime.datetime.now()
        self.saldo_inicial = saldo_inicial
        self.monto = monto 

    def __str__(self):
        return f"Fehcha y hora: {self.time}\nSaldo inicial: {self.saldo_inicial}\nMonto: {self.monto}"  

print("\t.:Cajero Automatico:.")
pin_in = int(input("Digite su pin: "))
while pin_in != pin and intentos > 0:
    print("Pin incorrecto")
    intentos -= 1
    if intentos == 0:
        print("Ha excedido el numero de intentos")
        print()
        exit()
    pin_in = int(input("Digite su pin: "))

print()
print(f".:Bienvenido {nombre}:.")

while True:
    print()
    print("\t.:MENU:.")
    print("1. Consultar saldo")
    print("2. Retirar saldo")
    print("3. Historico de movimientos")
    print("4. Salir")
    print()
    opcion = int(input("Digite una opcion de menu: "))
    if opcion==1:
        print()
        print(f"Dinero en la cuenta: {saldo}")
    elif opcion==2:
        retirar = input("Cuánto dinero desea retirar -> ")
        #Validar que es una cantidad valida
        val = re.match(r'[0-9]*(\.[0-9]{2})?', str(retirar))
        if val is None:
            print("CANNTIDAD INVALIDA")
            continue
        retirar = float(retirar)
        if retirar>saldo:
            print()
            print("No tiene esa cantidad de dinero")
        else:
            saldo -= retirar
            movimiento= Movimiento(retirar,saldo+retirar)
            historial.append(movimiento)
    elif opcion==3:
        print()
        print("\t.:Historial:.")
        for(move) in historial:
            print(move)
        print()
    else:
        print("OPCION INVALIDA")

    while opcion in [1,2.3]:
        print()
        print("\t.:MENU:.")
        print("1. Volver al menu principal")	
        print("2. Salir")
        print()
        opcion = int(input("Digite una opcion de menu: "))

        if opcion==1:
            break 
        elif opcion==2:
            exit()
        else:
            print()
            print("OPCION INCORRECTA")

# Se añade comentario para hacer merge