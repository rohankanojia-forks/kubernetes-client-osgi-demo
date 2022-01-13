package org.eclipse.jkube.quickstart.karaf;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResourceList;

public class BookList extends CustomResourceList<Book> implements Namespaced {
}
