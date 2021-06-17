# AstronomicalDatabaseApplication
IDEA Javafx project with a database that allows you to add, find, and edit information about astronomical objects

# How to launch
1. Install MySQL and InteliJ IDEA (Java 1.8)

2. Import `mysql-connector-java.jar` in modulepath in IDEA (Download - https://dev.mysql.com/downloads/connector/j/5.1.html, choose Platform Independent)

3. Import the database and its contents via files `AstronomicalDatabase.sql` and `insertObjects.sql`
4. Change the host, port, user and pass to your own in `Configs.java`

# How to use

After you have run the application you will see a window:

![Image alt](https://github.com/r0mbeg/AstronomicalDatabaseApplication/blob/main/images/pic1.png)

Here you can choose an action - add a new object or find and edit an existing one

If you chose to add a new object, you will see the following window:


![Image alt](https://github.com/r0mbeg/AstronomicalDatabaseApplication/blob/main/images/pic2.png)

Next, in the drop-down list, select the type of object to add

![Image alt](https://github.com/r0mbeg/AstronomicalDatabaseApplication/blob/main/images/pic3.png)

Let's say you chose to add a new galaxy

![Image alt](https://github.com/r0mbeg/AstronomicalDatabaseApplication/blob/main/images/pic4.png)

Here you need to enter the parameters of the object to be added - name (required), type (for example, spiral), declination, right ascension, and distance to the object.

If you enter the name of an existing galaxy and try to add it, you will see an error message:

![Image alt](https://github.com/r0mbeg/AstronomicalDatabaseApplication/blob/main/images/pic5.png)

