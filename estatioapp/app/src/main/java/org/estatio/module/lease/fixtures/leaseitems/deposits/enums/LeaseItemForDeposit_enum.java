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
package org.estatio.module.lease.fixtures.leaseitems.deposits.enums;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import org.apache.isis.applib.fixturescripts.PersonaWithBuilderScript;
import org.apache.isis.applib.fixturescripts.PersonaWithFinder;
import org.apache.isis.applib.services.registry.ServiceRegistry2;

import org.estatio.module.lease.dom.Fraction;
import org.estatio.module.lease.dom.Lease;
import org.estatio.module.lease.dom.LeaseItem;
import org.estatio.module.lease.dom.LeaseItemRepository;
import org.estatio.module.lease.fixtures.lease.enums.Lease_enum;
import org.estatio.module.lease.fixtures.leaseitems.builders.LeaseItemForDepositBuilder;
import org.estatio.module.lease.fixtures.leaseitems.builders.LeaseTermForDepositBuilder;
import org.estatio.module.lease.fixtures.leaseitems.rent.enums.LeaseItemForRent_enum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import static org.incode.module.base.integtests.VT.bi;

@AllArgsConstructor()
@Getter
@Accessors(chain = true)
public enum LeaseItemForDeposit_enum implements PersonaWithFinder<LeaseItem>, PersonaWithBuilderScript<LeaseItem, LeaseItemForDepositBuilder> {

    OxfMiracle005bGb(Lease_enum.OxfMiracl005Gb, bi(1), LeaseItemForRent_enum.OxfMiracl005Gb,
            new TermSpec[]{
                new TermSpec(null, null, Fraction.M6)
            }),
    OxfTopModel001Gb(Lease_enum.OxfTopModel001Gb, bi(1), LeaseItemForRent_enum.OxfTopModel001Gb,
            new TermSpec[]{
                new TermSpec(null, null, Fraction.M6)
            })
    ;

    private final Lease_enum lease_d;
    private final BigInteger sequence;
    private final LeaseItemForRent_enum sourceItem_d;
    private final TermSpec[] termSpecs;

    @AllArgsConstructor
    @Data
    static class TermSpec {
        LocalDate startDate;
        LocalDate endDate;
        //LeaseTermFrequency leaseTermFrequency;
        Fraction fraction;
    }

    @Override
    public LeaseItemForDepositBuilder builder() {
        return new LeaseItemForDepositBuilder()
                .setPrereq((f,ec) -> f.setLease(f.objectFor(lease_d, ec)))
                .setPrereq((f,ec) -> f.setSourceItem(f.objectFor(sourceItem_d, ec)))
                .setSequence(sequence)
                .setPrereq((f,ec) -> f.setTermSpecs(
                        Arrays.stream(termSpecs)
                                .map(x -> new LeaseTermForDepositBuilder.TermSpec(x.startDate, x.endDate, x.fraction))
                                .collect(Collectors.toList())
                        ))
                ;
    }

    @Override
    public LeaseItem findUsing(final ServiceRegistry2 serviceRegistry) {
        final Lease lease = lease_d.findUsing(serviceRegistry);
        final LocalDate startDate = lease.getStartDate();
        final LeaseItemRepository leaseItemRepository = serviceRegistry.lookupService(LeaseItemRepository.class);
        return leaseItemRepository.findLeaseItem(
                lease, LeaseItemForDepositBuilder.LEASE_ITEM_TYPE, startDate, sequence);
    }
}
