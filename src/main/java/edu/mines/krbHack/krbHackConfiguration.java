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

import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.spi.ConfigurationProperty;

public class krbHackConfiguration extends AbstractConfiguration {

    private static final Log LOG = Log.getLog(krbHackConfiguration.class);

    private String sampleProperty;

    private String script;
    private String adminPrincipal;
    private String keytab;

    @Override
    public void validate() {
        //todo implement
    }

    @ConfigurationProperty(displayMessageKey = "krbhack.config.script", helpMessageKey = "krbhack.config.script.help", order = 1)
    public String getScript() { return script; }

    @ConfigurationProperty(displayMessageKey = "krbhack.config.adminPrincipal", helpMessageKey = "krbhack.config.script.help", order = 2)
    public String getAdminPrincipal() { return adminPrincipal; }

    @ConfigurationProperty(displayMessageKey = "krbhack.config.keytab", helpMessageKey = "krbhack.config.keytab.help", order = 3)
    public String getKeytab() { return keytab; }

    @ConfigurationProperty(displayMessageKey = "krbhack.config.sampleProperty",
            helpMessageKey = "krbhack.config.sampleProperty.help")
    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String sampleProperty) {
        this.sampleProperty = sampleProperty;
    }
}