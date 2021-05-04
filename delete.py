import pyodbc
from getpass import getpass


server = 'tcp:grapefruit-mango-s1.database.windows.net'
database = 'Grapefruit'
username = 'GrapeAdmin'

print("Please Enter The Password:")
password = getpass()

cnxn = pyodbc.connect(
    'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
cursor = cnxn.cursor()

def delete():
    user_input = input("Please enter the Product ID you would like to delete ")
    cursor.execute(f"""
    DELETE FROM dbo.customer_orders_team2 WHERE product_id = '{user_input}'
    """)
    cnxn.commit()

    cursor.execute(f"""
            SELECT *
            FROM inventory_team2
            WHERE product_id = '{user_input}' """)
    verify = cursor.fetchall()
    cnxn.commit()
    if len(verify) == 0:
        print("Data successfully deleted")





