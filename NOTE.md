```bash
sudo mysql -u root
```
```sql
mysql> USE mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> SELECT User, Host, plugin FROM mysql.user;
+------------------+-----------+-----------------------+
| User             | Host      | plugin                |
+------------------+-----------+-----------------------+
| SCOTT            | localhost | caching_sha2_password |
| debian-sys-maint | localhost | caching_sha2_password |
| mysql.infoschema | localhost | caching_sha2_password |
| mysql.session    | localhost | caching_sha2_password |
| mysql.sys        | localhost | caching_sha2_password |
| root             | localhost | auth_socket           |
+------------------+-----------+-----------------------+
6 rows in set (0.00 sec)


ALTER USER 'SCOTT'@'localhost' IDENTIFIED BY '@***123***@'
SELECT VALIDATE_PASSWORD_STRENGTH ('@***123***@');

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| emp                |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
mysql> use emp;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> use emp;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

mysql> select * from employee;
+------+--------+-------+
| id   | fname  | lname |
+------+--------+-------+
|  101 | Soumen | Das   |
+------+--------+-------+
1 row in set (0.00 sec)
Database changed
select * from employee;

mysql> describe employee;
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| id    | int         | YES  |     | NULL    |       |
| fname | varchar(32) | YES  |     | NULL    |       |
| lname | varchar(32) | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+
3 rows in set (0.03 sec)


INSERT INTO employee (id, fname, lname) VALUES (102, 'John', 'Doe');
INSERT INTO employee (id, fname, lname) VALUES (103, 'Jane', 'Smith');
INSERT INTO employee (id, fname, lname) VALUES (104, 'Alice', 'Johnson');
INSERT INTO employee (id, fname, lname) VALUES (106, 'Bob', 'Brown');
INSERT INTO employee (id, fname, lname) VALUES (107, 'Charlie', 'Davis');
```
IDE:

<img src="mysql-wb.png"><br>