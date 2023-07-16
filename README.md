## Links
#### Micorservice with Kubernetes and Docker
https://piotrminkowski.wordpress.com/2017/03/31/microservices-with-kubernetes-and-docker/

#### Minikube
https://sensu.io/blog/minikube-tutorial

## Steps
#### 1. Build jar
```
mvn clean package
```

#### 2. Build, push Docker image
```
# build Docker image, -t tag name, . specify Dockerfile path
docker build -t shhu/hotpot-service hotpot/.
docker push shhu/hotpot-service

docker build -t shhu/order-service order/.
docker push shhu/order-service
```

#### 1. Start minikube to create a local Kubernetes cluster

```
minikube start

minikube status

minikube dashboard

minikube stop
```

#### 2. Kubernetes 
```
# Apply Kub Deployment
kubectl apply -f hotpot/kubernetes/deployment.yml
kubectl apply -f order/kubernetes/deployment.yml

# Debug
kubectl get pods
kubectl describe pod [pod]
kubectl logs [pod] --all-containers
kubectl rollout restart deployment hotpot-service


```

## Debug
#### 1. https://stackoverflow.com/questions/48582951/deployment-invalid-spec-template-metadata-labels-invalid-value
```
spec.selector: Required value
spec.template.metadata.labels: Invalid value: map[string]string{"app":"hotpot-service"}: `selector` does not match template `labels`
```

#### 2. Start Kub cluster 
```
The connection to the server localhost:8080 was refused - did you specify the right host or port?
```

spring boot 
2.7.13 -> 3.1.1
java 11 -> 17
feign 3.1.4 -> 4.0.3