import Button from "../components/Button";
import Heading from "../components/Heading";
import Link from "../components/Link";
import PageLayout from "../components/PageLayout";
import Section from "../components/Section";
import Table from "../components/Table";
import { useFindCustomerByIdQuery } from "../generated/graphql-types";
import * as React from "react";
import { useParams } from "react-router-dom";
// import NewVisitPanel from "./NewVisitPanel";


const stringToUuid = (str: any) => {
  str = str.replace('-', '');
  return 'xxxxxxxx-xxxx-4xxx-xxxx-xxxxxxxxxxxx'.replace(/[x]/g, function(c, p) {
    return str[p % str.length];
  });
}

export default function CustomerPage() {
  const { customerId } = useParams<{ customerId: string }>();

  const { loading, data, error } = useFindCustomerByIdQuery({
    variables: {
      customerId: stringToUuid(customerId),
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
      {/*<div className="mb-4">*/}
      {/*  <Heading level="2">Pets and Visits</Heading>*/}
      {/*</div>*/}
      {/*{data.owner.pets.map((pet) => (*/}
      {/*  <div key={pet.id} className="mb-8">*/}
      {/*    <Section invert>*/}
      {/*      <div className="md:flex items-baseline justify-between border-b-4 border-spr-white mb-2 pb-2">*/}
      {/*        <Heading level="3">*/}
      {/*          {pet.name} ({pet.type.name}, * {pet.birthDate})*/}
      {/*        </Heading>*/}
      {/*        <Button type="link">Edit {pet.name}</Button>*/}
      {/*      </div>*/}
      {/*      {pet.visits.visits.length ? (*/}
      {/*        <Table*/}
      {/*          labels={["Visit Date", "Treating vet", "Description"]}*/}
      {/*          values={pet.visits.visits.map((visit) => [*/}
      {/*            visit.date,*/}
      {/*            visit.treatingVet ? (*/}
      {/*              <Link to={`/vets/${visit.treatingVet.id}`}>*/}
      {/*                {visit.treatingVet.firstName} {visit.treatingVet.lastName}*/}
      {/*              </Link>*/}
      {/*            ) : (*/}
      {/*              ""*/}
      {/*            ),*/}
      {/*            visit.description,*/}
      {/*          ])}*/}
      {/*        />*/}
      {/*      ) : (*/}
      {/*        <b className="block mb-4">No visits yet</b>*/}
      {/*      )}*/}
      {/*      <NewVisitPanel petId={pet.id} />*/}
      {/*    </Section>*/}
      {/*  </div>*/}
      {/*))}*/}
    </PageLayout>
  );
}
