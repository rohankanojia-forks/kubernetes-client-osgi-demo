package org.eclipse.jkube.quickstart.karaf;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

public class BookService {
  public void createBook(KubernetesClient kubernetesClient, int count) {
    io.fabric8.kubernetes.internal.KubernetesDeserializer.registerCustomKind("testing.fabric8.io/v1alpha1", "Book", Book.class);
    kubernetesClient.resources(Book.class);
    MixedOperation<Book, BookList, Resource<Book>> fooClient = kubernetesClient.resources(Book.class, BookList.class);

    Book foo = fooClient.load(getClass().getResourceAsStream("/test-foo.yml")).get();
    foo.getMetadata().setName("book" + count);
    fooClient.inNamespace("default").resource(foo).createOrReplace();
  }
}
