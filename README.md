# K8SHelloWorld

#### Running from localhost

http://localhost:8080/

### For Docker hub manual push

1. Create Repository on https://hub.docker.com/ . <repo-name>
2. Log into the Docker Hub from the command line
`docker login --username=nitinkc --email=gs.nitin@gmail.com`
2. Check the image ID (on local machine) using `docker images`
3. Tag the image `docker tag bb38976d03cf dockerhub-user-name/<repo-name>e:tag-name`
4. Push the image to the repository  created `docker push nitinkc/<repo-name>`

##### Example

```sh
docker login --username=nitinkc
docker images
docker tag 33a7bad180b7 nitinkc/k8s-helloworld:k8s-helloworld-latest
docker push nitinkc/k8s-helloworld
docker pull nitinkc/k8s-helloworld:k8s-helloworld-latest
docker run --publish 5005:5005 -t nitinkc/k8s-helloworld:k8s-helloworld-latest
```

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
