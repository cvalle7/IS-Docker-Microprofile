export DATABASE_DRIVER="oracle.jdbc.pool.OracleDataSource"
export DATABASE_TYPE="oracle"
export DATABASE_URL="thin:@localhost:1521:xe"
export DATABASE_USER="VAIE"
export DATABASE_PSW="bd123"

java -jar /Payara/micro/payara-micro-5.2020.4.jar  --port 88 --deploy ./target/BackendF1-1.0.war --contextroot /back   
