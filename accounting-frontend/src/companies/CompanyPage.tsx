import Button from "../components/Button";
import Heading from "../components/Heading";
import Link from "../components/Link";
import PageLayout from "../components/PageLayout";
import Section from "../components/Section";
import Table from "../components/Table";
import { useFindCompanyByIdQuery } from "../generated/graphql-types";
import * as React from "react";
import { useParams } from "react-router-dom";
import { ID } from "graphql-ws";
import Paginator from "../customers/Paginator";

export default function CompanyPage() {
  const { companyId } = useParams<{ companyId: ID }>();

  const { loading, data, error } = useFindCompanyByIdQuery({
    variables: {
      companyId: companyId,
    },
  });

  if (loading) {
    return <PageLayout title="Companies">Loading</PageLayout>;
  } else if (error || !data) {
    return <PageLayout title="Companies">Error</PageLayout>;
  }

  let resultTable = null;
  if ( !loading && !error && data) {
    if (data.company.transactions.length === 0) {
      resultTable = <div className="max-w-2xl mx-auto">No transactions found</div>;
    } else {
      const values = data.company.transactions.map((transaction) => [
        transaction?.name,
        transaction?.number,
        transaction?.transactiondate,
        transaction?.amount,
        transaction?.type,
      ]);
      resultTable = (
        <div className="mt-8 border-4 border-gray-100 p-4">
          <Table
            title={`transactions`}
            labels={[
              "Name",
              "Number",
              "Date",
              "Amount",
              "Type"
            ]}
            values={values}
          />
        </div>
      );
    }
  }

  return (
    <PageLayout
      title={`Company - ${data.company.name}`}
    >
      <Section>
        <Table
          values={[
            ["Name", data.company.name],
            ["Address", data.company.address],
            ["City", data.company.city],
            ["Telephone", data.company.phone],
          ]}
        ></Table>
      </Section>
      <div className="mb-4">
        <Heading level="2">Transactions</Heading>
      </div>
      <div className="mb-8">{resultTable}</div>

      {/*<NewCompanyPanel  />*/}


    </PageLayout>
  );
}
