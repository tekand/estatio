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
package org.estatio.module.application.demos;

import org.apache.isis.applib.fixturescripts.DiscoverableFixtureScript;

import org.estatio.module.application.migrations.CreateInvoiceNumerators;
import org.estatio.module.application.fixtures.EstatioBaseLineFixture;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForDylanOfficeAdministratorGb;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForFaithConwayGb;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForJonathanPropertyManagerGb;
import org.estatio.module.asset.fixtures.property.personas.PropertyAndOwnerAndManagerForGraIt;
import org.estatio.module.asset.fixtures.property.personas.PropertyAndOwnerAndManagerForMnsFr;
import org.estatio.module.asset.fixtures.property.personas.PropertyAndOwnerAndManagerForVivFr;
import org.estatio.module.capex.fixtures.orderinvoice.OrderInvoiceFixture;
import org.estatio.module.asset.fixtures.property.personas.PropertyAndOwnerAndManagerForCARTEST;
import org.estatio.module.asset.fixtures.property.personas.PropertyAndOwnerAndManagerForHanSe;
import org.estatio.module.asset.fixtures.property.personas.PropertyAndOwnerAndManagerForMacFr;
import org.estatio.module.budget.fixtures.BudgetsForOxf;
import org.estatio.module.budget.fixtures.KeyTablesForOxf;
import org.estatio.module.budget.fixtures.PartitioningAndItemsForOxf;
import org.estatio.module.capex.fixtures.document.personas.IncomingPdfFixture;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndMandateForPoisonNl;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndMandateForTopModelGb;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForAcmeNl;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForHelloWorldGb;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForHelloWorldNl;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForMediaXGb;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForMiracleGb;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForPretGb;
import org.estatio.module.lease.fixtures.bankaccount.personas.BankAccountAndFaFaForTopModelGb;
import org.estatio.module.guarantee.fixtures.personas.GuaranteeForOxfTopModel001Gb;
import org.estatio.module.capex.fixtures.IncomingInvoiceFixture;
import org.estatio.module.lease.fixtures.invoicing.personas.InvoiceForLeaseItemTypeOfDiscountOneQuarterForOxfMiracle005;
import org.estatio.module.lease.fixtures.invoicing.personas.InvoiceForLeaseItemTypeOfRentOneQuarterForKalPoison001;
import org.estatio.module.lease.fixtures.invoicing.personas.InvoiceForLeaseItemTypeOfRentOneQuarterForOxfPoison003;
import org.estatio.module.lease.fixtures.lease.LeaseBreakOptionsForOxfMediax002Gb;
import org.estatio.module.lease.fixtures.lease.LeaseBreakOptionsForOxfPoison003Gb;
import org.estatio.module.lease.fixtures.lease.LeaseBreakOptionsForOxfTopModel001;
import org.estatio.module.lease.fixtures.lease.LeaseForOxfPret004Gb;
import org.estatio.module.lease.fixtures.lease.LeaseItemAndLeaseTermForRentForKalPoison001;
import org.estatio.module.lease.fixtures.lease.LeaseItemAndTermsForOxfMiracl005Gb;
import org.estatio.module.party.fixtures.numerator.personas.NumeratorForOrganisationFra;
import org.estatio.module.capex.fixtures.OrderFixture;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForBrunoTreasurerFr;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForEmmaTreasurerGb;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForFifineLacroixFr;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForGabrielHerveFr;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForGinoVannelliGb;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForLinusTorvaldsNl;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForOlivePropertyManagerFr;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForOscarCountryDirectorGb;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForRosaireEvrardFr;
import org.estatio.module.asset.fixtures.person.personas.PersonAndRolesForThibaultOfficerAdministratorFr;
import org.estatio.module.capex.fixtures.project.personas.ProjectsForGra;
import org.estatio.module.capex.fixtures.project.personas.ProjectsForKal;
import org.estatio.module.base.platform.applib.TickingFixtureClock;

public class EstatioDemoFixture extends DiscoverableFixtureScript {

    public EstatioDemoFixture() {
        this(null, "demo");
    }

    public EstatioDemoFixture(final String friendlyName, final String name) {
        super(friendlyName, name);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {
        TickingFixtureClock.replaceExisting();
        doExecute(executionContext);
    }

    private void doExecute(final ExecutionContext executionContext) {
        executionContext.executeChild(this, new EstatioBaseLineFixture());
        executionContext.executeChild(this, new PersonAndRolesForLinusTorvaldsNl());
        executionContext.executeChild(this, new BankAccountAndFaFaForAcmeNl());
        executionContext.executeChild(this, new BankAccountAndFaFaForHelloWorldNl());
        executionContext.executeChild(this, new BankAccountAndFaFaForHelloWorldGb());
        executionContext.executeChild(this, new BankAccountAndMandateForTopModelGb());
        executionContext.executeChild(this, new LeaseBreakOptionsForOxfTopModel001());
        executionContext.executeChild(this, new BankAccountAndFaFaForMediaXGb());
        executionContext.executeChild(this, new LeaseBreakOptionsForOxfMediax002Gb());
        executionContext.executeChild(this, new BankAccountAndFaFaForPretGb());
        executionContext.executeChild(this, new LeaseForOxfPret004Gb());
        executionContext.executeChild(this, new BankAccountAndFaFaForMiracleGb());
        executionContext.executeChild(this, new LeaseItemAndTermsForOxfMiracl005Gb());
        executionContext.executeChild(this, new BankAccountAndMandateForPoisonNl());
        executionContext.executeChild(this, new LeaseBreakOptionsForOxfPoison003Gb());
        executionContext.executeChild(this, new InvoiceForLeaseItemTypeOfRentOneQuarterForOxfPoison003());
        executionContext.executeChild(this, new LeaseItemAndLeaseTermForRentForKalPoison001());
        executionContext.executeChild(this, new InvoiceForLeaseItemTypeOfRentOneQuarterForKalPoison001());
        executionContext.executeChild(this, new InvoiceForLeaseItemTypeOfDiscountOneQuarterForOxfMiracle005());
        executionContext.executeChild(this, new GuaranteeForOxfTopModel001Gb());
        executionContext.executeChild(this, new BankAccountAndFaFaForTopModelGb());
        executionContext.executeChild(this, new PersonAndRolesForGinoVannelliGb());

        executionContext.executeChild(this, new PropertyAndOwnerAndManagerForGraIt());
        executionContext.executeChild(this, new PropertyAndOwnerAndManagerForVivFr());
        executionContext.executeChild(this, new PropertyAndOwnerAndManagerForHanSe());
        executionContext.executeChild(this, new PropertyAndOwnerAndManagerForMnsFr());
        executionContext.executeChild(this, new PropertyAndOwnerAndManagerForMacFr());

        executionContext.executeChild(this, new PersonAndRolesForDylanOfficeAdministratorGb()); // gb mailroom
        executionContext.executeChild(this, new PersonAndRolesForJonathanPropertyManagerGb());  // gb property mgr for OXF
        executionContext.executeChild(this, new PersonAndRolesForFaithConwayGb());  // gb country administrator
        executionContext.executeChild(this, new PersonAndRolesForOscarCountryDirectorGb());  // gb country director
        executionContext.executeChild(this, new PersonAndRolesForEmmaTreasurerGb());   // gb treasurer

        executionContext.executeChild(this, new PersonAndRolesForThibaultOfficerAdministratorFr());  // fr mailroom
        executionContext.executeChild(this, new PersonAndRolesForFifineLacroixFr());  // fr property mgr for VIV and MNS
        executionContext.executeChild(this, new PersonAndRolesForOlivePropertyManagerFr());  // fr property mgr for MAC
        executionContext.executeChild(this, new PersonAndRolesForRosaireEvrardFr());  // fr country administrator
        executionContext.executeChild(this, new PersonAndRolesForGabrielHerveFr());  // fr country director
        executionContext.executeChild(this, new PersonAndRolesForBrunoTreasurerFr()); // fr treasurer

        executionContext.executeChild(this, new ProjectsForKal());
        executionContext.executeChild(this, new ProjectsForGra());

        executionContext.executeChild(this, new BudgetsForOxf());
        executionContext.executeChild(this, new KeyTablesForOxf());
        executionContext.executeChild(this, new PartitioningAndItemsForOxf());

        executionContext.executeChild(this, new PropertyAndOwnerAndManagerForCARTEST());
        executionContext.executeChild(this, new NumeratorForOrganisationFra());

        executionContext.executeChild(this, new CreateInvoiceNumerators());

        executionContext.executeChild(this, new OrderInvoiceFixture());

        executionContext.executeChild(this, new IncomingPdfFixture().setRunAs("estatio-user-fr"));

        executionContext.executeChild(this, new OrderFixture());

        executionContext.executeChild(this, new IncomingInvoiceFixture());

    }

}