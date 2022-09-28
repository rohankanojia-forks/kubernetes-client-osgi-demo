package org.eclipse.jkube.quickstart.karaf;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.openshift.api.model.DeploymentConfig;
import io.fabric8.openshift.client.OpenShiftClient;
import org.slf4j.Logger;

public class OpenShiftDeploymentService {
  public void printDeploymentConfigs(Logger logger, OpenShiftClient openShiftClient) {
    openShiftClient.deploymentConfigs()
        .inNamespace("default")
        .list()
        .getItems()
        .stream()
        .map(DeploymentConfig::getMetadata)
        .map(ObjectMeta::getName)
        .forEach(logger::info);
  }
}
