import Button from "../components/Button";
import ButtonBar from "../components/ButtonBar";
import Heading from "../components/Heading";
import Input from "../components/Input";
import Label from "../components/Label";
import Section from "../components/Section";
import {
  useAddCompanyMutation,
  CustomerCompaniesFragment
} from "../generated/graphql-types";
import * as React from "react";
import { useForm } from "react-hook-form";
import { ID } from "graphql-ws";
import { gql } from "@apollo/client";
import produce from "immer";

/** Fragment for updating the Cache after mutation (adding new Company to existing Customer) */
const CustomerCompanies = gql`
    fragment CustomerCompanies on Customer {
        id
        companies {
                name
                address
                city
                phone
        }
    }
`;
type CompanyFormData = {
  name: string;
  address: string;
  city: string;
  telephone: string;
};

type NewCompanyFormProps = {
  onFinish(): void;
  customerId: ID;
};

export default function NewCompanyForm({ onFinish, customerId }: NewCompanyFormProps) {
  const [addCompany, { called, loading, error }] = useAddCompanyMutation({
    update(cache, { data }) {
      if (!data) {
        return;
      }
      const existingCustomer = cache.readFragment<CustomerCompaniesFragment>({
        fragment: CustomerCompanies,
        id: `Customer:${customerId}`,
      });
      if (existingCustomer) {
        const newData = produce(existingCustomer, (draftCustomer) => {
          draftCustomer.companies.push(data.addCompany.company);
        });
        cache.writeFragment<CustomerCompaniesFragment>({
          fragment: CustomerCompanies,
          data: newData,
        });
      }
    }});
  const { register, errors, handleSubmit } = useForm<CompanyFormData>();


  async function handleAddClick({ name, address, city, telephone }: CompanyFormData) {
    const result = await addCompany({
      variables: {
        input: {
          customerId: customerId,
          name,
          address,
          city,
          telephone,
        },
      },
    });
    if (result.data) {
      onFinish();
    }
  }

  return (
    <Section>
      <Heading level="3">Add Company</Heading>


      <Input
        type="text"
        name="name"
        ref={register({ required: true })}
        label="Name"
        error={errors.name && "Please fill in a name"}
      />
      <Input
        type="text"
        name="address"
        ref={register({ required: true })}
        label="Address"
        error={errors.address && "Please fill in a address"}
      />
      <Input
        type="text"
        name="city"
        ref={register({ required: true })}
        label="City"
        error={errors.city && "Please fill in a city"}
      />
      <Input
        type="text"
        name="telephone"
        ref={register({ required: true })}
        label="telephone"
        error={errors.telephone && "Please fill in a telephone"}
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
      <Heading level="3">Add Company</Heading>
    </Section>);
}
