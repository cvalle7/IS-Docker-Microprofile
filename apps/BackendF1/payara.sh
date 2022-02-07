export DATABASE_DRIVER="oracle.jdbc.pool.OracleDataSource"
export DATABASE_TYPE="oracle"
export DATABASE_URL="thin:@localhost:1521:xe"
export DATABASE_USER="yourUser"
export DATABASE_PSW="yourPsswrd"

java -jar /Payara/micro/payara-micro-5.2020.4.jar  --port 88 --deploy ./target/BackendF1-1.0.war --contextroot /back   
