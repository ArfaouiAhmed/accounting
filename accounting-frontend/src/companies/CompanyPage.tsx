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
      {/*<div className="mb-4">*/}
      {/*  <Heading level="2">Companies</Heading>*/}
      {/*</div>*/}
      {/*{data.customer.companies.length ? (*/}
      {/*  data.customer.companies.map(company => (*/}
      {/*  <div key={company!.id} className="mb-8">*/}
      {/*    <div className="md:flex items-baseline justify-between border-b-4 border-spr-white mb-2 pb-2">*/}
      {/*      <Heading level="3">*/}
      {/*        {company!.name}*/}
      {/*      </Heading>*/}
      {/*      <Button type="link">Edit {company!.name}</Button>*/}
      {/*    </div>*/}
      {/*  </div>)) ) : (*/}
      {/*  <b className="block mb-4">No companies yet</b>*/}
      {/*)}*/}
      {/*<NewCompanyPanel  />*/}


    </PageLayout>
  );
}
