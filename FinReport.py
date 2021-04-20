import pyodbc
import matplotlib.pyplot as plt
from plotly import offline
from datetime import datetime,timedelta
import numpy as np
from getpass import getpass

# finacial reporting that will give top products and customers

def finReport():
    # sql connection
    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'

    print("Please enter the password:")
    password = getpass()

    cnxn = pyodbc.connect(
        'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
    cursor = cnxn.cursor()
    print("Database Connection Established Succesfully...")

    # Gather info from user to build correct sql query
    finChoice = input("Would you like to see\n[1] Best Customers\n[2] Best Products? ")
    #if finChoice != '1' or '1':
       # print("invalid entry")

    # Create formatted date for sql query based on user input
    timeChoice = input("Would you like daily, weekly or monthly report\n"
                       "[1] Daily\n"
                       "[2] Weekly\n"
                       "[3] Monthly")
    if timeChoice != '1' or '2' or '3':
        print('invalid entry')
    userDateChoice = input("please enter the date to generate report in YYYY-MM-DD Format")
    numResults = input("How many results would you like to see")
    numResults = str(numResults)
    parseTime = datetime.strptime(userDateChoice, '%Y-%m-%d')

    if timeChoice == '1':
        parseTime = (parseTime - timedelta(days=0))
        deltaTime =parseTime.strftime('%Y-%m-%d')
    elif timeChoice == '2':
        parseTime = (parseTime - timedelta(days=7))
        deltaTime = parseTime.strftime('%Y-%m-%d')
    elif timeChoice == '3':
        parseTime = (parseTime - timedelta(weeks=4))
        deltaTime = parseTime.strftime('%Y-%m-%d')
    prodID= []
    prodCount = []
    salePrice = []
    totalOrder = []
    totalDollar = []
    custEmail = []
    print(deltaTime)
    print(userDateChoice)
    if finChoice == '1':
        # Build sql query for top customers
        cursor.execute(f"""SELECT TOP({numResults})
            sub1.cust_email,
            sub1.total_orders,
            sub2.total_dollar

            FROM
            (
                SElECT
                    customer_orders_team2.cust_email,
                    COUNT(DISTINCT customer_orders_team2.product_quantity) 
                    AS total_orders
                FROM customer_orders_team2
                WHERE [date] >= '{deltaTime}' AND
                        [date]   <= '{userDateChoice}' 
                GROUP BY customer_orders_team2.cust_email
            ) AS sub1
            INNER JOIN
            (
                SELECT 
                    customer_orders_team2.cust_email,
                    SUM(customer_orders_team2.product_quantity*inventory_team2.sale_price)
                        AS total_dollar
                FROM inventory_team2 INNER JOIN customer_orders_team2
                ON customer_orders_team2.product_id = inventory_team2.product_id
                GROUP BY customer_orders_team2.cust_email
            ) AS sub2
            ON sub1.cust_email = sub2.cust_email
                ORDER by sub2.total_dollar DESC       
                              """)
        datas = cursor.fetchall()
        for data in datas:
            custEmail.append(data[0])
            totalOrder.append(data[1])
            totalDollar.append(data[2])
            cnxn.commit()

        dataViz = [{
            'type': 'bar',
            'x': custEmail,
            'y': totalDollar,
            'hovertext': totalOrder,
            'marker': {
                'color': "rgb(60,100,150)",
                'line': {'width': 1.5, 'color': 'rgb(25,25,25)'}
            },
            'opacity': 0.6,
        }]

        myLayout = {
            'title': "Grapefruit Marketing Report: Top Customers",
            'titlefont': {'size': 28},
            'xaxis': {'title': 'Customer Email (Hover for Total Orders)',
                      'titlefont': {'size': 24},
                      'tickfont': {'size': 14},
                      },
            'yaxis': {'title': "Sales in $"},
        }

        fig = {'data': dataViz, 'layout': myLayout}
        offline.plot(fig, filename=f"GrapeFruitFinance_topCusties_{timeChoice}.html")

    elif finChoice == '2':
        cursor.execute(f"""
        SELECT TOP({numResults}) customer_orders_team2.product_id,sale_price,
        SUM([product_quantity]) AS product_count
        FROM (inventory_team2
        INNER JOIN customer_orders_team2 ON customer_orders_team2.product_id = inventory_team2.product_id
        )
        WHERE [date] >= '{deltaTime}' AND
        [date]   <= '{userDateChoice}' 
        GROUP BY customer_orders_team2.product_id,sale_price
        ORDER BY product_count DESC
            """)
        datas = cursor.fetchall()
        for data in datas:
            prodID.append(data[0])
            salePrice.append(data[1])
            prodCount.append(data[2])
            cnxn.commit()

        dataViz = [{
            'type': 'bar',
            'x': prodID,
            'y': prodCount,
            'hovertext': salePrice,
            'marker': {
                'color': "rgb(60,100,150)",
                'line': {'width': 1.5, 'color': 'rgb(25,25,25)'}
            },
            'opacity': 0.6,
        }]

        myLayout = {
            'title': "Grapefruit Finance Report",
            'titlefont': {'size': 28},
            'xaxis': {'title': 'Procuct ID',
                      'titlefont': {'size': 24},
                      'tickfont': {'size': 14},
                      },
            'yaxis': {'title': "Total Products Sold"},
        }

        fig = {'data': dataViz, 'layout': myLayout}
        offline.plot(fig, filename=f"GrapeFruitMarketing_topProducts_{timeChoice}.html")
    return print( prodID),print(salePrice),print(totalOrder),print(totalDollar),print(custEmail)


if __name__ == '__main__':
    finReport()
