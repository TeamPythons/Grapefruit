import os
from welcome import welcome
import create
import read
import update
import delete
from marketing import marketingData
from FinReport import finReport

def main():
    while True:
        os.system("clear")
        print(welcome("Grapefruit Database"))
        print("\n Please Select An Option: ")
        print("""
        1 : Create
        2 : Read
        3 : Update
        4 : Delete
        5 : Marketing Report
        6 : Financial Report
        0 : Exit"""
              )
        choice = input("\nEnter your choice : ")

        if choice == '1':
            create()
        elif choice == '2' :
            read()
        elif choice == '3' :
            update()
        elif choice == '4' :
            delete()
        elif choice == '5' :
            marketingData()
        elif choice == '6' :
            finReport()
        elif choice == '0':
            exit()
        os.system("cls")

if __name__ == "__main__":
    main()
