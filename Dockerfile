FROM airhacks/glassfish
COPY ./target/ecommerce.war ${DEPLOYMENT_DIR}
