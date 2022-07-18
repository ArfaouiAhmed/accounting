// eslint-disable-next-line
declare var process: any;

const backendHost = window.__accounting__backend_host__.replace(
    "__BACKEND__",
    "http://localhost:8080"
);
export const graphqlApiUrl = `${backendHost}/graphql`;
export const loginApiUrl = `${backendHost}/login`;

console.log("USING GRAPHQL API URL", graphqlApiUrl);
console.log("USING LOGIN API URL", loginApiUrl);