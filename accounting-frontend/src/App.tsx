import { AnonymousPageLayout } from "./components/PageLayout";
import { useMeLazyQuery } from "./generated/graphql-types";
import { useAuthToken } from "./login/AuthTokenProvider";
import LoginPage from "./login/LoginPage";
import NotFoundPage from "./NotFoundPage";
// import CustomerPage from "customers/CustomerPage";
// import CustomersPage from "customers/CustomerSearchPage";
// import CompanyPage from "companies/CompanyPage";
import React from "react";
import { Route, Switch } from "react-router-dom";
import WelcomePage from "./WelcomePage";

function App() {
  const [token] = useAuthToken();
  const [queryMe, { called, loading, error }] = useMeLazyQuery();

  React.useEffect(() => {
    // don't try to read user data if we don't have token.
    if (token) {
      queryMe();
    }
  }, [queryMe, token]);

  if (!token || error) {
    return <LoginPage />;
  }

  if (loading || !called) {
    return <AnonymousPageLayout title="Initializing..."></AnonymousPageLayout>;
  }

  return (
    <Switch>
      <Route path="/" exact>
        <WelcomePage />
      </Route>
      {/*<Route path="/customers/:customerId" exact>*/}
      {/*  <CustomerPage />*/}
      {/*</Route>*/}
      {/*<Route path="/customers" exact>*/}
      {/*  <CustomersPage />*/}
      {/*</Route>*/}
      {/*<Route path="/companies/:companyId?">*/}
      {/*  <CompanyPage />*/}
      {/*</Route>*/}
      <Route>
        <NotFoundPage />
      </Route>
    </Switch>
  );
}

export default App;