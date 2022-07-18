import Heading from "./components/Heading";
import PageLayout from "./components/PageLayout";
import * as React from "react";

export default function WelcomePage() {
  const publicPath = process.env.PUBLIC_URL;
  const AccountingImgSrc = `${publicPath}/accounting-logo.jpeg`;
  return (
    <PageLayout title="Welcome">
      <Heading>Welcome</Heading>
      <img src={AccountingImgSrc} alt="Accounting" />
    </PageLayout>
  );
}