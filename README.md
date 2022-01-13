# Kubernetes Client on OSGI Demo

Demo application demonstrating Kubernetes Client running on OSGi. This application creates a new custom Resource Book every second.

## How to Build?

```shell
$ mvn clean install
```

## How to Run?

We'll be running application in Karaf based Pod in Kubernetes. I tested it on minikube. 

```shell
$ minikube start
$ eval $(minikube -p minikube docker-env)
$ mvn k8s:build k8s:resource k8s:apply
```

You can check whether application got deployed by checking running pods:
```shell
$ kubectl get pods
NAME                                          READY   STATUS    RESTARTS        AGE
kubernetes-client-osgi-demo-f4568dc45-qdr5j   1/1     Running   0               3s
```
Check logs of application:
```shell
$ mvn k8s:log
```

You should be seeing Book resources getting created every second:
```shell
$ kubectl get book -w
NAME     AGE
book1    58s
book10   13s
book11   8s
book12   3s
book2    53s
book3    48s
book4    43s
book5    38s
book6    33s
book7    28s
book8    23s
book9    18s
book13   0s
book14   0s
book15   0s
book16   0s
```


