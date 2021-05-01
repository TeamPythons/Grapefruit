import pyodbc
import matplotlib.pyplot as plt
from plotly import offline
from datetime import datetime,timedelta
import numpy as np
from getpass import getpass


def Cnxn():
    server = 'tcp:grapefruit-mango-s1.database.windows.net'
    database = 'Grapefruit'
    username = 'GrapeAdmin'

    print("Please enter the password:")
    password = getpass()

    cnxn = pyodbc.connect(
        'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
    cursor = cnxn.cursor()

    return cnxn
