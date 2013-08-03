Simple websockets app template for future projects.
Will execute a cronjob every 5 seconds
which reads a value from (Mysql) database,adds random values
and broadcast to all clients. JNDI Datasource being looked up
is 'jdbc/rtnotifier'.

For using with glassfish 4+ (becuase of it's native websocket support).

