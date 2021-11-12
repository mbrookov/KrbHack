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

import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.util.Objects;

public class KrbHackConfiguration extends AbstractConfiguration {

    private static final Log LOG = Log.getLog(KrbHackConfiguration.class);

    private String sampleProperty;

    private String script;
    private String adminPrincipal;
    private String keytab;

    @Override
    public void validate() {
        LOG.info("*************************validate called!");
        if (StringUtil.isBlank(keytab)) {
            throw new ConfigurationException("Ukeytab must not be blank!");
        }
        //todo implement
    }

    @ConfigurationProperty(displayMessageKey = "krbhack.script.display", helpMessageKey = "krbhack.script.help", order = 1, required = true)
    public String getScript() {
        return script;
    }

    public final void setScript(final String script) {
        this.script = script;
    }

    @ConfigurationProperty(required = true, displayMessageKey = "krbhack.adminPrincipal.display", helpMessageKey = "krbhack.adminPrincipal.help", order = 2)
    public String getAdminPrincipal() {
        return adminPrincipal;
    }

    public final void setAdminPrincipal(final String adminPrincipal) {
        this.adminPrincipal = adminPrincipal;
    }

    @ConfigurationProperty(required = true, displayMessageKey = "krbhack.keytab.display", helpMessageKey = "krbhack.keytab.help", order = 3)
    public String getKeytab() {
        return keytab;
    }

    public final void setKeytab(final String keytab) {
        this.keytab = keytab;
    }

    @ConfigurationProperty(displayMessageKey = "krbhack.sampleProperty.display", helpMessageKey = "krbhack.sampleProperty.help", order = 4)
    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String sampleProperty) {
        this.sampleProperty = sampleProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KrbHackConfiguration)) return false;
        KrbHackConfiguration that = (KrbHackConfiguration) o;
        return Objects.equals(sampleProperty, that.sampleProperty) && Objects.equals(script, that.script) && Objects.equals(adminPrincipal, that.adminPrincipal) && Objects.equals(keytab, that.keytab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sampleProperty, script, adminPrincipal, keytab);
    }

    @Override
    public String toString() {
        return "krbHackConfiguration{" +
                "sampleProperty='" + sampleProperty + '\'' +
                ", script='" + script + '\'' +
                ", adminPrincipal='" + adminPrincipal + '\'' +
                ", keytab='" + keytab + '\'' +
                '}';
    }
}