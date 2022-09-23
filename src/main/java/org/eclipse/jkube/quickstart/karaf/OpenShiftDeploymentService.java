package org.eclipse.jkube.quickstart.karaf;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.openshift.api.model.DeploymentConfig;
import io.fabric8.openshift.client.OpenShiftClient;

public class OpenShiftDeploymentService {

  public void printDeploymentConfigs(OpenShiftClient openShiftClient) {
    openShiftClient.deploymentConfigs()
        .inNamespace("default")
        .list()
        .getItems()
        .stream()
        .map(DeploymentConfig::getMetadata)
        .map(ObjectMeta::getName)
        .forEach(System.out::println);
  }
}
