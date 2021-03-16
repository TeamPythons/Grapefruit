import pyodbc
import unittest
from getpass import getpass



def create():
    date = input("Please enter the date in mm/dd/yyyy format: ")
    cust_email = input('Please enter customer email: ')
    cust_location = input('Please enter customer location: ')
    product_id = input('Please enter product ID: ')
    product_id = input('Please enter product quantity: ')
    cursor.execute("""
    INSERT INTO dbo.customer_orders_team2 (date, cust_email, cust_location, product_id, product_quantity)
    VALUES (?,?,?,?,?)""",
    date, cust_email, cust_location, product_id, product_id)
    cnxn.commit()

def read():
    cust_email = input("Please enter the data you would like to read: ")
    cursor.execute("""
    SELECT * FROM  dbo.customer_orders_team2 WHERE cust_email = ? """, cust_email)
    for row in cursor:
        print('row = %r' % (row,))
    cnxn.commit()

#def read_input():

def update(date, cust_email, cust_location, product_id, product_quantity):
    cursor.execute("""
    UPDATE  dbo.customer_orders_team2 SET ? WHERE
    VALUES (?,?,?,?,?)""",
    '3/8/2021', 'Test_Email@Gmail.com', 00000, 'TESTING' , 21)
    cnxn.commit()


#def update_input():

def delete():
    field = input("Please enter the record you would like to delete: ")
    cursor.execute("""
    DELETE FROM dbo.customer_orders_team2 WHERE cust_email = ?""", field)
    cnxn.commit()
    print("Record deleted succesfully.")

#def delete_input():




if __name__ == "__main__":
    print(r"""\

        __________  ___    ____  ________________  __  ____________   ____  ___  _________    ____  ___   _____ ______
      / ____/ __ \/   |  / __ \/ ____/ ____/ __ \/ / / /  _/_  __/  / __ \/   |/_  __/   |  / __ )/   | / ___// ____/
    / / __/ /_/ / /| | / /_/ / __/ / /_  / /_/ / / / // /  / /    / / / / /| | / / / /| | / __  / /| | \__ \/ __/
    / /_/ / _, _/ ___ |/ ____/ /___/ __/ / _, _/ /_/ // /  / /    / /_/ / ___ |/ / / ___ |/ /_/ / ___ |___/ / /___
    \____/_/ |_/_/  |_/_/   /_____/_/   /_/ |_|\____/___/ /_/    /_____/_/  |_/_/ /_/  |_/_____/_/  |_/____/_____/


    """)
    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'


    print("Please enter the password:")
    password = getpass()

    cnxn = pyodbc.connect('DRIVER={ODBC Driver 17 for SQL Server};SERVER='+server+';DATABASE='+database+';UID='+username+';PWD='+ password)
    cursor = cnxn.cursor()
    print("Database Connection Established Succesfully...")
    print("======================================================")
    print("MENU" )
    print("[1] Create")
    print("[2] Read")
    print("[3] Update" )
    print("[4] Delete")
    print("[!] Exit")
    choice = input("Please select an option: ")
    alive = 1
    while alive == 1:
        if choice == "1":
            create()
            choice = "0"
        elif choice == "2":
            read()
            choice = "0"
        elif choice =="3":
            update()
            choice = "0"
        elif choice == "4":
            delete()
            choice = "0"
        elif choice is "!":
            print("Goodbye")
            alive = 0
        elif choice == "0":
            choice = input("Please select a new option C[1] R[2] U[3] D[4] Exit [!] : " )
        else:
            print("Please enter a different value.")
