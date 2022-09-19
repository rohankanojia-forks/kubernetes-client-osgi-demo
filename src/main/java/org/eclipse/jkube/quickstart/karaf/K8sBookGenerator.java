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

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.apache.camel.CamelContext;

import java.io.InputStream;
import java.util.Random;

public class K8sBookGenerator {
  private int count = 1;
  private Random random = new Random();

  public InputStream generateOrder(CamelContext camelContext) {
    final int number = random.nextInt(5) + 1;
    createBook();
    final String orderSource = String.format("data/order%s.xml", number);
    return camelContext.getClassResolver().loadResourceAsStream(orderSource);
  }

  public String generateFileName() {
    return String.format("order%s.xml", count++);
  }

  public void createBook() {
    try (KubernetesClient client = new KubernetesClientBuilder().build()) {
      io.fabric8.kubernetes.internal.KubernetesDeserializer.registerCustomKind("testing.fabric8.io/v1alpha1", "Book", Book.class);
      MixedOperation<Book, BookList, Resource<Book>> fooClient = client.resources(Book.class, BookList.class);
      Book foo = fooClient.load(getClass().getResourceAsStream("/test-foo.yml")).get();
      foo.getMetadata().setName("book" + count);
      fooClient.inNamespace("default").resource(foo).createOrReplace();
    }
  }
}
