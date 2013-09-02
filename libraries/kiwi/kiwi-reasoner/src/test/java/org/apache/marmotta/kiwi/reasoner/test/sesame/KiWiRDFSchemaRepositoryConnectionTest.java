/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.marmotta.kiwi.reasoner.test.sesame;

import org.apache.marmotta.kiwi.config.KiWiConfiguration;
import org.apache.marmotta.kiwi.reasoner.engine.ReasoningConfiguration;
import org.apache.marmotta.kiwi.reasoner.sail.KiWiReasoningSail;
import org.apache.marmotta.kiwi.sail.KiWiStore;
import org.apache.marmotta.kiwi.test.junit.KiWiDatabaseRunner;
import org.apache.marmotta.kiwi.transactions.sail.KiWiTransactionalSail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.repository.RDFSchemaRepositoryConnectionTest;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.base.RepositoryConnectionWrapper;
import org.openrdf.repository.base.RepositoryWrapper;
import org.openrdf.repository.sail.SailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Run the {@link KiWiRDFSchemaRepositoryConnectionTest}s.
 * @author Jakob Frank <jakob@apache.org>
 *
 */
@RunWith(KiWiDatabaseRunner.class)
public class KiWiRDFSchemaRepositoryConnectionTest extends RDFSchemaRepositoryConnectionTest {

    public Logger log = LoggerFactory.getLogger(this.getClass());
    
    private final KiWiConfiguration config;
    private KiWiReasoningSail rsail;

    public KiWiRDFSchemaRepositoryConnectionTest(KiWiConfiguration config) {
        this.config = config;
    }
    
    /* (non-Javadoc)
     * @see org.openrdf.repository.RepositoryConnectionTest#createRepository()
     */
    @Override
    protected Repository createRepository() throws Exception {
        KiWiStore sail = new KiWiStore(config);
        KiWiTransactionalSail tsail = new KiWiTransactionalSail(sail);
        rsail = new KiWiReasoningSail(tsail, new ReasoningConfiguration());
        
        
        return new RepositoryWrapper(new SailRepository(rsail)) {
            @Override
            public RepositoryConnection getConnection()
                    throws RepositoryException {
                return new RepositoryConnectionWrapper(this, super.getConnection()) {
                    @Override
                    public void commit() throws RepositoryException {
                        super.commit();
                        
                        // wait for the reasoner to finish
                        try {
                                while(rsail.getEngine().isRunning()) {
                                    log.info("sleeping for 100ms to let engine finish processing ... ");
                                    Thread.sleep(100);
                                }
                                Thread.sleep(100);
                                log.info("sleeping for 100ms to let engine finish processing ... ");
                        } catch (InterruptedException e) {
                            throw new RepositoryException("Could not finish reasoning", e);
                        }
                    }
                };
            }
        };
    }
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        log.info("Setting up reasoning for RDFS");
        rsail.addProgram("rdfs", this.getClass().getResourceAsStream("rdfs.skwrl"));
    }
    
    @Override
    @Test
    public void testExplicitFlag() throws Exception {
        // We need at least _some_ data to infer... 
        testCon.begin();
        testCon.add(bob, RDF.TYPE, RDFS.RESOURCE);
        testCon.commit();
        
        super.testExplicitFlag();
    }

    @Override
    @Test
    @Ignore("in KiWi, inferencing is triggered on commit")
    public void testInferencerQueryDuringTransaction() throws Exception {
    }
    
    @Override
    @Test
    @Ignore("in KiWi, inferencing is triggered on commit")
    public void testInferencerTransactionIsolation() throws Exception {
    }
}