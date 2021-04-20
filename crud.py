import pyodbc
from getpass import getpass
from crud import *


server = 'tcp:grapefruit-mango-s1.database.windows.net'
database = 'Grapefruit'
username = 'GrapeAdmin'

print("Please enter the password:")
password = getpass()

cnxn = pyodbc.connect('DRIVER={ODBC Driver 17 for SQL Server};SERVER='+server+';DATABASE='+database+';UID='+username+';PWD='+ password)
cursor = cnxn.cursor()

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

def update(date, cust_email, cust_location, product_id, product_quantity):
    cursor.execute("""
    UPDATE  dbo.customer_orders_team2 SET ? WHERE
    VALUES (?,?,?,?,?)""",
    '3/8/2021', 'Test_Email@Gmail.com', 00000, 'TESTING' , 21)
    cnxn.commit()

def read():
    cust_email = input("Please enter the data you would like to read: ")
    cursor.execute("""
    SELECT * FROM  dbo.customer_orders_team2 WHERE cust_email = ? """, cust_email)
    for row in cursor:
        print('row = %r' % (row,))
    cnxn.commit()

def delete():
    field = input("Please enter the record you would like to delete: ")
    cursor.execute("""
    DELETE FROM dbo.customer_orders_team2 WHERE cust_email = ?""", field)
    cnxn.commit()
    print("Record deleted succesfully.")
