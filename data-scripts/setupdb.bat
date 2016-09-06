rem Set environment

rem execute the sql script to create JDO tables
"C:\Program Files\MySQL\MySQL Server 5.6\bin\mysql" --user=root --password=password< setup.sql


"C:\Program Files\MySQL\MySQL Server 5.6\bin\mysql" --user=phoenix --password=password< createphoenix.sql

pause