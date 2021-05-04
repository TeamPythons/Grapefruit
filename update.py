import pyodbc
from getpass import getpass




def update():

    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'

    print("Please enter the password:")
    password = getpass()

    cnxn = pyodbc.connect(
        'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
    cursor = cnxn.cursor()

    global user_input
    print("Please Select what value you would like to update in the database"
          "\n [1] Quantity"
          "\n [2] Wholesale Cost"
          "\n [3] SalePrice"
          "\n [4] Supplier ID"
          )

    initial_input = input("\nPlease enter the corresponding number to choose")

    if initial_input == '1':
        user_input = "quantity"
    elif initial_input == "2":
        user_input = "wholesale_cost"
    elif initial_input == "3":
        user_input = "sale_price"
    elif initial_input == "4":
        user_input = "supplier_id"
    else:
        print("Invalid Input")

    condition = input(f"please enter the product ID you want to search for")

    newValue = input(f"Please enter the new {user_input} for product: {condition}")



    # update
    cursor.execute(f"""
        UPDATE inventory_team2
        SET {user_input} = {newValue}
        WHERE product_id = '{condition}' """)
    cnxn.commit()

    # print updated result
    cursor.execute(f"""
                SELECT *
                FROM inventory_team2
                WHERE product_id = '{condition}' """)

    newData = cursor.fetchall()
    print("Updated Data")
    for x in newData:
        print(x)

    cnxn.commit()
