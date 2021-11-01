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

import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.CreateOp;
import org.identityconnectors.framework.spi.operations.SchemaOp;
import org.identityconnectors.framework.spi.operations.TestOp;

import java.util.HashSet;
import java.util.Set;

@ConnectorClass(displayNameKey = "krbhack.connector.display", configurationClass = krbHackConfiguration.class)
public class krbHackConnector implements Connector, CreateOp, TestOp, SchemaOp {

    private static final Log LOG = Log.getLog(krbHackConnector.class);

    private krbHackConfiguration configuration;
    private krbHackConnection connection;

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void init(Configuration configuration) {
        LOG.ok("KrbHack Connector initialization started " + this.getClass().getName());
        this.configuration = (krbHackConfiguration)configuration;
        this.connection = new krbHackConnection(this.configuration);
    }

    @Override
    public void dispose() {
        configuration = null;
        if (connection != null) {
            connection.dispose();
            connection = null;
        }
    }

    @Override
    public void test() {

    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        LOG.info("Create new user");
        System.out.println(operationOptions.getOptions());

        for (Attribute a:set) {
            LOG.info("*********************" + a.toString());
        }
        return null;
    }

    @Override
    public Schema schema() {
        final SchemaBuilder schemaBuilder = new SchemaBuilder(krbHackConnector.class);
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


}
