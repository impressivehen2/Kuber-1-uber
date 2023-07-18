# Uber
## Links
#### Micorservice with Kubernetes and Docker
https://piotrminkowski.wordpress.com/2017/03/31/microservices-with-kubernetes-and-docker/

#### Minikube
https://sensu.io/blog/minikube-tutorial

# Summary
Java 17, SpringBoot 3.1.1
order-service calls hotpot-service with Kubernetes Service endpoint using FeignClient
Deploy local Kubernetes cluster with Minikube, expose pods with Deployment, Service yml
!!! Still not able to call Ingress on MacOs

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

#### 3. Start Minikube to create a local Kubernetes cluster

```
minikube start

minikube status

minikube dashboard

minikube stop

minikube delete
```

For MacOS, to solve Kub Service not reachable
```
# https://devopscube.com/minikube-mac/
brew install qemu
brew install socket_vmnet
# home brew start socket_vmnet service
HOMEBREW=$(which brew) && sudo ${HOMEBREW} services start socket_vmnet
HOMEBREW=$(which brew) && sudo ${HOMEBREW} services stop socket_vmnet

# Start Minikube with Qemu driver and socket_vmnet
minikube start --driver qemu --network socket_vmnet
```

#### 2. Kubernetes 
```
# Apply Kub Deployment
kubectl apply -f hotpot/kubernetes/deployment.yml
kubectl apply -f order/kubernetes/deployment.yml

# Apply Kub Service
kubectl apply -f hotpot/kubernetes/service.yml
kubectl apply -f order/kubernetes/service.yml

# Get Service NodePort url
minikube service hotpot-service --url  # http://192.168.105.2:30033/hotpot/menu
minikube service order-service --url # http://192.168.105.2:30022/order/price/Spicy Beef

# For testing only forward service endpoint to localhost
kubectl port-forward service/hotpot-service 3333:3333 # http://localhost:3333/hotpot/menu
kubectl port-forward service/order-service 2222:2222 http://localhost:2222/order/price/Spicy Beef 

# Apply Kub Ingress
kubectl apply -f kubernetes/ingress.yml
```

## Debug
#### Kubernetes apply deployment.yml error
https://stackoverflow.com/questions/48582951/deployment-invalid-spec-template-metadata-labels-invalid-value
```
spec.selector: Required value
spec.template.metadata.labels: Invalid value: map[string]string{"app":"hotpot-service"}: `selector` does not match template `labels`
```

#### Unable to access NodePort MacOS Minikube -> Step 3
https://github.com/kubernetes/minikube/issues/11193

#### Unable to test Ingress MacOS
https://github.com/kubernetes/minikube/issues/13510

#### Kubernetes Debug
```
kubectl get pods
kubectl describe pod [pod]
kubectl logs [pod] --all-containers
kubectl rollout restart deployment hotpot-servic
kubectl get endpoints
kubectl get svc # get service
kubectl get ing # get ingress
```
