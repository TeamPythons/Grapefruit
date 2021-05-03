import pyodbc
from getpass import getpass


server = 'tcp:grapefruit-mango-s1.database.windows.net'
database = 'Grapefruit'
username = 'GrapeAdmin'

print("Please enter the password:")
password = getpass()

cnxn = pyodbc.connect(
    'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
cursor = cnxn.cursor()

def create():
        date = input('Please enter date in YYYY-MM-DD format: ')
        cust_email = input('Please enter customer email: ')
        cust_location = input('Please enter customer location: ')
        product_id = input('Please enter product ID: ')
        product_quantity = input('Please enter product quantity: \n')
        cursor.execute("""
            INSERT INTO dbo.customer_orders_team2 (date, cust_email, cust_location, product_id, product_quantity)
            VALUES (?,?,?,?,?)""",
            date, cust_email, cust_location, product_id, product_quantity)
        cnxn.commit()

        data = cursor.execute("""
            SELECT *
            FROM dbo.customer_orders_team2
            WHERE date = ?
            AND cust_email = ?
            AND cust_location = ?
            AND product_id = ?
            AND product_quantity = ? """, date, cust_email, cust_location, product_id, product_quantity)

        data = cursor.fetchall()
        cnxn.commit()

        return print("fetching data added from database.... "), print(f"\n{data} added to the database")

if __name__ == '__main__':
    create()





