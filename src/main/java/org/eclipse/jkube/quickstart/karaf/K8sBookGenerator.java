/**
 * Copyright (c) 2019 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at:
 *
 *     https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.jkube.quickstart.karaf;

import io.fabric8.kubernetes.api.model.APIGroup;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.client.OpenShiftClient;
import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Random;

public class K8sBookGenerator {
  private int count = 1;
  private final Random random = new Random();

  @BeanInject
  private KubernetesClient kubernetesClient;

  @BeanInject
  private OpenShiftClient openShiftClient;

  private final Logger logger = LoggerFactory.getLogger(K8sBookGenerator.class);

  public InputStream generateOrder(CamelContext camelContext) {
    final int number = random.nextInt(5) + 1;
    printApiGroups();
    new BookService().createBook(kubernetesClient, count);
//    new OpenShiftDeploymentService().printDeploymentConfigs(logger, openShiftClient);
    final String orderSource = String.format("data/order%s.xml", number);
    return camelContext.getClassResolver().loadResourceAsStream(orderSource);
  }

  public String generateFileName() {
    return String.format("order%s.xml", count++);
  }

  private void printApiGroups() {
    logger.info("Found {} apiGroups", kubernetesClient.getApiGroups().getGroups().size());
  }
}
