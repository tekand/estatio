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
package org.estatio.module.asset.fixtures.person;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.estatio.module.asset.fixtures.person.builders.PersonAndRolesBuilder;
import org.estatio.module.asset.fixtures.person.enums.Person_enum;
import org.estatio.module.party.dom.Person;
import org.estatio.module.party.dom.role.IPartyRoleType;
import org.estatio.module.party.fixtures.organisation.personas.OrganisationForYoukeaSe;

import lombok.Getter;

public abstract class PersonAndRolesAbstract extends FixtureScript {

    private final Person_enum data;

    @Getter
    Person person;

    protected PersonAndRolesAbstract(final Person_enum data) {
        this.data = data;
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        executionContext.executeChild(this, new OrganisationForYoukeaSe());

        final PersonAndRolesBuilder personAndRolesBuilder = new PersonAndRolesBuilder();
        personAndRolesBuilder
                .setReference(data.getRef())
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setInitials(data.getInitials())
                .setSecurityUsername(data.getSecurityUserName())
                .setPersonGenderType(data.getPersonGenderType())
                .setAtPath(data.getApplicationTenancy().getPath())
                .setRelationshipType(data.getPartyRelationshipType())
                .setFromParty(data.getPartyFrom());

        for (final IPartyRoleType partyRoleType : data.getPartyRoleTypes()) {
            personAndRolesBuilder.addPartyRoleType(partyRoleType);
        }
        for (final Person_enum.FixedAssetRoleSpec roleSpec : data.getFixedAssetRoles()) {
            personAndRolesBuilder.addFixedAssetRole(roleSpec.getFixedAssetRole(), roleSpec.getProperty().getRef());
        }
        person = personAndRolesBuilder
                .build(this, executionContext)
                .getPerson();

    }


}
