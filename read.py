import pyodbc
from getpass import getpass




def read():

    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'

    print("Please enter the password:")
    password = getpass()

    cnxn = pyodbc.connect(
        'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
    cursor = cnxn.cursor()

    global user_input
    print("Please Select how you would like to search the database"
          "\n [1] Product ID"
          "\n [2] Quantity"
          "\n [3] Wholesale Cost"
          "\n [4] SalePrice"
          "\n [5] Supplier ID"
          )

    initial_input = input("\nPlease enter the corresponding number to choose")

    if initial_input == '1':
        user_input = "product_id"
    elif initial_input == '2':
        user_input = "quantity"
    elif initial_input == "3":
        user_input = "wholesale_cost"
    elif initial_input == "4":
        user_input = "sale_price"
    elif initial_input == "5":
        user_input = "supplier_id"
    else:
        print("Invalid Input")

    condition = input(f"please enter the {user_input} you want to search for")

    print(condition)
    print(user_input)
    dataset = cursor.execute(f"""
        SELECT *
        FROM inventory_team2
        WHERE {condition} = '{user_input}' """)

    data = cursor.fetchall()
    print("product id-quantity-wholesale cost-sale price-supplier id")
    for x in data:
        print(x)

    cnxn.commit()
