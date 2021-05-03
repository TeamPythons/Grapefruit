import pyodbc
import matplotlib.pyplot as plt
from plotly import offline
from datetime import datetime,timedelta
import numpy as np
from getpass import getpass



def marketingData():
    # run sql to collect data then display using matplotlib

    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'


    print("Please enter the password:")
    password = getpass()


    cnxn = pyodbc.connect('DRIVER={ODBC Driver 17 for SQL Server};SERVER='+server+';DATABASE='+database+';UID='+username+';PWD='+ password)
    cursor = cnxn.cursor()
    print("Database Connection Established Succesfully...")
    #build time delta loop for 3 month time series
    startTime = input("what date do you want ot start 'YYYY-MM-DD'")

    parseTime =datetime.strptime(startTime,'%Y-%m-%d')
    i = 12
    dateList = []
    totalSales = []


    while(i>=0):


        parseTime = (parseTime - timedelta(days=7))
        deltaTime =parseTime.strftime('%Y-%m-%d')






        # create virtual table for specific range


        cursor.execute(f"""
            CREATE VIEW temp_table 
            AS
            (SELECT customer_orders_team2.product_id, 
            sale_price,
            product_quantity,
            cust_email,
            [date]
            FROM inventory_team2
            INNER JOIN customer_orders_team2 ON customer_orders_team2.product_id = inventory_team2.product_id
            WHERE [date] >= '{deltaTime}' AND
            [date]   <= '{startTime}' )
                  """)

        cnxn.commit()

        # Aggregate virtual table for finance opperations
        cursor.execute("""
        SELECT[sale_price], [product_quantity], product_id
        FROM
        dbo.temp_table
        GROUP
        BY
        sale_price, [product_quantity], product_id
            """)

        salestemp = 0
        datas = cursor.fetchall()
        for data in datas:
            salestemp = round(((data[0])*(data[1]) + salestemp),2)



        cnxn.commit()


        cursor.execute("""DROP VIEW [dbo].[temp_table]
        """)
        cnxn.commit()

        dateList.append(startTime)
        totalSales.append(salestemp)

        startTime = deltaTime
        i = i - 1

    # were gonna build the sales delta here, double pointers utilized
    changeSales = []
    changeSales.append(0)
    ii = 0
    j = ii + 1
    while j < len(totalSales):
        changeSales.append(round(totalSales[j] - totalSales[ii], 2))
        ii = ii + 1
        j = j + 1

    # data visulization

    dataViz = [{
        'type': 'bar',
        'x': dateList,
        'y': totalSales,
        'hovertext': changeSales,
        'marker': {
            'color': "rgb(60,100,150)",
            'line': {'width':1.5,'color': 'rgb(25,25,25)'}
        },
        'opacity':0.6,
    }]

    myLayout = {
        'title': "Grapefruit Finance Report",
        'titlefont':{'size':28},
        'xaxis': {'title':'Week beginning with',
                  'titlefont': {'size':24},
                  'tickfont': {'size':14},
                  },
        'yaxis': {'title': "Sales in $"},
    }

    fig = {'data':dataViz,'layout':myLayout}
    offline.plot(fig, filename=f"GrapeFruitMarketing_{startTime}.html")

    return print(totalSales),print(dateList),print(changeSales)

if __name__ == '__main__':
    marketingData()






