import { FC, useState } from "react";
import "./context.css";

interface ContextProps {
  setJsonToSend: React.Dispatch<React.SetStateAction<string>>;
}

export const Context: FC<ContextProps> = ({ setJsonToSend }) => {
  const [docType, setDocType] = useState<string>("");
  const [transactionAmount, setTransactionAmount] = useState<string>("");
  const [deviceType, setDeviceType] = useState<string>("");
  const [urgency, setUrgency] = useState<string>("");
  const [bankSystemIssues, setBankSystemIssues] = useState<string>("");
  const [clientTypeIssues, setClientTypeIssues] = useState<string>("");
  const [balanceOpportunity, setBalanceOpportunity] = useState<string>("");
  const [userLocation, setUserLocation] = useState<string>("");

  const updateJson = (key: string, value: string) => {
    setJsonToSend((prevJson) => {
      const parsedJson = JSON.parse(prevJson);
      
      if (!parsedJson.context) {
        parsedJson.context = {};
      }

      parsedJson.context[key] = value;

      return JSON.stringify(parsedJson, null, 2);
    });
  };

  return (
    <div className="context">
      <div className="context__uppe">Моделирование контекста</div>
      <div className="context-options">
        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox1"
            value={docType}
            onChange={(e) => {
              setDocType(e.target.value);
              updateJson("docType", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Тип документа
            </option>
            <option value="PAYMENT_ORDER">Платежное поручение (перевод денег)</option>
            <option value="INVOICE">Накладная (поступление товара)</option>
            <option value="GOVERMENT_REQUESTS">
              Запросы государственных органов (высокая срочность - 5 дней){" "}
            </option>
            <option value="AGREEMENT">Договор (об оказании услуг)</option>
            <option value="BILLING_DETAILS">
              Счета фактуры (документ, подтверждающий НДС)
            </option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox2"
            value={transactionAmount}
            onChange={(e) => {
              setTransactionAmount(e.target.value);
              updateJson("transactionAmount", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Денежная сумма
            </option>
            <option value="LOW">Малая</option>
            <option value="MEDIUM">Средняя</option>
            <option value="HIGH">Большая</option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox3"
            value={deviceType}
            onChange={(e) => {
              setDeviceType(e.target.value);
              updateJson("deviceType", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Устройство, с которого был выполнен вход
            </option>
            <option value="MOBILE">Телефон</option>
            <option value="WEB">Компьютер</option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox4"
            value={urgency}
            onChange={(e) => {
              setUrgency(e.target.value);
              updateJson("urgency", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Срочность документа
            </option>
            <option value="HIGH">Высокая срочность</option>
            <option value="LOW">Низкая срочность</option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox5"
            value={bankSystemIssues}
            onChange={(e) => {
              setBankSystemIssues(e.target.value);
              updateJson("bankSystemIssues", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Проблемы на стороне Банка
            </option>
            <option value="NONE">Проблем нет</option>
            <option value="SMS">Проблемы с сервисом SMS</option>
            <option value="PAY_CONTROL">Проблемы с оплатой</option>
            <option value="QS_TOKEN">Проблемы с токеном</option>
            <option value="QS_MOBILE">Проблемы с мобильным приложением</option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox6"
            value={clientTypeIssues}
            onChange={(e) => {
              setClientTypeIssues(e.target.value);
              updateJson("clientTypeIssues", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Проблемы на стороне Клиента
            </option>
            <option value="NONE">Проблем нет</option>
            <option value="CONNECTION">Плохое соединение</option>
            <option value="UNAVAILABLE">Вне зоны доступа</option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox7"
            value={balanceOpportunity}
            onChange={(e) => {
              setBalanceOpportunity(e.target.value);
              updateJson("balanceOpportunity", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Денежная возможность
            </option>
            <option value="YES">Есть</option>
            <option value="NO">Нет</option>
          </select>
        </div>

        <div className="options-selectBox">
          <select
            className="selectBox__selector"
            id="selectBox8"
            value={userLocation}
            onChange={(e) => {
              setUserLocation(e.target.value);
              updateJson("userLocation", e.target.value);
            }}
          >
            <option value="" disabled hidden>
              Локация пользователя
            </option>
            <option value="BUILDING">В зданиии</option>
            <option value="ON_THE_GO">В дороге</option>
          </select>
        </div>
      </div>
    </div>
  );
};
