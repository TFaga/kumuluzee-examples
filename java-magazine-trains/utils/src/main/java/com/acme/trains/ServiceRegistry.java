package com.acme.trains;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * <p>Manages the service registry API and the connection to the Zookeeper backend.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@ApplicationScoped
public class ServiceRegistry {

    private final CuratorFramework zookeeper;
    private final ConcurrentHashMap<String, String> zonePaths;

    /**
     * <p>Retrieves the Zookeeper service URI from the environemnt and initiates the client.</p>
     */
    @Inject
    public ServiceRegistry() {

        try {
            String zookeeperUri = System.getenv("ZOOKEEPER_URI");

            zookeeper = CuratorFrameworkFactory.newClient(zookeeperUri, new RetryNTimes(5, 1000));

            zookeeper.start();

            zonePaths = new ConcurrentHashMap<>();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

    /**
     * <p>Registers the service with Zookeeper. Creates an ephemeral entry for the specified name and uri.</p>
     *
     * @param name The name of entry in service discovery, usually the services name.
     * @param uri The URI to be saved of the service specified in the name param.
     */
    public void registerService(String name, String uri) {

        try {
            String node = "/services/" + name;

            if (zookeeper.checkExists().forPath(node) == null) {

                zookeeper.create().creatingParentsIfNeeded().forPath(node);
            }

            String nodePath = zookeeper.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(node + "/_", uri.getBytes());

            zonePaths.put(uri, nodePath);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

    /**
     * <p>Unregisters the service with Zookeeper. Removes the entry for the specified name and uri if it exists.</p>
     *
     * @param name The name of entry in service discovery, usually the services name.
     * @param uri The URI to be saved of the service specified in the name param.
     */
    public void unregisterService(String name, String uri) {

        try {
            if (zonePaths.contains(uri)) {
                zookeeper.delete().forPath(zonePaths.get(uri));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

    /**
     * <p>Searches the registry for the provided service name and returns its URI.</p>
     *
     * @param name The name of the service for which the URI was requested.
     * @return The URI of the requested service.
     */
    public String discoverServiceURI(String name) {

        try {
            String node = "/services/" + name;

            List<String> uris = zookeeper.getChildren().forPath(node);

            return new String(zookeeper.getData().forPath(ZKPaths.makePath(node, uris.get(0))));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }
}