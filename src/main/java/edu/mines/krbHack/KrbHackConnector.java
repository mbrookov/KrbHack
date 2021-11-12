/*
 * Copyright (c) 2010-2014 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.mines.krbHack;

import edu.mines.krbHack.search.Operand;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.common.objects.filter.FilterTranslator;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.CreateOp;
import org.identityconnectors.framework.spi.operations.SchemaOp;
import org.identityconnectors.framework.spi.operations.SearchOp;
import org.identityconnectors.framework.spi.operations.TestOp;

import java.util.HashSet;
import java.util.Set;

@ConnectorClass(displayNameKey = "krbhack.connector.display", configurationClass = KrbHackConfiguration.class)
public class KrbHackConnector implements Connector, CreateOp, TestOp, SchemaOp, SearchOp<Operand> {

    private static final Log LOG = Log.getLog(KrbHackConnector.class);

    private KrbHackConfiguration configuration;
    private KrbHackConnection connection;

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void init(Configuration configuration) {
        LOG.ok("KrbHack Connector initialization started " + this.getClass().getName());
        this.configuration = (KrbHackConfiguration)configuration;
        this.connection = new KrbHackConnection(this.configuration);
    }

    @Override
    public final void dispose() {
        configuration = null;
        if (connection != null) {
            connection.dispose();
            connection = null;
        }
    }

    @Override
    public final void test() {
        LOG.info("*************************** Testing1..2..3..");

    }

    @Override
    public final Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        LOG.info("Create new user");
        System.out.println(operationOptions.getOptions());

        for (Attribute a:set) {
            LOG.info("*********************" + a.toString());
        }
        return null;
    }

    @Override
    public final Schema schema() {
        final SchemaBuilder schemaBuilder = new SchemaBuilder(KrbHackConnector.class);
        Set<AttributeInfo> attributes = new HashSet<AttributeInfo>();
        attributes = new HashSet<AttributeInfo>();

        attributes.add(OperationalAttributeInfos.PASSWORD);
        attributes.add(OperationalAttributeInfos.ENABLE);
        attributes.add(OperationalAttributeInfos.LOCK_OUT);
        attributes.add(OperationalAttributeInfos.DISABLE_DATE);

        AttributeInfoBuilder attrBuilder = new AttributeInfoBuilder();
        attrBuilder.setName("krbPrincipal");
        attrBuilder.setRequired(true);
        attrBuilder.setType(String.class);
        attrBuilder.setMultiValued(false);
        attributes.add(attrBuilder.build());

        AttributeInfoBuilder attrBuilder1 = new AttributeInfoBuilder();
        attrBuilder1.setName("krbPolicy");
        attrBuilder1.setRequired(true);
        attrBuilder1.setType(String.class);
        attrBuilder1.setMultiValued(false);
        attributes.add(attrBuilder1.build());

        final ObjectClassInfo ociInfoAccount =
                new ObjectClassInfoBuilder().setType(ObjectClass.ACCOUNT_NAME).addAllAttributeInfo(
                        attributes).build();

        schemaBuilder.defineObjectClass(ociInfoAccount);

        schemaBuilder.defineOperationOption(OperationOptionInfoBuilder.buildPagedResultsOffset());
        schemaBuilder.defineOperationOption(OperationOptionInfoBuilder.buildPageSize());

        return schemaBuilder.build();
    }

    /* for SearchOp - you must implement FilterTranslator and executeQuery */
    @Override
    public final FilterTranslator<Operand> createFilterTranslator(ObjectClass objectClass, OperationOptions operationOptions) {
        if (objectClass == null || (!objectClass.equals(ObjectClass.ACCOUNT)) && (!objectClass.equals(ObjectClass.GROUP)))
            throw new IllegalArgumentException(this.getClass().getName() + " FilterTranslator - ObjecltClass must be ACCOUNT OR GROUP");
        return new KrbHackFilterTranslator();
    }

    @Override
    public final void executeQuery(ObjectClass objectClass, Operand operand, ResultsHandler resultsHandler, OperationOptions operationOptions) {
        LOG.info("Execute Query!");

    }
}
