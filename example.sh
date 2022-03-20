curl 'http://localhost:8080/clear'

curl 'http://localhost:8080/new-user?name=Dima&currency=UAH'

curl 'http://localhost:8080/new-user?name=Vlad&currency=RUB'

curl 'http://localhost:8080/new-user?name=Anches&currency=EUR'

curl 'http://localhost:8080/new-user?name=Roma&currency=USD'

curl 'http://localhost:8080/new-item?name=Nike&currency=USD&cost=130'
curl 'http://localhost:8080/new-item?name=Taxi&currency=RUB&cost=150'
curl 'http://localhost:8080/new-item?name=Kia&currency=EUR&cost=20000'

curl 'http://localhost:8080/get-items?user=Anches'

curl 'http://localhost:8080/get-items?user=Vlad'

curl 'http://localhost:8080/get-items?user=Roma'

curl 'http://localhost:8080/get-items?user=Dima'