<<<<<<< HEAD
# ecommerce-javaee
=======
# Build
mvn clean package && docker build -t com.ecommerce/ecommerce .

# RUN

docker rm -f ecommerce || true && docker run -d -p 8080:8080 -p 4848:4848 --name ecommerce com.ecommerce/ecommerce 
>>>>>>> d10b41e (feat: 404 page)
