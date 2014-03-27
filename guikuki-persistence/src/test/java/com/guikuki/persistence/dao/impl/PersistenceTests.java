package com.guikuki.persistence.dao.impl;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by antoniosilvadelpozo on 26/03/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({PhotoDAOImplTest.class, RestaurantDAOImplTest.class})
public class PersistenceTests {

    private static MongodExecutable mongodExe;
    private static MongodProcess mongod;

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpEmbeddedMongoDB() throws Exception {
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        mongodExe = runtime.prepare(new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net(27017, Network.localhostIsIPv6())).build());
        mongod = mongodExe.start();
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void stopEmbeddedMongoDB() throws Exception {
        mongod.stop();
        mongodExe.stop();
    }

}
