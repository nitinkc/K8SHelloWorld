# K8S HelloWorld
- Build the project to get the .jar file
- Create a docker image and push it to dockerhub
- Deploy the image on GKE

Testing from localhost [http://localhost:8080/](http://localhost:8080/)

# For Docker hub manual push
1. Create Repository on https://hub.docker.com/<repo-name>
2. Log into the Docker Hub from the command lin
   ```shell
    docker login --username=nitinkc --email=gs.nitin@gmail.com`
   ```
2. Check the image ID (on local machine) using `docker images`
3. Tag the image `docker tag bb38976d03cf dockerhub-user-name/<repo-name>e:tag-name`
4. Push the image to the repository created using `docker push nitinkc/<repo-name>`

##### Example
```shell
docker login --username=nitinkc
docker images
docker tag 33a7bad180b7 nitinkc/k8s-helloworld:k8s-helloworld-latest
docker push nitinkc/k8s-helloworld
docker pull nitinkc/k8s-helloworld:k8s-helloworld-latest
docker run --publish 5005:5005 -t nitinkc/k8s-helloworld:k8s-helloworld-latest
```

# DockerHub Automated Builds
[Configure automated builds here](https://docs.docker.com/docker-hub/builds/)

## Important Docker Commands

```sh
# To list all running Docker containers
docker ps --all

# List all containers, both running and stopped
sudo docker ps -a
sudo docker ps -aq #quiet


docker system prune

# Show all containers

 # Remove Containers
 docker container rm 6d0806087e50
 docker container rm 8cf7ae980534

 docker system prune
 docker ps -l
```


# Manually Deploy the Springboot app on GKE Cluster

Springboot application runs on port 5000(resources.yaml). Load balancer should allow only port 8080

 Note: for GCR we need image name as ' --image=gcr.io/my-kubernetes-project-52188/hello-world:v1'

```shell
kubectl create deployment hello-service --image=nitinkc/k8s-helloworld
kubectl expose deployment hello-service --type=LoadBalancer --port=4012 --target-port=8080
kubectl scale deployment hello-service  --replicas=5
minikube tunnel
kubectl get svc
kubectl scale deployment hello-service  --replicas=2
```

API's

<app_name>:<port_number>/

<app_name>:<port_number>/hello-world

<app_name>:<port_number>/actuator

<app_name>:<port_number>/actuator/health