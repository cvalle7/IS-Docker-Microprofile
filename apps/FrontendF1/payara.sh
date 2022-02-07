export COM_MYCOMPANY_FRONTENDF1_MODELS_SERVICES_BACKENDSERVICE_MP_REST_URL="http://localhost:88/back"
java -jar /Payara/micro/payara-micro-5.2020.4.jar  --port 80 --deploy ./target/FrontendF1-1.0.war --contextroot /front
