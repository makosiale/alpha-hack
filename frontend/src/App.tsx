import { useState } from "react";
import "./App.css";
import { ApiInfo } from "./components/apiInfo/apiInfo";
import { Context } from "./components/context/context";
import { CreatorsInfo } from "./components/creatorsInfo/creatorsInfo";
import { JsonRedactor } from "./components/jsonRedactor/jsonRedactor";
import { Recommendations } from "./components/recommendations/recommendations";

interface FinalJsonProps {
  signatureMethod: string;
  information: string;
}

export const App = () => {
  const [jsonToSend, setJsonToSend] = useState<string>(
    JSON.stringify(
      {
        clientId: 1,
        organizationId: 1,
        segment: "SMALL_BUSINESSES",
        role: "EIO",
        organizations: 3,
        currentMethod: "SMS",
        mobileApp: true,
        signatures: {
          common: {
            SMS: 3,
            PAY_CONTROL: 10,
            QS_TOKEN: 3,
            QS_MOBILE: 4,
          },
          special: {
            SMS: 3,
            PAY_CONTROL: 10,
            QS_TOKEN: 3,
            QS_MOBILE: 4,
          }
        },
        availableMethods: ["SMS", "PAY_CONTROL", "QS_TOKEN"],
        reports: {
          SMS: 0,
          PAY_CONTROL: 0,
          QS_TOKEN: 0,
          QS_MOBILE: 0
        }
      },
      null,
      2
    )
  );

  const [finalJson, setFinalJson] = useState<FinalJsonProps | null>(null);

  return (
    <div className="container">
      <Context setJsonToSend={setJsonToSend} />
      <CreatorsInfo />
      <ApiInfo />
      <JsonRedactor jsonToSend={jsonToSend} setJsonToSend={setJsonToSend} setFinalJson={setFinalJson}/>
      <Recommendations finalJson={finalJson}/>
    </div>
  );
};
