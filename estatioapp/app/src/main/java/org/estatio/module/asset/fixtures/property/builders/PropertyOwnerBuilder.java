/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.module.asset.fixtures.property.builders;

import org.joda.time.LocalDate;

import org.estatio.module.asset.dom.Property;
import org.estatio.module.asset.dom.role.FixedAssetRoleTypeEnum;
import org.estatio.module.base.platform.fixturesupport.BuilderScriptAbstract;
import org.estatio.module.party.dom.Party;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class PropertyOwnerBuilder
        extends BuilderScriptAbstract<PropertyOwnerBuilder> {

    @Getter @Setter
    private Property property;

    @Getter @Setter
    private Party owner;

    @Getter @Setter
    private LocalDate startDate;

    @Getter @Setter
    private LocalDate endDate;

    @Override
    protected void execute(final ExecutionContext executionContext) {

        checkParam("property", executionContext, Property.class);
        checkParam("owner", executionContext, Party.class);

        defaultParam("startDate", executionContext, property.getAcquireDate());

        wrap(property).newRole(FixedAssetRoleTypeEnum.PROPERTY_OWNER, owner, startDate, endDate);
    }

}