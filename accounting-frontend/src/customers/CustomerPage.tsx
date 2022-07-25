import Button from "../components/Button";
import Heading from "../components/Heading";
import Link from "../components/Link";
import PageLayout from "../components/PageLayout";
import Section from "../components/Section";
import Table from "../components/Table";
import { useFindCustomerByIdQuery } from "../generated/graphql-types";
import * as React from "react";
import { useParams } from "react-router-dom";
import NewCompanyPanel from "./NewCompanyPanel";

export default function CustomerPage() {
  const { customerId } = useParams<{ customerId: string }>();

  const { loading, data, error } = useFindCustomerByIdQuery({
    variables: {
      customerId: customerId,
    },
  });

  if (loading) {
    return <PageLayout title="Customers">Loading</PageLayout>;
  } else if (error || !data) {
    return <PageLayout title="Customers">Error</PageLayout>;
  }

  return (
    <PageLayout
      title={`Customers - ${data.customer.name}`}
    >
      <Section>
        <Table
          values={[
            ["Name", data.customer.name],
            ["Address", data.customer.address],
            ["City", data.customer.city],
            ["Telephone", data.customer.phone],
          ]}
        ></Table>
      </Section>
      <div className="mb-4">
        <Heading level="2">Companies</Heading>
      </div>
      {/*{data.customer.companies.map((company) => (*/}
      {/*  <div key={company.id} className="mb-8">*/}
      {/*    <Section invert>*/}
      {/*      <div className="md:flex items-baseline justify-between border-b-4 border-spr-white mb-2 pb-2">*/}
      {/*        <Heading level="3">*/}
      {/*          {company.name}*/}
      {/*        </Heading>*/}
      {/*        <Button type="link">Edit {company.name}</Button>*/}
      {/*      </div>*/}
      {/*      /!*{company.visits.visits.length ? (*!/*/}
      {/*      /!*  <Table*!/*/}
      {/*      /!*    labels={["Visit Date", "Treating vet", "Description"]}*!/*/}
      {/*      /!*    values={company.visits.visits.map((visit) => [*!/*/}
      {/*      /!*      visit.date,*!/*/}
      {/*      /!*      visit.treatingVet ? (*!/*/}
      {/*      /!*        <Link to={`/vets/${visit.treatingVet.id}`}>*!/*/}
      {/*      /!*          {visit.treatingVet.firstName} {visit.treatingVet.lastName}*!/*/}
      {/*      /!*        </Link>*!/*/}
      {/*      /!*      ) : (*!/*/}
      {/*      /!*        ""*!/*/}
      {/*      /!*      ),*!/*/}
      {/*      /!*      visit.description,*!/*/}
      {/*      /!*    ])}*!/*/}
      {/*      /!*  />*!/*/}
      {/*      /!*) : (*!/*/}
      {/*      /!*  <b className="block mb-4">No visits yet</b>*!/*/}
      {/*      /!*)}*!/*/}
      {/*      <NewCompanyPanel companyId={company.id} />*/}
      {/*    </Section>*/}
      {/*  </div>*/}
      {/*))}*/}
    </PageLayout>
  );
}
