
  echo 'Limpando e construindo o projeto do customer'
  ./gradlew clean build -x test

  echo 'Gerando a imagem do customer-ms...'
  docker build -t peronnydev/customer-ms:latest .