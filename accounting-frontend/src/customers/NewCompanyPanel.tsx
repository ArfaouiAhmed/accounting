import Button from "../components/Button";
import * as React from "react";
import NewCompanyForm from "./NewCompanyForm";
import { ID } from "graphql-ws";
import { useParams } from "react-router-dom";

type NewCompanyPanelProps = { customerId: ID };

export default function NewCompanyPanel() {
  const { customerId } = useParams<{ customerId: ID }>();
  const [formOpen, setFormOpen] = React.useState(false);

  if (!formOpen) {
    return <Button onClick={() => setFormOpen(true)}>New Company</Button>;
  }

  return <NewCompanyForm onFinish={() => setFormOpen(false)} customerId={customerId} />;
}
