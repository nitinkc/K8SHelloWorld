# Kubernetes HelloWorld Deployment Guide

This guide walks you through building, containerizing, and deploying a Spring Boot application on Kubernetes using 
Docker and Minikube. It also covers testing the application locally and on Google Kubernetes Engine (GKE).

---

## 🛠️ Steps Overview

1. **Build the Spring Boot Project**
2. **Create and Push Docker Image**
3. **Test Locally with Docker**
4. **Deploy on Minikube**
5. **Deploy on GKE (Google Kubernetes Engine)**
6. **Test Application Endpoints**

---

## 1. Build the Spring Boot Project

Compile your Spring Boot application to generate the `.jar` file. Ensure your `Dockerfile` is set up to copy and 
run this `.jar` file.
- [DockerFile](https://github.com/nitinkc/K8SHelloWorld/blob/master/Dockerfile)

---

## 2. Create and Push Docker Image

### a. Create a Docker Hub Repository

- Visit [Docker Hub](https://hub.docker.com/) and create a new repository, e.g., `nitinkc/k8s-helloworld`.

### b. Build the Docker Image

Navigate to the directory containing your `Dockerfile` and run:

```bash
docker build -t nitinkc/k8s-helloworld:v3 .
```

Verify the image:

```bash
docker images
```

### c. Tag the Image for Docker Hub

Tag the image to match your Docker Hub repository:

```bash
docker tag 6762c6bb9288 nitinkc/k8s-helloworld:k8s-hw-v3
# Or
docker tag nitinkc/k8s-helloworld:v3 nitinkc/k8s-helloworld:k8s-hw-v3
```

### d. Log in to Docker Hub

```bash
docker login --username=nitinkc
```

### e. Push the Image to Docker Hub

```bash
docker push nitinkc/k8s-helloworld:k8s-hw-v3
```

### f. Pull the Image to Verify

```bash
docker pull nitinkc/k8s-helloworld:k8s-hw-v3
```

---

## 3. Test Locally with Docker

Run the Docker container and map ports:

```bash
docker run -p 5005:8080 -t nitinkc/k8s-helloworld:k8s-hw-v3
```

Access the application at [http://localhost:5005/](http://localhost:5005/).

---

## 4. Deploy on Minikube

### a. Start Minikube Cluster

```bash
minikube start
```

### b. Access Minikube Dashboard (Optional)

```bash
minikube dashboard
```

### c. Create Deployment and Expose Service

```bash
kubectl create deployment hello-service --image=nitinkc/k8s-helloworld:k8s-hw-v3
kubectl expose deployment hello-service --type=LoadBalancer --port=4012 --target-port=8080
```

### d. Scale the Deployment

```bash
kubectl scale deployment hello-service --replicas=5
```

### e. Start Minikube Tunnel

```bash
minikube tunnel
```

Access the application at [http://localhost:4012/](http://localhost:4012/).

---

## 5. Deploy on GKE (Google Kubernetes Engine)

### a. Set Up GKE Cluster

Ensure you have a GKE cluster set up. If not, create one using the Google Cloud Console or `gcloud` CLI.

### b. Authenticate Docker with GCR (Google Container Registry)

```bash
gcloud auth configure-docker
```

### c. Tag the Image for GCR

```bash
docker tag nitinkc/k8s-helloworld:k8s-hw-v3 gcr.io/<your-project-id>/k8s-helloworld:v3
```

### d. Push the Image to GCR

```bash
docker push gcr.io/<your-project-id>/k8s-helloworld:v3
```

### e. Deploy on GKE

```bash
kubectl create deployment hello-service --image=gcr.io/<your-project-id>/k8s-helloworld:v3
kubectl expose deployment hello-service --type=LoadBalancer --port=8080 --target-port=8080
```

Access the application using the external IP provided by the LoadBalancer.

---

## 6. Test Application Endpoints

Test the following endpoints to ensure the application is running correctly:

- `http://localhost:8080/`
- `http://localhost:8080/hello-world`
- `http://localhost:8080/actuator`
- `http://localhost:8080/actuator/health`

---

## 🔧 Important Docker Commands

- List all Docker containers:

  ```bash
  docker ps --all
  ```

- Remove specific containers:

  ```bash
  docker container rm <container-id>
  ```

- Clean up unused Docker resources:

  ```bash
  docker system prune
  ```

---

## 🔄 Update Deployment with New Image

After building and pushing a new image (e.g., `k8s-hw-v5`), update the deployment:

```bash
kubectl set image deployment/hello-service hello-service=nitinkc/k8s-helloworld:k8s-hw-v5
```

Alternatively, find the container name and update:

```bash
kubectl get deployment hello-service -o yaml
kubectl set image deployment/hello-service <container-name>=nitinkc/k8s-helloworld:k8s-hw-v5
```

---

## 🧪 Testing with Docker

Run the Docker container with port mapping:

```bash
docker run --publish 5005:8080 -t nitinkc/k8s-helloworld:k8s-helloworld-latest
```

Access the application at [http://localhost:5005/](http://localhost:5005/).

---

## 🧪 Testing with Minikube

Start the Minikube cluster:

```bash
minikube start
```

Access the Minikube dashboard:

```bash
minikube dashboard
```

Create the deployment and expose a service:

```bash
kubectl create deployment hello-service --image=nitinkc/k8s-helloworld:k8s-hw-v3
kubectl expose deployment hello-service --type=LoadBalancer --port=4012 --target-port=8080
```

Start a tunnel to test the service:

```bash
minikube tunnel
```

Test the service at [http://localhost:4012/](http://localhost:4012/).

---

## 🔄 Subsequent Updates

To update the deployment with a new image version:

```bash
kubectl set image deployment/hello-service hello-service=nitinkc/k8s-helloworld:k8s-hw-v5
```

Alternatively, find the container name and update:

```bash
kubectl get deployment hello-service -o yaml
kubectl set image deployment/hello-service <container-name>=nitinkc/k8s-helloworld:k8s-hw-v5
```