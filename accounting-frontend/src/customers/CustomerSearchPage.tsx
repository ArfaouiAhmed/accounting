import Button from "../components/Button";
import Input from "../components/Input";
import Link from "../components/Link";
import PageLayout from "../components/PageLayout";
import Table from "../components/Table";
import { useFindCustomerByNameLazyQuery } from "../generated/graphql-types";
import * as React from "react";
import { useForm } from "react-hook-form";
import Paginator from "./Paginator";

type FindCustomerFormData = { name: string };

export default function CustomersPage() {
  const [
    findCustomersByName,
    { loading, data, error, called, refetch },
  ] = useFindCustomerByNameLazyQuery();
  const { register, handleSubmit } = useForm<FindCustomerFormData>({});

  function handleFindClick({ name }: FindCustomerFormData) {
    findCustomersByName({
      variables: name ? { name, page: 0 } : { name: null, page: 0 },
    });
  }

  function handlePageClick(newPageNumber: number) {
    if (refetch) {
      refetch({
        page: newPageNumber,
      });
    }
  }

  let resultTable = null;
  if (called && !loading && !error && data) {
    if (data.customers.customers.length === 0) {
      resultTable = <div className="max-w-2xl mx-auto">No customers found</div>;
    } else {
      const values = data.customers.customers.map((customer) => [
        <Link to={`/customers/${customer?.id}`}>{customer?.name}</Link>,
        customer?.name,
        customer?.address,
        customer?.city,
        customer?.phone,
      ]);
      resultTable = (
        <div className="mt-8 border-4 border-gray-100 p-4">
          <Table
            title={`${data.customers.pageInfo.customersCount} customers found`}
            labels={[
              "Name",
              "Address",
              "City",
              "Telephone",
            ]}
            values={values}
          />
          <Paginator
            pageInfo={data.customers.pageInfo}
            onPageClick={handlePageClick}
          />
        </div>
      );
    }
  }

  const searchButton = (
    <Button onClick={handleSubmit(handleFindClick)}>Find</Button>
  );
  return (
    <PageLayout title="Search Customer">
      <div className="max-w-2xl mx-auto">
        <div className="flex">
          <Input
            ref={register}
            label="Name"
            name="name"
            action={searchButton}
          />
        </div>
      </div>
      <div className="mb-8">{resultTable}</div>
    </PageLayout>
  );
}
