[//]: # (Step 1)
Build Docker image:
docker build -t course-springboot .
[//]: # (Step 2)
Run Docker container:
docker run --name course-springboot --network my-network \
-e "DBMS_CONNECTION=jdbc:mysql://mysql:3306/identity_service?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" \
-p 8081:8081 -d course-springboot
[//]: # (Step 3)
Create a network:
docker network create my-network
[//]: # (Step 4)
Connect MySQL container to the network:
docker network connect my-network mysql
[//]: # (Step 5)
Connect course-springboot container to the network:
docker network connect my-network course-springboot
[//]: # (Step 6)
Check the logs:
docker logs course-springboot
[//]: # (Step 7)

