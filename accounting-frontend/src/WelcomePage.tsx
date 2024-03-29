import Heading from "./components/Heading";
import PageLayout from "./components/PageLayout";
import * as React from "react";
import logo from "./assets/accounting-logo.jpeg";

export default function WelcomePage() {

  return (
    <PageLayout title="Welcome">
      <Heading>Welcome</Heading>
      <img src={logo} alt="Accounting" />
    </PageLayout>
  );
}