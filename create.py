import pyodbc
from getpass import getpass




def create():

    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'

    print("Please Enter The Password:")
    password = getpass()

    cnxn = pyodbc.connect(
        'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
    cursor = cnxn.cursor()


    prod_id = input('Please enter the date in YYYY-MM-DD format: ')
    quant = input('Please enter the customer email: ')
    wholesale = input('Please enter the customer location: ')
    price = input('Please enter the product ID: ')
    suplier = input('Please enter product quantity: \n')
    cursor.execute(f"""
        INSERT dbo.inventory_team2(product_id,quantity,wholesale_cost,sale_price,supplier_id)
        VALUES ('{prod_id}',{quant},{wholesale},{price},'{suplier}')""")
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
