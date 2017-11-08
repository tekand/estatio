package org.estatio.module.budgetassignment.contributions;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.estatio.module.budgetassignment.dom.override.BudgetOverrideForFixed;
import org.estatio.module.budgetassignment.dom.override.BudgetOverrideRepository;
import org.estatio.module.budgeting.dom.budgetcalculation.BudgetCalculationType;
import org.estatio.module.charge.dom.Charge;
import org.estatio.module.charge.dom.ChargeRepository;
import org.estatio.module.lease.dom.Lease;

/**
 * This cannot be inlined because Lease doesn't know about BudgetOverrideRepository.
 */
@Mixin
public class Lease_NewFixedOverride {

    private final Lease lease;
    public Lease_NewFixedOverride(Lease lease){
        this.lease = lease;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(contributed = Contributed.AS_ACTION)
    @MemberOrder(name="budgetOverrides", sequence = "2")
    public BudgetOverrideForFixed newFixedValue(
            final BigDecimal fixedValue,
            @Nullable
            final LocalDate startDate,
            @Nullable
            final LocalDate endDate,
            final Charge invoiceCharge,
            @Nullable
            final Charge incomingCharge,
            @Nullable
            final BudgetCalculationType type,
            final String reason
    ) {
        return budgetOverrideRepository.newBudgetOverrideForFixed(fixedValue, lease,startDate,endDate,invoiceCharge,incomingCharge,type, reason);
    }

    public List<Charge> choices3NewFixedValue() {
        return chargeRepository.allOutgoing();
    }

    public List<Charge> choices4NewFixedValue() {
        return chargeRepository.allIncoming();
    }


    public String validateNewFixedValue(
            final BigDecimal fixedValue,
            final LocalDate startDate,
            final LocalDate endDate,
            final Charge invoiceCharge,
            final Charge incomingCharge,
            final BudgetCalculationType type,
            final String reason
    ){
        return budgetOverrideRepository.validateNewBudgetOverride(lease, startDate, endDate, invoiceCharge, incomingCharge, type, reason);
    }

    @Inject
    private BudgetOverrideRepository budgetOverrideRepository;

    @Inject
    private ChargeRepository chargeRepository;

}