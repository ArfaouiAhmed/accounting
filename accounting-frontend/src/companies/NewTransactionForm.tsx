import Button from "../components/Button";
import ButtonBar from "../components/ButtonBar";
import Heading from "../components/Heading";
import Input from "../components/Input";
import Label from "../components/Label";
import Section from "../components/Section";
import {
  useAddTransactionMutation,
  CompanyTransactionsFragment
} from "../generated/graphql-types";
import * as React from "react";
import { useForm } from "react-hook-form";
import { ID } from "graphql-ws";
import { gql } from "@apollo/client";
import produce from "immer";

/** Fragment for updating the Cache after mutation (adding new Transaction to existing Company) */
const CompanyTransactions = gql`
    fragment CompanyTransactions on Company {
        id
        transactions {
            name
            number
            transactionDate
            amount
            type
        }
    }
`;
type TransactionFormData = {
  name: string,
  number: number,
  transactionDate: Date,
  amount: BigInt,
  type: string
};

type NewTransactionFormProps = {
  onFinish(): void;
  companyId: ID;
};

export default function NewTransactionForm({ onFinish, companyId }: NewTransactionFormProps) {
  const [addTransaction, { called, loading, error }] = useAddTransactionMutation({
    update(cache, { data }) {
      if (!data) {
        return;
      }
      const existingCompany = cache.readFragment<CompanyTransactionsFragment>({
        fragment: CompanyTransactions,
        id: `Company:${companyId}`,
      });
      if (existingCompany) {
        const newData = produce(existingCompany, (draftCompany) => {
          draftCompany.transactions.push(data.addTransaction.transaction);
        });
        cache.writeFragment<CompanyTransactionsFragment>({
          fragment: CompanyTransactions,
          data: newData,
        });
      }
    }});
  const { register, errors, handleSubmit } = useForm<TransactionFormData>();


  async function handleAddClick({ name, number, transactionDate, amount, type }: TransactionFormData) {
    const result = await addTransaction({
      variables: {
        input: {
          companyId: companyId,
          name,
          number,
          transactionDate,
          amount,
          type
        },
      },
    });
    if (result.data) {
      onFinish();
    }
  }

  return (
    <Section>
      <Heading level="3">Add Transaction</Heading>


      <Input
        type="text"
        name="name"
        ref={register({ required: true })}
        label="Name"
        error={errors.name && "Please fill in a name"}
      />
      <Input
        type="text"
        name="number"
        ref={register({ required: true })}
        label="Address"
        error={errors.number && "Please fill in a number"}
      />
      <Input
        type="text"
        name="date"
        ref={register({ required: true })}
        label="Date"
        error={errors.transactionDate && "Please fill in a date"}
      />
      <Input
        type="text"
        name="Amount"
        ref={register({ required: true })}
        label="Amount"
        error={errors.amount && "Please fill in an Amount"}
      />
      <Input
        type="text"
        name="Type"
        ref={register({ required: true })}
        label="Type"
        error={errors.type && "Please fill in a Type"}
      />

      <ButtonBar align="left">
        {called && loading ? (
          <Button disabled>Saving...</Button>
        ) : (
          <Button onClick={handleSubmit(handleAddClick)}>Save</Button>
        )}
        <Button type="secondary" onClick={onFinish}>
          Cancel
        </Button>
      </ButtonBar>
      {error && <Label>Saving failed</Label>}
    </Section>
  );
  return (
    <Section>
      <Heading level="3">Add Transaction</Heading>
    </Section>);
}
