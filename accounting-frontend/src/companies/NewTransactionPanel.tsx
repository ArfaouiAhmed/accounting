import Button from "../components/Button";
import * as React from "react";
import NewTransactionForm from "./NewTransactionForm";
import { ID } from "graphql-ws";
import { useParams } from "react-router-dom";


export default function NewTransactionPanel() {
  const { companyId } = useParams<{ companyId: ID }>();
  const [formOpen, setFormOpen] = React.useState(false);

  if (!formOpen) {
    return <Button onClick={() => setFormOpen(true)}>New Transaction</Button>;
  }

  return <NewTransactionForm onFinish={() => setFormOpen(false)} companyId={companyId} />;
}
