from com.ziclix.python.sql import zxJDBC


# Modify as needed for your database.
url='jdbc:hsqldb:hsql://localhost/xdb'
user='sa'
pw=''
driver='org.hsqldb.jdbcDriver'


db = zxJDBC.connect(url, user, pw, driver)

cursor = db.cursor()

cursor.execute("""
create table user 
    (userid integer, 
    username varchar, 
    firstname varchar, 
    lastname varchar, 
    phone varchar)
""")

cursor.execute("""create index userid on user (userid)""")

cursor.execute("""
insert into user (userid,username,firstname,lastname,phone) 
values (1,'ericfj','Eric','Foster-Johnson','555-5555')
""")

cursor.execute("""
insert into user (userid,username,firstname,lastname,phone) 
values (2,'tosh','Peter','Tosh','555-5554')
""")

cursor.execute("""
insert into user (userid,username,firstname,lastname,phone) 
values (3,'bob','Bob','Marley','555-5553')
""")

cursor.execute("""
insert into user (userid,username,firstname,lastname,phone) 
values (4,'scientist','Hopeton','Brown','555-5552')
""")

db.commit()


cursor.execute("select * from user")
for row in cursor.fetchall(): 
    print(row)
    

cursor.close()
db.close()
