#python3
import pymysql

#Create DB tutoring
def createDB(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd)
    myCursor = connection.cursor()
    myCursor.execute("CREATE DATABASE IF NOT EXISTS bank_system;")
    connection.commit()
    print("Data base bank_system created")
    connection.close()

def createTableUser(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd, db="bank_system")
    myCursor = connection.cursor()
    myCursor.execute("CREATE TABLE IF NOT EXISTS User (idNum INT PRIMARY KEY, password VARCHAR(32) NOT NULL, firstName VARCHAR(32) NOT NULL, lastName VARCHAR(32) NOT NULL, email VARCHAR(32) NOT NULL, phoneNumber INT);")
    connection.commit()
    print("Table User created")
    connection.close()

def createTableAccount(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd, db="bank_system")
    myCursor = connection.cursor()
    myCursor.execute("CREATE TABLE IF NOT EXISTS Account (accNum INT PRIMARY KEY, accOwnerID INT UNIQUE NOT NULL, accBalance INT NOT NULL, accManager INT NOT NULL);")
    connection.commit()
    print("Table Account created")
    connection.close()

def createTableManager(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd, db="bank_system")
    myCursor = connection.cursor()
    myCursor.execute("CREATE TABLE IF NOT EXISTS Manager (idManager INT PRIMARY KEY, firstName VARCHAR(32) NOT NULL, lastName VARCHAR(32) NOT NULL, email VARCHAR(32) NOT NULL, phoneNumber INT NOT NULL, level INT NOT NULL);")
    connection.commit()
    print("Table Manager created")
    connection.close()

def createTableOwns(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd, db="bank_system")
    myCursor = connection.cursor()
    myCursor.execute("CREATE TABLE IF NOT EXISTS Owns (idNum INT, accNum INT, FOREIGN KEY (idNum) REFERENCES User(idNum), FOREIGN KEY (accNum) REFERENCES Account(accNum));")
    connection.commit()
    print("Table Owns created")
    connection.close()

def createTableManages(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd, db="bank_system")
    myCursor = connection.cursor()
    myCursor.execute("CREATE TABLE IF NOT EXISTS Manages (idManager INT , accNum INT, FOREIGN KEY (idManager) REFERENCES Manager(idManager), FOREIGN KEY (accNum) REFERENCES Account(accNum));")
    connection.commit()
    print("Table Manages created")
    connection.close()

def dropTables(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd, db="bank_system")
    myCursor = connection.cursor()
    myCursor.execute("DROP TABLE Owns;")
    myCursor.execute("DROP TABLE Manages;")
    myCursor.execute("DROP TABLE User;")
    myCursor.execute("DROP TABLE Manager;")
    myCursor.execute("DROP TABLE Account;")
    connection.commit()
    print("Tables deleted")
    connection.close()

def deleteDB(pswd):
    connection = pymysql.connect(host = 'localhost',user = 'root', port = 3306, password = pswd)
    myCursor = connection.cursor()
    myCursor.execute("DROP DATABASE bank_system;")
    connection.commit()
    print("Database bank_system deleted")
    connection.close()

def displayMenu(pswd):
    print("Menu:")
    print("1- Create all tables")
    print("2- Delete all tables")
    print("3- Create database")
    print("4- Delete database")
    print("0- Exit")

def main():
    pswd = input("What is the password:")

    displayMenu(pswd)

    option = input("Choose one option: ")

    while(not option == "0"):
        if(option == "1"):
            createTableUser(pswd)
            createTableAccount(pswd)
            createTableManager(pswd)
            createTableOwns(pswd)
            createTableManages(pswd)

        elif(option == "2"):
            dropTables(pswd)

        elif(option == "3"):
            createDB(pswd)

        elif(option == "4"):
            deleteDB(pswd)

        elif(option == "0"):
            break

        else:
            print("Wrong command, try again later.")
        displayMenu(pswd)
        option = input("Choose one option: ")

if __name__ == "__main__":
    main()
