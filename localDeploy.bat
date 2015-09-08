SET cq_user=admin
SET cq_password=admin
SET cq_port=4502
SET cq_host=localhost
mvn clean install -DskipTests=true -Pdeploy 
