# jee-thymeleaf-integration

Mise en oeuvre des api jEE8 CDI et Servlet pour configurer le moteur de template [Thymeleaf](https://www.thymeleaf.org) au sein d'une webapp.


## build

```
mvn clean package
```

## Déploiement avec wildfly-swarm

### Build

```
mvn clean package -Pswarm
```

### Exécution

```
java -jar target/servlet-thymeleaf-hollow-swarm.jar target/servlet-thymeleaf.war
```


## Déploiement avec payara

```
mvn clean package -Ppayara
```

## Exécution

```
java -jar target/payara-micro.jar target/servlet-thymeleaf.war
```

