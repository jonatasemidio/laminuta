Here's a step-by-step guide on how to create a cloud-native Java application using Spring:

**Step 1: Choose a Cloud Platform**

Select a cloud platform that you want to deploy your application to. Popular options include:

* Amazon Web Services (AWS)
* Microsoft Azure
* Google Cloud Platform (GCP)
* Cloud Foundry
* Kubernetes (on-premises or cloud-agnostic)

For this example, we'll use Kubernetes as our cloud platform.

**Step 2: Set up your Development Environment**

* Install Java 11 or later (e.g., OpenJDK 11)
* Install an IDE of your choice (e.g., Eclipse, IntelliJ IDEA, Visual Studio Code)
* Install the Spring Tool Suite (STS) or Spring Initializr plugin for your IDE
* Install Docker and Kubernetes CLI (if you haven't already)

**Step 3: Create a New Spring Boot Project**

* Open your IDE and create a new Spring Boot project using the Spring Initializr plugin or by visiting the Spring Initializr website
* Choose the following dependencies:
    + Web
    + Actuator
    + Cloud Config
    + Kubernetes
* Name your project (e.g., "cloud-native-java-app")

**Step 4: Configure your Application**

* In your project's `application.properties` file, add the following configuration:
```properties
spring:
  application:
    name: cloud-native-java-app
  cloud:
    config:
      enabled: true
  kubernetes:
    enabled: true
```
* Create a `bootstrap.properties` file with the following configuration:
```properties
spring:
  cloud:
    config:
      uri: ${CONFIG_SERVER_URI:http://localhost:8888}
```
**Step 5: Implement your Application**

* Create a new Java class (e.g., `CloudNativeJavaAppApplication`) with the `@SpringBootApplication` annotation
* Add a simple REST endpoint using `@RestController` and `@GetMapping` annotations:
```java
@RestController
public class CloudNativeJavaAppController {
  
  @GetMapping("/hello")
  public String hello() {
    return "Hello from Cloud Native Java App!";
  }
}
```
**Step 6: Containerize your Application**

* Create a `Dockerfile` in your project's root directory:
```dockerfile
FROM openjdk:11-jdk-alpine
WORKDIR /app
COPY target/cloud-native-java-app.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "cloud-native-java-app.jar"]
```
* Build your Docker image using the following command:
```
docker build -t cloud-native-java-app .
```
**Step 7: Create a Kubernetes Deployment**

* Create a `deployment.yaml` file in your project's root directory:
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: cloud-native-java-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: cloud-native-java-app
  template:
    metadata:
      labels:
        app: cloud-native-java-app
    spec:
      containers:
      - name: cloud-native-java-app
        image: cloud-native-java-app:latest
        ports:
        - containerPort: 8080
```
* Apply the deployment configuration to your Kubernetes cluster using the following command:
```
kubectl apply -f deployment.yaml
```
**Step 8: Expose your Application**

* Create a `service.yaml` file in your project's root directory:
```yaml
apiVersion: v1
kind: Service
metadata:
  name: cloud-native-java-app
spec:
  selector:
    app: cloud-native-java-app
  ports:
  - name: http
    port: 80
    targetPort: 8080
  type: LoadBalancer
```
* Apply the service configuration to your Kubernetes cluster using the following command:
```
kubectl apply -f service.yaml
```
**Step 9: Test your Application**

* Use the `kubectl` command to get the external IP address of your service:
```
kubectl get svc cloud-native-java-app -o jsonpath='{.status.loadBalancer.ingress[0].hostname}'
```
* Use a tool like `curl` to test your application:
```
curl http://<EXTERNAL_IP_ADDRESS>/hello
```
You should see the response "Hello from Cloud Native Java App!".

That's it! You've successfully created a cloud-native Java application using Spring and deployed it to a Kubernetes cluster.