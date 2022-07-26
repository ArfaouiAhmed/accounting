import Button from "../components/Button";
import * as React from "react";
import NewCompanyForm from "./NewCompanyForm";
import { ID } from "graphql-ws";

type NewCompanyPanelProps = { customerId: ID };

export default function NewCompanyPanel({ customerId }: NewCompanyPanelProps) {
  const [formOpen, setFormOpen] = React.useState(false);

  if (!formOpen) {
    return <Button onClick={() => setFormOpen(true)}>New Company</Button>;
  }

  return <NewCompanyForm onFinish={() => setFormOpen(false)} customerId={customerId} />;
}
